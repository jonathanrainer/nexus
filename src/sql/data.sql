TRUNCATE TABLE `Nexus`.`Users`;

INSERT INTO `Nexus`.`Users`
(`name`,`team`, `password`)
VALUES
('Helene Roe','Control Office', NULL),
('Andrew Wyss','Control Office', NULL),
('Katie Tupling','Control Office', NULL),
('Ian Walker','Control Office', NULL),
('John','Control Office', NULL),
('Mel Meesam','Control Office', NULL),
('Colin','Control Office', NULL),
('Hannah Allison','Control Office', NULL),
/* Admin Team */
('David Jones','Administration Team', NULL),
('Jonathan Rainer','Administration Team', NULL);
