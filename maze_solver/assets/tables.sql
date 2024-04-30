CREATE DATABASE maze;

CREATE TABLE user (id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(500) NOT NULL,
name VARCHAR(300) NOT NULL,
nif VARCHAR(20) NOT NULL UNIQUE,
email VARCHAR(300) NOT NULL UNIQUE,
address VARCHAR(500),
birthdate DATE,
role VARCHAR(50)
);