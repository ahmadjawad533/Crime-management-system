package com.cjs.service;

import com.cjs.model.User;
import com.cjs.util.DatabaseConnection;
import com.cjs.util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {
    public String login(String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT role, password_hash FROM users WHERE username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            Logger.info("User found by username: " + username);

            if (rs.next()) {
                String storedPassword = rs.getString("password_hash");
                String hashedPassword = hashPassword(password);

                Logger.info("Comparing passwords for user '" + username + "': entered=" + hashedPassword + ", stored=" + storedPassword);

                if (storedPassword.equals(hashedPassword)) {
                    String role = rs.getString("role");
                    Logger.info("Login successful: " + username + ", Role: " + role);
                    return role;
                } else {
                    Logger.info("Login failed: Invalid password for user '" + username + "'");
                    return null;
                }
            } else {
                Logger.info("Login failed: Username '" + username + "' not found");
                return null;
            }
        } catch (SQLException e) {
            Logger.error("Database error during login for username '" + username + "': " + e.getMessage());
            throw e;
        } finally {
            DatabaseConnection.close(conn, stmt, rs);
        }
    }

    public User getUserByUsername(String username) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT username, password_hash, role, name, designation FROM users WHERE username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setDesignation(rs.getString("designation"));
                Logger.info("Fetched user details for username: " + username);
                return user;
            } else {
                Logger.info("User not found for username: " + username);
                return null;
            }
        } catch (SQLException e) {
            Logger.error("Database error while fetching user '" + username + "': " + e.getMessage());
            throw e;
        } finally {
            DatabaseConnection.close(conn, stmt, rs);
        }
    }

    public void register(String username, String password, String role, String name, String designation) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO users (username, password_hash, role, name, designation) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, hashPassword(password));
            stmt.setString(3, role);
            stmt.setString(4, name);
            stmt.setString(5, designation);
            stmt.executeUpdate();
            Logger.info("User registered: " + username + ", Role: " + role);
        } catch (SQLException e) {
            Logger.error("Database error during registration for username '" + username + "': " + e.getMessage());
            throw e;
        } finally {
            DatabaseConnection.close(conn, stmt, null);
        }
    }

    private String hashPassword(String password) {
        return "hashed_" + password; // Placeholder for actual hashing
    }
}