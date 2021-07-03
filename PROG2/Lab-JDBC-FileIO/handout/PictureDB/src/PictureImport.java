import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sql.DataSource;

import util.SimpleDataSource;
import model.Picture;

/* This test-application reads some picture data from terminal, 
 * saves it to the DB, read it from the DB and prints the result
 */

public class PictureImport {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static PrintWriter out = new PrintWriter(System.out, true);
    private DataSource ds;

    public static void main (String [] args) {
        String dbUser = prompt("Username: ");
        String dbPasswd = prompt("Password: ");
        String dbURL = "jdbc:postgresql://dublin.zhaw.ch/" + dbUser;
        PictureImport importer = new PictureImport(dbURL, dbUser, dbPasswd);
        Picture pict = importer.createPicture();
        importer.addPicture(pict);
        Picture pict2 = importer.getPicture(pict.getId());
        if (pict2 != null) {
            out.println("The following pictures has been saved: ");
            out.println(pict2);
        } else {
            out.println("Picture with id=" + pict.getId() + " not found.");
        }
        /* Exercise 4) */
        // String fileName = prompt("File: ");
        // File file = new File(fileName);
        // importer.importFile(file);
    }
    
    PictureImport(String dbUrl, String dbUser, String dbPasswd) {
        this.ds = new SimpleDataSource(dbUrl, dbUser, dbPasswd);
    }

    public Picture createPicture() {
        // asks the values for the objects
        out.println("** Create a new picture **");
        String urlString = prompt("Picture URL: ");
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        String title = prompt("Picture title: ");
        String comment = prompt("Picture comment: ");

        
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        Date date = new Date(); // now
        try {
            date = df.parse(prompt("Picture time ("+DATE_FORMAT+"): "));
        } catch (ParseException e) {
            out.println("Unknown date format. Using "+date.toString());
        }
        
        float longitude = 0.0f;
        try {
            longitude = Float.parseFloat(prompt("Picture position longitude: "));
        } catch (NumberFormatException e) {
            out.println("Unknown number format. Using " + longitude);
        }
        float latitude = 0.0f;
        try {
            latitude = Float.parseFloat(prompt("Picture position latitude: "));
        } catch (NumberFormatException e) {
            out.println("Unknown number format. Using " + latitude);
        }
        float altitude = 0.0f;
        try {
            altitude = Float.parseFloat(prompt("Picture position altitude: "));
        } catch (NumberFormatException e) {
            out.println("Unknown number format. Using " + altitude);
        }
        return new Picture(url, new Date(), title, comment, longitude, latitude, altitude);
    }
    
    public void addPicture (Picture picture) {
        // you can get a connection to the DB from the dataSource using ds.getConnection()
        try (Connection conn = ds.getConnection()) {
            // TODO saves the picture to the db
            
        } catch (SQLException e) {
            throw new RuntimeException("DB operation failed: insert(picture): " + picture, e);
        }
    }
    
    public Picture getPicture(int id) {
        // TODO Implement method
        return null;
    }

    public void importFile(File file) {
        // TODO Implement method
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

