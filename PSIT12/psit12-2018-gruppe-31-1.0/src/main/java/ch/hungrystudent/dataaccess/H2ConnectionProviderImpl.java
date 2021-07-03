package ch.hungrystudent.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class H2ConnectionProviderImpl implements ConnectionProvider {

    private static H2ConnectionProviderImpl instance;
    private Connection connection;

    private static final String USERNAME = "hungrystudent";
    private static final String PASSWORD = "hungrystudent";

    private static final Logger LOGGER = Logger.getLogger(H2ConnectionProviderImpl.class.getName());
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";

    private H2ConnectionProviderImpl() {

        try {
            loadDriver();
            establishDBConnection();
            setUpDatabase();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Couldn't connect to the database", e);
        }
    }

    public static H2ConnectionProviderImpl getInstance() {

        if(instance == null) {
            instance = new H2ConnectionProviderImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {

        try {
            if(connection == null || connection.isClosed()) {

                try {
                    establishDBConnection();
                } catch (SQLException e) {
                    LOGGER.log(Level.SEVERE, "Connection could not be established with H2.", e);
                    return null;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection could not be closed correctly.", e);
        }
        return connection;
    }

    private void loadDriver() {

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Driver " + DB_DRIVER + " not found.", e);
        }
    }

    private void establishDBConnection() throws SQLException {

        try {
            connection = DriverManager.getConnection(DB_CONNECTION, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection could not be established.", e);
            throw e;
        }
    }

    private void setUpDatabase() {

        connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            // removes tables
            statement.execute("DROP TABLE IF EXISTS Created;");
            statement.execute("DROP TABLE IF EXISTS Favourites;");
            statement.execute("DROP TABLE IF EXISTS Review;");
            statement.execute("DROP TABLE IF EXISTS Marker;");
            statement.execute("DROP TABLE IF EXISTS User;");

            // create tables
            statement.execute("CREATE TABLE User (" +
                    "UserID int NOT NULL AUTO_INCREMENT," +
                    "Username varchar(50) NOT NULL," +
                    "Email varchar(50)," +
                    "Password varchar(1024) NOT NULL," +
                    "CONSTRAINT PK_User PRIMARY KEY (UserID)" +
                    ");");

            statement.execute("CREATE TABLE Marker (" +
                    "ID int NOT NULL AUTO_INCREMENT," +
                    "Name varchar(50) NOT NULL," +
                    "Address varchar(50) NOT NULL," +
                    "Website varchar(50)," +
                    "Description varchar(500)," +
                    "OpeningHours varchar(100) NOT NULL," +
                    "Kitchen varchar(50)," +
                    "Price varchar(20) NOT NULL," +
                    "Vegetarian boolean NOT NULL," +
                    "TakeAway boolean NOT NULL," +
                    "Location varchar(50) NOT NULL," +
                    "Image blob," +
                    "CONSTRAINT PK_Marker PRIMARY KEY (ID)" +
                    ");");

            statement.execute("CREATE TABLE Created (" +
                    "UserID int NOT NULL," +
                    "ID int NOT NULL," +
                    "CONSTRAINT PK_Created PRIMARY KEY (UserID, ID)," +
                    "FOREIGN KEY (UserID) REFERENCES User (UserID)," +
                    "FOREIGN KEY (ID) REFERENCES Marker (ID)" +
                    ");");

            statement.execute("CREATE TABLE Favourites (" +
                    "UserID int NOT NULL," +
                    "ID int NOT NULL," +
                    "CONSTRAINT PK_Favourites PRIMARY KEY (UserID, ID)," +
                    "FOREIGN KEY (UserID) REFERENCES User (UserID)," +
                    "FOREIGN KEY (ID) REFERENCES Marker (ID)" +
                    ");");

            statement.execute("CREATE TABLE Review (" +
                    "UserID INT NOT NULL," +
                    "ID INT NOT NULL," +
                    "ReviewText VARCHAR(100) NOT NULL," +
                    "CONSTRAINT PK_Review PRIMARY KEY (UserID, ID, ReviewText)," +
                    "FOREIGN KEY (UserID) REFERENCES User (UserID)," +
                    "FOREIGN KEY (ID) REFERENCES Marker (ID)" +
                    ");");

            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"Couldn't create tables", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING,"Couldn't close connection to database", e);
            }
        }
    }

    /**
     * Clears every entry from every table
     */
    public void cleanUpDatabase() {

        connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute("DELETE FROM Created;");
            statement.execute("DELETE FROM Favourites;");
            statement.execute("DELETE FROM Marker;");
            statement.execute("DELETE FROM User;");
            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Couldn't delete entries from tables");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING,"Couldn't close connection to database", e);
            }
        }
        setUpDatabase();
    }

    /**
     * Loads loads a single user so markers can be created with it's id
     */
    public void createSingleUser() {

        connection = getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO User (UserID, Username, Email, Password) VALUES ('1','mustermann','mustermann@testing.ch','abc123');");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"Couldn't create user");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING,"Couldn't close connection to database", e);
            }
        }
    }
}
