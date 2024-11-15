CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	verify_code varchar(255) DEFAULT NULL,
    status ENUM('online', 'offline') DEFAULT 'offline'
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

CREATE TABLE Chats (
    chat_id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    message TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (sender_id) REFERENCES Users(user_id),
    FOREIGN KEY (receiver_id) REFERENCES Users(user_id)
);

CREATE TABLE Chat_History (
    history_id INT PRIMARY KEY AUTO_INCREMENT,
    chat_id INT,
    user_id INT,
    action ENUM('sent', 'deleted') NOT NULL,
    action_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chat_id) REFERENCES Chats(chat_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE ChatGroup (
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
    FOREIGN KEY (group_id) REFERENCES ChatGroup(group_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Group_Messages (
    message_id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT NOT NULL,
    sender_id INT NOT NULL,
    message TEXT NOT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (group_id) REFERENCES ChatGroup(group_id),
    FOREIGN KEY (sender_id) REFERENCES Users(user_id)
);

