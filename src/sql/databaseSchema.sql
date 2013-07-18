DROP DATABASE IF EXISTS `Nexus`;
CREATE DATABASE `Nexus`;
USE `Nexus`;

CREATE TABLE `tickets` (
`jobRefId` INT(4) NOT NULL AUTO_INCREMENT,
`dateTime` DATETIME NOT NULL,
`ticketRaisedByTeam` ENUM('Control Office', 'Information Team', 'Administration Team') NOT NULL,
`ticketRaisedByMember` VARCHAR(255) NOT NULL,
`problemLocation` VARCHAR(255) NOT NULL,
`problemDescription` VARCHAR(255) NOT NULL,
`CISKeywords` VARCHAR(255) NOT NULL,
`reportedBy` VARCHAR(255) NOT NULL,
`whoIsA` ENUM ('Delegate', 'Speaker', 'Team Member') NOT NULL,
`contactVia` ENUM('Mobile', 'Radio', 'Village', 'Village Host', 'Not Required') NOT NULL,
`contactNumber` VARCHAR(255),
`locationVenueVillage` ENUM ('Venue', 'Village', 'Not Required'),
`delegateImpact` ENUM('Low', 'Medium', 'High') NOT NULL,
`showOnCIS` BOOLEAN NOT NULL,
`ticketAllocatedTo` ENUM('Control Office', 'Day Stewards', 'Night Stewards',
'Site Crew', 'Cleaners (Jackie)', 'WigWam', 'Production'),
`jobProgress` ENUM('Issue Reported', 'Ticket Printed', 'Job In Progress',
'Job Escalated', 'Job Complete', 'Duplicate Job') NOT NULL,
`asAt` DATETIME NOT NULL,
`update1Description` VARCHAR(144),
`update1EstimatedCompletion` DATETIME,
`update1UpdatedAt` DATETIME,
`update2Description` VARCHAR(144),
`update2EstimatedCompletion` DATETIME,
`update2UpdatedAt` DATETIME,
`update3Description` VARCHAR(144),
`update3EstimatedCompletion` DATETIME,
`update3UpdatedAt` DATETIME,
`jobClosed` DATETIME,
`nextUpdateDue` DATETIME,
PRIMARY KEY (`jobRefId`)
) Engine = InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE `users` (
`userID` INT(4) AUTO_INCREMENT  NOT NULL,
`name` VARCHAR(255) NOT NULL,
`team` ENUM('Super Users', 'Control Office', 'Information Team', 'Administration Team') NOT NULL,
`password` VARCHAR(255) NULL,
PRIMARY KEY (`userID`)
) Engine = InnoDB CHARACTER SET utf8 COLLATE utf8_bin;

CREATE TABLE `DuplicateQueue` LIKE `Tickets`;


