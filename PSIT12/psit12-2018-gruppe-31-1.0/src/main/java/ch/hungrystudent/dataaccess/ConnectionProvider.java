package ch.hungrystudent.dataaccess;

import java.sql.Connection;

interface ConnectionProvider {

    Connection getConnection();
}
