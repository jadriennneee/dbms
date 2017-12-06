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
        String stSel = "SELECT * FROM album ORDER BY 3";
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery(stSel); 
        
        menu();
        System.out.print("Enter your choice: ");
        int choice = console.nextInt();
        
        while(choice != 13){
            switch(choice){
                case 1:
                case 2:
                case 3:
                case 4:
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
