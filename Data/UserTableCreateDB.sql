DROP database virtualdesktop;
CREATE database virtualdesktop;
USE virtualdesktop;

DROP TABLE Users;
DROP TABLE UserType;

CREATE TABLE UserType (
		TypeId INT(1) PRIMARY KEY NOT NULL,
		Name VARCHAR(5),
		AccountType VARCHAR(1)
		);

INSERT INTO UserType VALUES (1, "Admin","A");
INSERT INTO UserType VALUES (2, "User","U");

CREATE TABLE Users (
        UserId INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
        Name VARCHAR(30),
		Surname VARCHAR(30),
		Email VARCHAR(30),
		Password VARCHAR(100),
		DataFreeAmount FLOAT(11),
		AccountType INT(1) DEFAULT(2),
		FOREIGN KEY (AccountType) REFERENCES UserType(TypeId)
		);

INSERT INTO Users VALUES (null,"Damian", "Radecki","damrad@admin.pl","5BMnlQGuZy1N/uHZCrt4Fw==", 1024,1);

SET GLOBAL time_zone='+00:00';