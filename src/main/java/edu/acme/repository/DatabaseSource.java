package edu.acme.repository;

import edu.acme.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSource {

    private static final Logger log = LoggerFactory.getLogger(DatabaseSource.class);

    private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:h2:~/university";
    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:university";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        Connection connection;
        try {
            connection = DriverManager.getConnection (DB_CONNECTION_URL_MEMORY_MODE, DB_USERNAME,DB_PASSWORD);
        } catch (SQLException e) {
            log.error("Connection cannot be established", e);
            throw new SQLException("Connection cannot be established");
        }
        return connection;
    }

    private DatabaseSource() {}
}
