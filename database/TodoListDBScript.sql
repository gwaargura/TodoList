USE [master]
GO

IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'TodoListDBScript')
BEGIN
	ALTER DATABASE TodoListDBScript SET OFFLINE WITH ROLLBACK IMMEDIATE;
	ALTER DATABASE TodoListDBScript SET ONLINE;
	DROP DATABASE TodoListDBScript;
END

GO

CREATE DATABASE TodoListDBScript
GO

USE TodoListDBScript
GO

/*
CREATE TABLE ToDo(
	Id INT,
	Goal NVARCHAR(100),
	DueDate VARCHAR(10),
	CreateDate VARCHAR(10),
	Completed BIT,
	PRIMARY KEY (Id)
);

INSERT INTO ToDo (Id, Goal, DueDate, CreateDate, Completed)
VALUES(1, 'Connect to the database successfully', '11/07/2024', '12/07/2024', 0);
*/