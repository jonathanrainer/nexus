DROP DATABASE IF EXISTS `Nexus`;
CREATE DATABASE `Nexus`;
USE `Nexus`;

CREATE TABLE `Tickets` (
`id` INT(4) NOT NULL,
`dateTime` DATETIME DEFAULT 0 NOT NULL,
`createdBy` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`problemLocation` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`problemDescription` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`CISKeywords` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`delegateImpact` ENUM('Low', 'Medium', 'High') NOT NULL,
PRIMARY KEY (`id`)
) Engine = InnoDB;

--CREATE TABLE `Verification-Queue` (
--)

--CREATE TABLE `CIS-Entries` (
--)

CREATE TABLE `Users` (
`userid` CHAR(6) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
`team` ENUM('Super Users', 'Control Office', 'Night Crew', 'Information Team',
'Administration Team') NOT NULL,
)
