CREATE TABLE user(
id int PRIMARY KEY AUTO_INCREMENT,
name varchar(256)  NOT NULL,
email varchar(256) UNIQUE NOT NULL,
password varchar(256)  NOT NULL,
create_date date NOT NULL,
update_date date NOT NULL
)

CREATE TABLE category(
id int PRIMARY KEY AUTO_INCREMENT,
category_name varchar(256) NOT NULL,
)

CREATE TABLE threads(
id int PRIMARY KEY AUTO_INCREMENT,
user_name varchar(256) NOT NULL,
title varchar(256) NOT NULL,
threads_category_id int NOT NULL,
create_date date NOT NULL,
update_date date NOT NULL,
user_id int NOT NULL
)

CREATE TABLE posts(
id int PRIMARY KEY AUTO_INCREMENT,
user_name varchar(256) NOT NULL,
message text NOT NULL,
thread_id int NOT NULL,
create_date date NOT NULL,
update_date date NOT NULL,
user_id int NOT NULL,
title varchar(256) NOT NULL
)