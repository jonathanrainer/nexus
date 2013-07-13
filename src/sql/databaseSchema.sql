DROP DATABASE IF EXISTS `Nexus`;
CREATE DATABASE `Nexus`;
USE `Nexus`;

CREATE TABLE `Tickets` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`dateTime` DATETIME DEFAULT 0 NOT NULL,
`createdBy` VARCHAR(255) NOT NULL,
`problemLocation` VARCHAR(255) NOT NULL,
`problemDescription` VARCHAR(255) NOT NULL,
`CISKeywords` VARCHAR(255) NOT NULL,
`delegateImpact` ENUM('Low', 'Medium', 'High') NOT NULL,
PRIMARY KEY (`id`)
) Engine = InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE `Users` (
`userID` INT(4) AUTO_INCREMENT  NOT NULL,
`name` VARCHAR(255) NOT NULL,
`team` ENUM('Super Users', 'Control Office', 'Information Team', 'Administration Team') NOT NULL,
`password` VARCHAR(255) NULL,
PRIMARY KEY (`userID`)
) Engine = InnoDB CHARACTER SET utf8 COLLATE utf8_bin;


--CREATE TABLE `Verification-Queue` (
--)

--CREATE TABLE `CIS-Entries` (
--)


