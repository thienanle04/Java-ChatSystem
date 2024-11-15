CREATE DATABASE chatsystem
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;
USE chatsystem;

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	verify_code varchar(255) DEFAULT NULL,
    status ENUM('online', 'offline') DEFAULT 'offline',
    role ENUM('admin', 'user') DEFAULT 'user'
);

CREATE TABLE Login_History (
	login_id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT,
	logged_in_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE User_Friends (
    user_id INT,
    friend_id INT,
    status ENUM('pending', 'accepted', 'blocked', 'rejected') NOT NULL,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, friend_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (friend_id) REFERENCES Users(user_id)
);

CREATE TABLE Chat_Group (
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(100) NOT NULL,
    created_by INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_encrypted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (created_by) REFERENCES Users(user_id)
);

CREATE TABLE Group_Members (
    group_id INT,
    user_id INT,
    role ENUM('admin', 'member') DEFAULT 'member',
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (group_id, user_id),
    FOREIGN KEY (group_id) REFERENCES Chat_Group(group_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Group_Messages (
    message_id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT NOT NULL,
    sender_id INT NOT NULL,
    message TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (group_id) REFERENCES Chat_Group(group_id),
    FOREIGN KEY (sender_id) REFERENCES Users(user_id)
);

CREATE TABLE Message_Visibility (
    visibility_id INT PRIMARY KEY AUTO_INCREMENT,
    message_id INT NOT NULL,
    user_id INT NOT NULL,
    is_sender BOOLEAN DEFAULT FALSE,
    visibility_status ENUM('visible', 'hidden') DEFAULT 'visible',
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (message_id) REFERENCES Group_Messages(message_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

DROP TRIGGER IF EXISTS after_message_insert;
DELIMITER //

CREATE TRIGGER after_message_insert
AFTER INSERT ON Group_Messages
FOR EACH ROW
BEGIN
    -- Insert visibility record for each user in the group
    INSERT INTO Message_Visibility (message_id, user_id, visibility_status, modified_at, is_sender)
    SELECT NEW.message_id, user_id, 'visible', CURRENT_TIMESTAMP, 
           IF(user_id = NEW.sender_id, TRUE, FALSE)
    FROM Group_Members
    WHERE group_id = NEW.group_id;
END;
//

DELIMITER ;


DROP TRIGGER IF EXISTS before_visibility_update;
DELIMITER //

CREATE TRIGGER before_visibility_update
BEFORE UPDATE ON Message_Visibility
FOR EACH ROW
BEGIN
    DECLARE message_sent_time TIMESTAMP;

    -- Get the sent time of the message
    SELECT sent_at INTO message_sent_time
    FROM Group_Messages
    WHERE message_id = NEW.message_id;

    -- Check if the sender is hiding the message for others
    IF NEW.visibility_status = 'hidden' AND OLD.visibility_status = 'visible' THEN
        IF NOT NEW.is_sender THEN
            -- Enforce the 1-day rule for hiding the message from others
            IF TIMESTAMPDIFF(HOUR, message_sent_time, NOW()) >= 24 THEN
                SIGNAL SQLSTATE '45000' 
                SET MESSAGE_TEXT = 'You can only hide this message from others within one day of sending it.';
            END IF;
        END IF;
    END IF;
END;
//

DELIMITER ;

