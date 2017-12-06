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
        String choice;
        do {
            System.out.println("Album");
            System.out.println(" 1. Add an album ");
            System.out.println(" 2. Show all albums ");
            System.out.println(" 3. Edit an album ");
            System.out.println(" 4. Delete an album ");
            System.out.println(" 5. Exit ");
            System.out.print("Enter your choice:");
            menu = kbd.nextInt();
            switch (menu) {
            case 1:
                addAlbum(kbd, con);
            break;
            case 2:
                showMethod(rs);                
            break;
            case 3:
                editAlbum(kbd, rs);              
            break;
            case 4:
                deleteMethod(rs);   
            break;
            case 5:
                System.out.println("Goodbye!");
                System.exit(0);
            }
            System.out.print("Press Enter");
            kbd.nextLine();
            kbd.nextLine();
            System.out.print("Do you want to enter again? (Y/N) ");
            choice = kbd.nextLine();
        } while (!"N".equals(choice));
        System.out.println("Goodbye!");

    }

    public static void deleteMethod(ResultSet rs) throws SQLException {
        rs.last();
        rs.deleteRow();
    }

    public static void editAlbum(Scanner kbd, ResultSet rs) throws SQLException {
        System.out.print("Column Number: ");
        int columnNumber = kbd.nextInt();
        System.out.print("Row Number: ");
        int rowNumber = kbd.nextInt();
        System.out.print("Original Content: ");
        kbd.nextLine(); //Bypass Bug
        String originalContent = kbd.nextLine();
        System.out.print("New Content: ");
        String newContent = kbd.nextLine();
        rs.first();
        rs.updateString(columnNumber, originalContent);
        rs.absolute(rowNumber);
        rs.updateString(columnNumber, newContent);
        rs.updateRow();
    }

    public static void showMethod(ResultSet rs) throws SQLException {
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
    }

    public static void addAlbum(Scanner kbd, Connection con) throws SQLException {
        System.out.print("Album No: ");
        int album_input = kbd.nextInt();
        System.out.print("Album Name: ");
        String name_input = kbd.nextLine();
        name_input = kbd.nextLine();
        System.out.print("Release Year: ");
        int year_input = kbd.nextInt();
        PreparedStatement psi;
        String stIns = "INSERT INTO album(album_no, name, release_year) "
                + "VALUES (?,?,?)";
        psi = con.prepareStatement(stIns);
        psi.setInt(1, album_input);
        psi.setString(2, name_input);
        psi.setInt(3, year_input);
        psi.executeUpdate();
        
        System.out.print("How many songs you will "
                + "include to this album?:");
        int times = kbd.nextInt();
        for (int i = 1; i<= times; i++){
            System.out.print("Song id: ");
            int song_id = kbd.nextInt();
            System.out.print("Title: ");
            String title = kbd.nextLine();
            title = kbd.nextLine();
            System.out.print("Duration: ");
            String duration = kbd.nextLine();
            System.out.print("Album no: ");
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
    }
}