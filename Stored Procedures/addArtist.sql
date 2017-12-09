CREATE DEFINER=`root`@`localhost` PROCEDURE `addArtist`(artistLastName varchar(45), artistFirstName varchar(45), 
				screenName varchar(45), artistGender varchar(1))
BEGIN
	insert into artist(lastName, firstName, screen_name, gender) 
		values (artistLastName, artistFirstName, screenName, artistGender);
END