package edu.acme.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseSourceConnectionPooling {

    private static final String DB_CONNECTION_URL_FILE_MODE = "jdbc:h2:~/university";
    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:university";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "";

    private static HikariDataSource ds;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(DB_CONNECTION_URL_MEMORY_MODE);
        config.setUsername(DB_USERNAME);
        config.setPassword(DB_PASSWORD);
        config.addDataSourceProperty("maximumPoolSize", "10");
        config.addDataSourceProperty("minimumIdle", "2");
        config.addDataSourceProperty("idleTimeout", "10000");
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DatabaseSourceConnectionPooling(){}
}
