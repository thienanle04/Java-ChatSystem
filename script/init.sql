CREATE DATABASE chatsystem
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_unicode_ci;
USE chatsystem;

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    address VARCHAR(255) DEFAULT NULL,
    date_of_birth DATE DEFAULT NULL,
    gender ENUM('Male', 'Female', 'Other') DEFAULT 'Other',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	verify_code varchar(255) DEFAULT NULL,
    status ENUM('online', 'offline') DEFAULT 'offline',
    role ENUM('admin', 'user') DEFAULT 'user',
     is_locked BOOLEAN DEFAULT FALSE
);

CREATE TABLE Login_History (
	login_id INT PRIMARY KEY AUTO_INCREMENT,
	user_id INT,
	logged_in_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

--  user_id_1 < user_id_2 (one record for 2 peopel)
CREATE TABLE User_Friends (
    user_id_1 INT,
    user_id_2 INT,
    status ENUM('pending_1_2', 'pending_2_1', 'friends', 'block_1_2', 'block_2_1') NOT NULL,
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id_1, user_id_2),
    FOREIGN KEY (user_id_1) REFERENCES Users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id_2) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE Chat_Group (
    group_id INT PRIMARY KEY AUTO_INCREMENT,
    group_name VARCHAR(100) default 'New group chat',
    group_type INT DEFAULT 2,
    created_by INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_encrypted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (created_by) REFERENCES Users(user_id) ON DELETE SET NULL
);

CREATE TABLE Group_Members (
    group_id INT,
    user_id INT,
    role ENUM('admin', 'member') DEFAULT 'member',
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (group_id, user_id),
    FOREIGN KEY (group_id) REFERENCES Chat_Group(group_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

CREATE TABLE Group_Messages (
    message_id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT NOT NULL,
    sender_id INT,
    message TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (group_id) REFERENCES Chat_Group(group_id) ON DELETE CASCADE,
    FOREIGN KEY (sender_id) REFERENCES Users(user_id) ON DELETE SET NULL
);

CREATE TABLE Message_Visibility (
    visibility_id INT PRIMARY KEY AUTO_INCREMENT,
    message_id INT NOT NULL,
    user_id INT,
    is_sender BOOLEAN DEFAULT FALSE,
    visibility_status ENUM('visible', 'hidden', 'deleted') DEFAULT 'visible',
    modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (message_id) REFERENCES Group_Messages(message_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE SET NULL
);

CREATE TABLE spam_list (
  report_id int NOT NULL,
  report_by int DEFAULT NULL,
  report_user int DEFAULT NULL,
  report_at timestamp NULL DEFAULT NULL,
  PRIMARY KEY (report_id),
  KEY report_by (report_by),
  KEY report_user (report_user),
  CONSTRAINT spam_list_ibfk_1 FOREIGN KEY (report_by) REFERENCES users (user_id) ON DELETE CASCADE,
  CONSTRAINT spam_list_ibfk_2 FOREIGN KEY (report_user) REFERENCES users (user_id) ON DELETE CASCADE
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

DROP TRIGGER IF EXISTS create_private_chat_after_accept_friend_request;
DELIMITER //

CREATE TRIGGER create_private_chat_after_accept_friend_request
AFTER UPDATE ON User_Friends
FOR EACH ROW
BEGIN
	DECLARE chat_id INT;
	-- Check if the friend request status is updated to 'accepted'
    IF NEW.status = 'friends' AND OLD.status != 'friends' THEN
        select group_id into chat_id
        from group_members 
        where user_id = NEW.user_id_1
        and group_id in (select gm.group_id
						from group_members gm join chat_group cg on cg.group_id = gm.group_id
                        where gm.user_id = NEW.user_id_2 and cg.group_type = 2) limit 1;
                        
		IF chat_id IS NULL THEN
			-- Insert a new group chat record with type = 2 (for friend-based group)
			INSERT INTO Chat_Group (group_type) VALUES (2);
        
			-- Add both users to the new group chat
			INSERT INTO group_members (group_id, user_id) 
			VALUES (LAST_INSERT_ID(), NEW.user_id_1), (LAST_INSERT_ID(), NEW.user_id_2);
		END IF;
    END IF;
END;
//

DELIMITER ;

