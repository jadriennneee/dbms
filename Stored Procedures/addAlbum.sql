CREATE DEFINER=`root`@`localhost` PROCEDURE `addAlbum`(albumAlbum_no int(11), albumName varchar(45), albumRelease_year year(4))
BEGIN
	insert into album(album_no, name, release_year)
		values(albumAlbum_no, albumName, albumRelease_year);
END