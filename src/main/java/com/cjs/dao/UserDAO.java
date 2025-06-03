package com.cjs.dao;

import com.cjs.model.User;
import com.cjs.util.DatabaseConnection;
import com.cjs.util.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password_hash, role, name, designation, contact, email, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getDesignation()); // 5th parameter is designation
            stmt.setString(6, user.getContact());     // 6th parameter is contact
            stmt.setString(7, user.getEmail());       // 7th parameter is email
            stmt.setTimestamp(8, user.getCreatedAt() != null ? user.getCreatedAt() : new Timestamp(System.currentTimeMillis())); // 8th parameter is created_at
            stmt.executeUpdate();
            Logger.info("User created in database: " + user.getUsername());
        } catch (SQLException e) {
            Logger.info("Failed to create user '" + user.getUsername() + "': " + e.getMessage());
            throw new SQLException("Error creating user: " + e.getMessage(), e);
        }
    }

    public User findUserById(int userId) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPasswordHash(rs.getString("password_hash"));
                    user.setRole(rs.getString("role"));
                    user.setName(rs.getString("name"));
                    user.setDesignation(rs.getString("designation"));
                    user.setContact(rs.getString("contact"));
                    user.setEmail(rs.getString("email"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    Logger.info("User found by ID: " + userId);
                    return user;
                }
            }
            Logger.info("User not found by ID: " + userId);
            return null;
        } catch (SQLException e) {
            Logger.info("Error finding user by ID " + userId + ": " + e.getMessage());
            throw new SQLException("Error finding user by ID: " + e.getMessage(), e);
        }
    }

    public User findUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPasswordHash(rs.getString("password_hash"));
                    user.setRole(rs.getString("role"));
                    user.setName(rs.getString("name"));
                    user.setDesignation(rs.getString("designation"));
                    user.setContact(rs.getString("contact"));
                    user.setEmail(rs.getString("email"));
                    user.setCreatedAt(rs.getTimestamp("created_at"));
                    Logger.info("User found by username: " + username);
                    return user;
                }
            }
            Logger.info("User not found by username: " + username);
            return null;
        } catch (SQLException e) {
            Logger.info("Error finding user by username '" + username + "': " + e.getMessage());
            throw new SQLException("Error finding user by username: " + e.getMessage(), e);
        }
    }

    public List<User> findAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setDesignation(rs.getString("designation"));
                user.setContact(rs.getString("contact"));
                user.setEmail(rs.getString("email"));
                user.setCreatedAt(rs.getTimestamp("created_at"));
                users.add(user);
            }
            Logger.info("Retrieved " + users.size() + " users from database");
            return users;
        } catch (SQLException e) {
            Logger.info("Error retrieving all users: " + e.getMessage());
            throw new SQLException("Error retrieving all users: " + e.getMessage(), e);
        }
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password_hash = ?, role = ?, name = ?, designation = ?, contact = ?, email = ? WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPasswordHash());
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getName());
            stmt.setString(5, user.getDesignation());
            stmt.setString(6, user.getContact());
            stmt.setString(7, user.getEmail());
            stmt.setInt(8, user.getUserId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                Logger.info("User updated: " + user.getUsername());
            } else {
                Logger.info("User update failed: User ID " + user.getUserId() + " not found");
                throw new SQLException("User with ID " + user.getUserId() + " not found");
            }
        } catch (SQLException e) {
            Logger.info("Error updating user '" + user.getUsername() + "': " + e.getMessage());
            throw new SQLException("Error updating user: " + e.getMessage(), e);
        }
    }

    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                Logger.info("User deleted: ID " + userId);
            } else {
                Logger.info("User deletion failed: User ID " + userId + " not found");
                throw new SQLException("User with ID " + userId + " not found");
            }
        } catch (SQLException e) {
            Logger.info("Error deleting user ID " + userId + ": " + e.getMessage());
            throw new SQLException("Error deleting user: " + e.getMessage(), e);
        }
    }
}