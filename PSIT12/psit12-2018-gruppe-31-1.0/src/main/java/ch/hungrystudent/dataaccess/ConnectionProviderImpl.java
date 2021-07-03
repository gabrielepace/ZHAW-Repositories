package ch.hungrystudent.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionProviderImpl implements ConnectionProvider {

    private static ConnectionProviderImpl instance;
    private Connection connection;

    private static final String USERNAME = "hungrystudent";
    private static final String PASSWORD = "hungrystudent";

    private static final Logger LOGGER = Logger.getLogger(ConnectionProviderImpl.class.getName());
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost/HungryStudent_DB?useUnicode=yes&characterEncoding=UTF-8";

    private ConnectionProviderImpl() {

        try {
            loadDriver();
            establishDBConnection();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Couldn't connect to the database", e);
        }
    }

    public static ConnectionProviderImpl getInstance() {

        if(instance == null) {
            instance = new ConnectionProviderImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {

        try {
            if(connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(DB_CONNECTION, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Connection could not be established with " + DB_CONNECTION + ".", e);
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
}
