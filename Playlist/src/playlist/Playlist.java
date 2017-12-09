package playlist;

import java.sql.*;
import java.util.*;

public class Playlist {

    public static void main(String[] args) throws Exception {
       Scanner console = new Scanner(System.in);
       Class.forName("com.mysql.jdbc.Driver");
        
        //Set-up the Connection to the Database
        String conStr = "jdbc:mysql://localhost:3306/dbms?user=root&password=";
        Connection con = DriverManager.getConnection(conStr);
        System.out.println("Connection Done");
        String stSel = "SELECT * FROM songs";
        String stSelartist = "SELECT * FROM artist";        
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery(stSel); 
        ResultSet rsart = stmt.executeQuery(stSelartist); 
        
        menu();
        
        CallableStatement callsp = null;

        System.out.print("Enter your choice: ");
        int choice = console.nextInt();
        
        switch(choice){
            case 1:
                //Add Song
                System.out.println("Song Name: ");
                console.nextLine(); //Bypass Bug
                String songTitle = console.nextLine();
                System.out.println("Song Duration; ");
                String songDuration = console.nextLine();
                //Call for the stored procedure in mySQL
                String callSong = "{call addSong(?,?)}";
                callsp = con.prepareCall(callSong);
                //Set Values for Song
                callsp.setString(1, songTitle);
                callsp.setString(2, songDuration);
                callsp.executeUpdate();

                //Add Artist
                System.out.println("Artist's Last Name: ");
                String artistLastName = console.nextLine();
                System.out.println("Artist's First Name: ");
                String artistFirstName = console.nextLine();
                System.out.println("Screen Name: ");
                String screenName = console.nextLine();
                System.out.println("Gender: ");
                String gender = console.nextLine();
                //Call for the stored procedure
                String callArtist = "{call addArtist(?,?,?,?)}";
                callsp = con.prepareCall(callArtist);
                //Set Values
                callsp.setString(1, artistLastName);
                callsp.setString(2, artistFirstName);
                callsp.setString(3, screenName);
                callsp.setString(4, gender);
                callsp.executeUpdate();
                
                System.out.println("Details has been added to the table.");
                //View results on MySQL 
                break;
            case 2:
            case 3:
            case 4:
            System.out.println("Input Artist's Screen Name: ");
            String searchScreenName = console.nextLine();
            int artistID = rsart.getInt(1);
            String queryScName = rsart.getString("screen_name");
            if(searchScreenName == queryScName){
                
            
            }else 
                System.out.print("gago");
            
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            default:
                System.out.println("Please enter a valid choice");
                break;
        }
    }
    
    public static void menu(){
        System.out.println("Please Choose an Action: ");
        System.out.println("1. Add a new song along with its artists");
        System.out.println("2. Add a new album along with its songs");
        System.out.println("3. Display all songs along with their artists");
        System.out.println("4. Display all songs of a particular artist");
        System.out.println("5. Display all songs and the albums they belong to");
        System.out.println("6. Display all songs from a specific album");
        System.out.println("7. Edit an artist's information");
        System.out.println("8. Edit a song's information");
        System.out.println("9. Delete an album along with its songs");
        System.out.println("10. Delete an artist from the list along with its songs");
        System.out.println("11. Delete a song from the list");
        System.out.println("12. Delete all songs of a specific genre");
        System.out.println("13. Exit");
    }
}
