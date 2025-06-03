package com.cjs.util;

import com.cjs.util.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/cjs_db?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "Jawad@12345"; // Your MySQL password

    static {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Logger.info("MySQL JDBC Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            Logger.error("Failed to load MySQL JDBC Driver: " + e.getMessage());
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Logger.info("Attempting to connect to database: " + URL);
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Logger.info("Database connection established successfully");
            return conn;
        } catch (SQLException e) {
            Logger.error("Database connection failed: " + e.getMessage());
            throw e;
        }
    }

    public static void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
                Logger.info("ResultSet closed successfully");
            } catch (SQLException e) {
                Logger.error("Error closing ResultSet: " + e.getMessage());
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
                Logger.info("PreparedStatement closed successfully");
            } catch (SQLException e) {
                Logger.error("Error closing PreparedStatement: " + e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
                Logger.info("Connection closed successfully");
            } catch (SQLException e) {
                Logger.error("Error closing Connection: " + e.getMessage());
            }
        }
    }
}