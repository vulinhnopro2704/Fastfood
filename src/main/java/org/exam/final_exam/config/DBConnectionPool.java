package org.exam.final_exam.config;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionPool {
    private static DBConnectionPool instance;
    private BasicDataSource dataSource;

    private DBConnectionPool() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://ep-silent-cherry-a139vucf.ap-southeast-1.aws.neon.tech/neondb?user=neondb_owner&password=5EDdVRUZy7YG&sslmode=require");
        dataSource.setUsername("neondb_owner");
        dataSource.setPassword("5EDdVRUZy7YG");
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
        dataSource.setDriverClassName("org.postgresql.Driver");
    }

    public static synchronized DBConnectionPool getInstance() {
        if (instance == null) {
            instance = new DBConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}