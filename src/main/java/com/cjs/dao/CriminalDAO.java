package com.cjs.dao;

import com.cjs.model.Criminal;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CriminalDAO {
    public void createCriminal(Criminal criminal) throws Exception {
        String sql = "INSERT INTO criminals (case_id, name, cnic, address, charges, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, criminal.getCaseId());
            stmt.setString(2, criminal.getName());
            stmt.setString(3, criminal.getCnic());
            stmt.setString(4, criminal.getAddress());
            stmt.setString(5, criminal.getCharges());
            stmt.setTimestamp(6, criminal.getCreatedAt() != null ? criminal.getCreatedAt() : new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public Criminal findCriminalById(int criminalId) throws Exception {
        String sql = "SELECT * FROM criminals WHERE criminal_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, criminalId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Criminal criminal = new Criminal();
                criminal.setCriminalId(rs.getInt("criminal_id"));
                criminal.setCaseId(rs.getInt("case_id"));
                criminal.setName(rs.getString("name"));
                criminal.setCnic(rs.getString("cnic"));
                criminal.setAddress(rs.getString("address"));
                criminal.setCharges(rs.getString("charges"));
                criminal.setCreatedAt(rs.getTimestamp("created_at"));
                return criminal;
            }
            return null;
        }
    }

    public List<Criminal> findAllCriminals() throws Exception {
        List<Criminal> criminals = new ArrayList<>();
        String sql = "SELECT * FROM criminals";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Criminal criminal = new Criminal();
                criminal.setCriminalId(rs.getInt("criminal_id"));
                criminal.setCaseId(rs.getInt("case_id"));
                criminal.setName(rs.getString("name"));
                criminal.setCnic(rs.getString("cnic"));
                criminal.setAddress(rs.getString("address"));
                criminal.setCharges(rs.getString("charges"));
                criminal.setCreatedAt(rs.getTimestamp("created_at"));
                criminals.add(criminal);
            }
        }
        return criminals;
    }

    public void updateCriminal(Criminal criminal) throws Exception {
        String sql = "UPDATE criminals SET case_id = ?, name = ?, cnic = ?, address = ?, charges = ? WHERE criminal_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, criminal.getCaseId());
            stmt.setString(2, criminal.getName());
            stmt.setString(3, criminal.getCnic());
            stmt.setString(4, criminal.getAddress());
            stmt.setString(5, criminal.getCharges());
            stmt.setInt(6, criminal.getCriminalId());
            stmt.executeUpdate();
        }
    }

    public void deleteCriminal(int criminalId) throws Exception {
        String sql = "DELETE FROM criminals WHERE criminal_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, criminalId);
            stmt.executeUpdate();
        }
    }
}