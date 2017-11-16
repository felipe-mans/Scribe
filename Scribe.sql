DROP DATABASE if exists Scribe;
CREATE DATABASE Scribe;
USE Scribe;
CREATE TABLE Users (
	userID INTEGER PRIMARY KEY not null auto_increment,
    fname VARCHAR(255) not null,
    lname VARCHAR(255) not null,
	username VARCHAR(255) not null,
    userPass VARCHAR(255) not null,
    email VARCHAR(255)
    );

CREATE TABLE b {

	};
	
CREATE TABLE c {

	};
	
INSERT INTO Users (fname, lnam, username, userPass, email)
VALUES ('Vincent','Rodriguez', 'vincentr', 'sike', 'vincentr@usc.edu');