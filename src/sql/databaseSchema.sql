DROP DATABASE IF EXISTS `nexus`;
CREATE DATABASE `nexus`;
USE `nexus`;

CREATE TABLE `Tickets` (
`id` INT(4) NOT NULL,
`dateTime` DATETIME DEFAULT 0 NOT NULL,
`createdBy` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`problemLocation` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`problemDescription` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`CISKeywords` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`delegateImpact` ENUM('Low', 'Medium', 'High') NOT NULL,
`showOnCIS` ENUM('Yes','No') NOT NULL,
PRIMARY KEY (`id`)
) Engine = InnoDB;




-- Tables Needed: Tickets, Users, Team Members