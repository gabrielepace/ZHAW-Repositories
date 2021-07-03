import java.sql.*;
import java.io.*;

public class TestJdbcConnection {

    public static void main(String args[]) 
    throws SQLException, IOException 
    {
        String dbUser = prompt("User: ");
        String dbPass = prompt("Passwort: ");
        String dbURL = "jdbc:postgresql://dublin.zhaw.ch/" + dbUser;

        try (Connection con = DriverManager
                .getConnection(dbURL, dbUser, dbPass)) {
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery("select * from picture");
            int numCols = rset.getMetaData().getColumnCount();
            PrintWriter out = new PrintWriter(System.out, true);
            for (int i = 1; i <= numCols; i++) {
                out.print(rset.getMetaData().getColumnLabel(i) + "\t");
            }
            out.println();
            while (rset.next()) {
                for (int i = 1; i <= numCols; i++) {
                    out.print(rset.getObject(i) + "\t");
                }
                out.println();
            }
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.exit(-1);
        }
    }

    // prompt function -- to read input string
    static String prompt(String prompt) {
        try {
            StringBuffer buffer = new StringBuffer();
            System.out.print(prompt);
            System.out.flush();
            InputStreamReader in = new InputStreamReader(System.in);
            int c = in.read();
            while (c != '\n' && c != -1) {
                buffer.append((char) c);
                c = in.read();
            }
            return buffer.toString().trim();
        } catch (IOException e) {
            return "";
        }
    }
}
