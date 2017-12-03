/*
 * This will serve as a code(crud) for songs
 */
package songscrud;
import java.sql.*;

/**
 *
 * @author Franchesca Derije
 */
public class SongsCrud {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
            Class.forName("com.mysqljdbc.Driver");
            
            String conStr = "jdbc:mysql://localhost:3306/dbms?user=root&password=";
            Connection con = DriverManager.getConnection(conStr);
            System.out.println("Connected");
            
            String stSel = "SELECT * FROM songs";
            
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(stSel);
            
            rs.beforeFirst();
            while (rs.next()) {
                int song_id = rs.getInt(1);
                String title = rs.getString("song title");
                
                /*System.out.println("%3d %-25s %10.2f\n", song_id, title);*/
                
            }
            rs.close();
            con.close();
    }
    
}
