import java.sql.*;
import java.util.*;

public class Album {

    public static void main(String[] args)throws Exception {
        Scanner kbd = new Scanner(System.in);
        Class.forName("com.mysql.jdbc.Driver");
        
        //Set-up the Connection to the Database
        String conStr = "jdbc:mysql://localhost:3306/dbms?user=root&password=";
        Connection con = DriverManager.getConnection(conStr);
        System.out.println("Connection Done");
        String stSel = "SELECT * FROM album ORDER BY 3";
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery(stSel);    
        int menu;
        System.out.println("Album");
        System.out.println(" 1. Add an album ");
        System.out.println(" 2. Show all albums ");
        System.out.println(" 3. Edit an album ");
        System.out.println(" 4. Delete an album ");
        menu = kbd.nextInt();

        while (menu != 0) {

            switch (menu) {

            case 1:
                System.out.print("Album No: ");
                kbd.nextInt(); // Bypass Bug
                int album_input = kbd.nextInt();
                System.out.println("Album Name: ");
                String name_input = kbd.nextLine();
                System.out.println("Release Year: ");
                int year_input = kbd.nextInt();
                PreparedStatement psi;
                String stIns = "INSERT INTO album(album_no, name, release_year) "
                        + "VALUES (?,?,?)";
                psi = con.prepareStatement(stIns);
                psi.setInt(1, album_input);
                psi.setString(2, name_input);
                psi.setInt(3, year_input);
                psi.executeUpdate();
                
                System.out.println("How many songs you will "
                        + "include to this album?");
                int times = kbd.nextInt();
                for (int i = 1; i<= times; i++){            
                    System.out.println("Song id: ");
                    kbd.nextInt();
                    int song_id = kbd.nextInt();
                    System.out.println("Title: ");
                    String title = kbd.nextLine();
                    System.out.println("Duration: ");
                    String duration = kbd.nextLine();
                    System.out.println("Album no: ");
                    kbd.nextInt();
                    int album_song = kbd.nextInt(album_input);


                    String stInss = "INSERT INTO songs(song_id, title, duration, album_no) VALUES(?,?,?,?)";
                    psi = con.prepareStatement(stInss);
                    psi.setInt(1, song_id);
                    psi.setString(2, title);
                    psi.setString(3, duration);
                    psi.setInt(4, album_song);
                    psi.executeUpdate();
                }
            break;

            case 2:
                rs.beforeFirst();
                //Column Header (For format guide)
                System.out.printf("%2s %10s %35s", "album_no", "name",
                        "release_year\n");
                while (rs.next()) {
                    int album_no = rs.getInt(1);
                    String name = rs.getString("name");
                    int release_year = rs.getInt(3);                        
                    System.out.printf("%-14s %-20s %10s\n", album_no,
                            name, release_year);
                }                
            break;
            case 3:
                System.out.println("Column Number: ");
                int columnNumber = kbd.nextInt();
                System.out.println("Row Number: ");
                int rowNumber = kbd.nextInt();
                System.out.println("Original Content: ");
                kbd.nextLine(); //Bypass Bug
                String originalContent = kbd.nextLine();
                System.out.println("New Content: ");
                String newContent = kbd.nextLine();
                rs.first();
                rs.updateString(columnNumber, originalContent); 
                rs.absolute(rowNumber); 
                rs.updateString(columnNumber, newContent); 
                rs.updateRow();              
            break;
            case 4:
                rs.last();
                rs.deleteRow();   
            break;
            }

        System.out.println("Album");
        System.out.println(" 1. Add an album ");
        System.out.println(" 2. Show all albums ");
        System.out.println(" 3. Edit an album ");
        System.out.println(" 4. Delete an album ");
        menu = kbd.nextInt();
        }
        System.out.println("Goodbye!");

    }

}
