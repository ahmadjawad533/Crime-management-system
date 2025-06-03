package com.cjs.dao;

import com.cjs.model.Notification;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {
    public void createNotification(Notification notification) throws Exception {
        String sql = "INSERT INTO notifications (user_id, message, type, sent_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notification.getUserId());
            stmt.setString(2, notification.getMessage());
            stmt.setString(3, notification.getType());
            stmt.setTimestamp(4, notification.getSentAt() != null ? notification.getSentAt() : new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public Notification findNotificationById(int notificationId) throws Exception {
        String sql = "SELECT * FROM notifications WHERE notification_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notificationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserId(rs.getInt("user_id"));
                notification.setMessage(rs.getString("message"));
                notification.setType(rs.getString("type"));
                notification.setSentAt(rs.getTimestamp("sent_at"));
                return notification;
            }
            return null;
        }
    }

    public List<Notification> findAllNotifications() throws Exception {
        List<Notification> notifications = new ArrayList<>();
        String sql = "SELECT * FROM notifications";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Notification notification = new Notification();
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserId(rs.getInt("user_id"));
                notification.setMessage(rs.getString("message"));
                notification.setType(rs.getString("type"));
                notification.setSentAt(rs.getTimestamp("sent_at"));
                notifications.add(notification);
            }
        }
        return notifications;
    }

    public void updateNotification(Notification notification) throws Exception {
        String sql = "UPDATE notifications SET user_id = ?, message = ?, type = ? WHERE notification_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notification.getUserId());
            stmt.setString(2, notification.getMessage());
            stmt.setString(3, notification.getType());
            stmt.setInt(4, notification.getNotificationId());
            stmt.executeUpdate();
        }
    }

    public void deleteNotification(int notificationId) throws Exception {
        String sql = "DELETE FROM notifications WHERE notification_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, notificationId);
            stmt.executeUpdate();
        }
    }
}