TRUNCATE TABLE `Nexus`.`Users`;

INSERT INTO `Nexus`.`Users`
(`name`,`team`, `password`)
VALUES
/* Admins and Super Users */
('Ian Walker', 'Super Users', NULL),
('Jonathan Rainer', 'Super Users', NULL),
('Andrew Wyss', 'Super Users', NULL),
('CT SU1', 'Super Users', NULL),
('CT SU2', 'Super Users', NULL),
('Info SU1', 'Super Users', NULL),
('Info SU2', 'Super Users', NULL),
('Admin SU1', 'Super Users', NULL),
('Admin SU2', 'Super Users', NULL),
/* Control Office */
('Andrew Wyss','Control Office', NULL),
('Katie Tupling','Control Office', NULL),
('Ian Walker','Control Office', NULL),
('Alison Fogg','Control Office', NULL),
('Colin Evans','Control Office', NULL),
('Hannah Allison','Control Office', NULL),
('Helene Roe','Control Office', NULL),
('John Haslam','Control Office', NULL),
('Mel Meesam','Control Office', NULL),
('Peter Frymann','Control Office', NULL),
('Richard Lawrence','Control Office', NULL),
('Wayne [Night Crew]','Control Office', NULL),
/* Information Team */
('Mark Rance', 'Information Team', NULL),
/* Admin Team */
('David Jones','Administration Team', NULL),
('Jonathan Rainer','Administration Team', NULL)
;
