DROP DATABASE IF EXISTS `Nexus`;
CREATE DATABASE `Nexus`;
USE `Nexus`;

CREATE TABLE `Tickets` (
`job_ref_id` INT(4) NOT NULL AUTO_INCREMENT,
`dateTime` DATETIME NOT NULL,
`ticket_raised_by_team` ENUM('Control Office', 'Information Team', 'Administration Team') NOT NULL,
`ticket_raised_by_member` VARCHAR(255) NOT NULL,
`problem_location` VARCHAR(255) NOT NULL,
`problem_description` VARCHAR(255) NOT NULL,
`CIS_keywords` VARCHAR(255) NOT NULL,
`reported_by` VARCHAR(255) NOT NULL,
`who_is_a` ENUM ('Delegate', 'Speaker', 'Team Member') NOT NULL,
`contact_via` ENUM('Mobile', 'Radio', 'Village', 'Village Host', 'Not Required') NOT NULL,
`contact_number` VARCHAR(255),
`location_venue_village` ENUM ('Venue', 'Village', 'Not Required'),
`delegateImpact` ENUM('Low', 'Medium', 'High') NOT NULL,
`show_on_CIS` BOOLEAN NOT NULL,
`ticket_allocated_to` ENUM('Control Office', 'Day Stewards', 'Night Stewards',
'Site Crew', 'Cleaners (Jackie)', 'WigWam', 'Production'),
`job_progress` ENUM('Issue Reported', 'Ticket Printed', 'Job In Progress',
'Job Escalated', 'Job Complete', 'Duplicate Job') NOT NULL,
`update_1_description` VARCHAR(144),
`update_1_estimatedCompletion` DATETIME,
`update_1_updatedAt` DATETIME,
`update_2_description` VARCHAR(144),
`update_2_estimatedCompletion` DATETIME,
`update_2_updatedAt` DATETIME,
`update_3_description` VARCHAR(144),
`update_3_estimatedCompletion` DATETIME,
`update_3_updatedAt` DATETIME,
`job_closed` DATETIME,
`next_update_due` DATETIME,
PRIMARY KEY (`job_ref_id`)
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


