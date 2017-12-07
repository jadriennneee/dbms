CREATE DEFINER=`root`@`localhost` PROCEDURE `addSong`(songTitle varchar(45), songDuration varchar(45))
BEGIN
	Insert into songs(title, duration) Values(songTitle, songDuration);
END