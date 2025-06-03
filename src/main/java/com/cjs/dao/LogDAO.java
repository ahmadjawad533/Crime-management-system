package com.cjs.dao;

import com.cjs.model.Log;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    public void createLog(Log log) throws Exception {
        String sql = "INSERT INTO logs (user_id, action, action_time) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, log.getUserId());
            stmt.setString(2, log.getAction());
            stmt.setTimestamp(3, log.getActionTime() != null ? log.getActionTime() : new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public Log findLogById(int logId) throws Exception {
        String sql = "SELECT * FROM logs WHERE log_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, logId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Log log = new Log();
                log.setLogId(rs.getInt("log_id"));
                log.setUserId(rs.getInt("user_id"));
                log.setAction(rs.getString("action"));
                log.setActionTime(rs.getTimestamp("action_time"));
                return log;
            }
            return null;
        }
    }

    public List<Log> findAllLogs() throws Exception {
        List<Log> logs = new ArrayList<>();
        String sql = "SELECT * FROM logs";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Log log = new Log();
                log.setLogId(rs.getInt("log_id"));
                log.setUserId(rs.getInt("user_id"));
                log.setAction(rs.getString("action"));
                log.setActionTime(rs.getTimestamp("action_time"));
                logs.add(log);
            }
        }
        return logs;
    }

    public void updateLog(Log log) throws Exception {
        String sql = "UPDATE logs SET user_id = ?, action = ? WHERE log_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, log.getUserId());
            stmt.setString(2, log.getAction());
            stmt.setInt(3, log.getLogId());
            stmt.executeUpdate();
        }
    }

    public void deleteLog(int logId) throws Exception {
        String sql = "DELETE FROM logs WHERE log_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, logId);
            stmt.executeUpdate();
        }
    }
}