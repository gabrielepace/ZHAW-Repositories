package util;

import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class SimpleDataSource implements DataSource {
    private PrintWriter out = new PrintWriter(System.out);
    private int timeout = 10;
    private String dbUrl, dbUser, dbPasswd;
    private Connection conn;

    // Disable default constructor
    @SuppressWarnings("unused")
    private SimpleDataSource() {
    }

    /**
     * Obtain a connection to a DB using the provided arguments to call
     * 
     * @param dbUrl String - The URL of the database (e.g.
     *                       jdbc:postgresql://example.com:5432/mydb)
     * @param dbUser String - Username
     * @param dbPasswd String - Password
     * @see DriverManager#getConnection(String,String,String)
     * @see javax.sql.DataSource
     */
    public SimpleDataSource(String dbUrl, String dbUser, String dbPasswd) {
        this.dbUrl = dbUrl;
        try {
            getConnection(dbUser, dbPasswd); //test if access to db works
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public Connection getConnection() throws SQLException {
        if ((conn == null) || (conn.isClosed())) {
            conn = getConnection(dbUser, dbPasswd);
        }
        return conn;
    }

    public Connection getConnection(String dbUser, String dbPasswd)
            throws SQLException {
        this.dbUser = dbUser;
        this.dbPasswd = dbPasswd;
        this.conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
        return conn;
    }

    /* The following methods are dummy methods to fulfill the 
     * DataSource interface.
     */
    
    public int getLoginTimeout() {
        return timeout;
    }

    public PrintWriter getLogWriter() {
        return out;
    }

    public void setLoginTimeout(int seconds) {
        timeout = seconds;
    }

    public void setLogWriter(PrintWriter out) {
        this.out = out;
    }

    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        return false;
    }

    public <T> T unwrap(final Class<T> iface) throws SQLException {
        throw new SQLException(
                "unwrap method not supported by this implementation");
    }

    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException(
                "Not supporting java.util.logging.Logger");
    }
}
