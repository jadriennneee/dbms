/*
 * This is will serve as a code(CRUD) for the Artist table 
 */
package artistscrud;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Rizalde Jr. A. Velasco
 */
public class ArtistsCRUD {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner kbd = new Scanner(System.in);
        Class.forName("com.mysql.jdbc.Driver");
        
        //Set-up the Connection to the Database
        String conStr = "jdbc:mysql://localhost:3306/dbms?user=root&password=";
        Connection con = DriverManager.getConnection(conStr);
        System.out.println("Connection Done");
        String stSel = "SELECT * FROM artist";
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery(stSel);
        
        System.out.println("Select a Command:");
        System.out.println("1 - Show Table\n2 - Insert new Entry\n"
                + "3 - Delete Last Entry\n4 - Update table content");
        int option = kbd.nextInt();
        
        if (option == 1) {
            rs.beforeFirst();
            //Column Header (For format guide)
            System.out.printf("%2s %10s %21s %26s %20s", "Artist ID", "Last Name",
                    "First Name", "Screen Name", "Gender\n");
            while (rs.next()) {
                int artistID = rs.getInt(1);
                String lastName = rs.getString("lastName");
                String firstName = rs.getString("firstName");
                String screenName = rs.getString("screen_name");
                String gender = rs.getString("gender");
                System.out.printf("%-10d %-20s %-25s %-15s %10s\n", artistID,
                        lastName, firstName, screenName, gender);
                kbd.close();
            }
        } else if (option == 2) { 
            //Insert New Entry
            System.out.println("Last Name: ");
            kbd.nextLine(); // Bypass Bug
            String lastName = kbd.nextLine();
            System.out.println("First Name: ");
            String firstName = kbd.nextLine();
            System.out.println("Screen Name: ");
            String screenName = kbd.nextLine();
            System.out.println("Gender: ");
            String gender = kbd.nextLine();
            PreparedStatement psi;
            String stIns = "INSERT INTO artist(lastname, firstname, screen_name, gender) VALUES (?,?,?,?)";
            psi = con.prepareStatement(stIns);
            psi.setString(1, lastName);
            psi.setString(2, firstName);
            psi.setString(3, screenName);
            psi.setString(4, gender);
            psi.executeUpdate();
        } else if (option == 3) {
            //Delete Last Row Entry
            rs.last();
            rs.deleteRow();
        } else if (option == 4) {
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
            rs.updateString(columnNumber, originalContent); //Select the row
            rs.absolute(rowNumber); //Change the number to the corresponding row
            rs.updateString(columnNumber, newContent); //Change the selected row
            rs.updateRow();
        }
        
        
        
        
        
    }
    
}
