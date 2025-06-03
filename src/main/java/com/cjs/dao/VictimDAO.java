package com.cjs.dao;

import com.cjs.model.Victim;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class VictimDAO {
    public void createVictim(Victim victim) throws Exception {
        String sql = "INSERT INTO victims (case_id, name, cnic, contact, statement, protection_requested, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, victim.getCaseId());
            stmt.setString(2, victim.getName());
            stmt.setString(3, victim.getCnic());
            stmt.setString(4, victim.getContact());
            stmt.setString(5, victim.getStatement());
            stmt.setBoolean(6, victim.isProtectionRequested());
            stmt.setTimestamp(7, victim.getCreatedAt() != null ? victim.getCreatedAt() : new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public Victim findVictimById(int victimId) throws Exception {
        String sql = "SELECT * FROM victims WHERE victim_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, victimId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Victim victim = new Victim();
                victim.setVictimId(rs.getInt("victim_id"));
                victim.setCaseId(rs.getInt("case_id"));
                victim.setName(rs.getString("name"));
                victim.setCnic(rs.getString("cnic"));
                victim.setContact(rs.getString("contact"));
                victim.setStatement(rs.getString("statement"));
                victim.setProtectionRequested(rs.getBoolean("protection_requested"));
                victim.setCreatedAt(rs.getTimestamp("created_at"));
                return victim;
            }
            return null;
        }
    }

    public List<Victim> findAllVictims() throws Exception {
        List<Victim> victims = new ArrayList<>();
        String sql = "SELECT * FROM victims";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Victim victim = new Victim();
                victim.setVictimId(rs.getInt("victim_id"));
                victim.setCaseId(rs.getInt("case_id"));
                victim.setName(rs.getString("name"));
                victim.setCnic(rs.getString("cnic"));
                victim.setContact(rs.getString("contact"));
                victim.setStatement(rs.getString("statement"));
                victim.setProtectionRequested(rs.getBoolean("protection_requested"));
                victim.setCreatedAt(rs.getTimestamp("created_at"));
                victims.add(victim);
            }
        }
        return victims;
    }

    public void updateVictim(Victim victim) throws Exception {
        String sql = "UPDATE victims SET case_id = ?, name = ?, cnic = ?, contact = ?, statement = ?, protection_requested = ? WHERE victim_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, victim.getCaseId());
            stmt.setString(2, victim.getName());
            stmt.setString(3, victim.getCnic());
            stmt.setString(4, victim.getContact());
            stmt.setString(5, victim.getStatement());
            stmt.setBoolean(6, victim.isProtectionRequested());
            stmt.setInt(7, victim.getVictimId());
            stmt.executeUpdate();
        }
    }

    public void deleteVictim(int victimId) throws Exception {
        String sql = "DELETE FROM victims WHERE victim_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, victimId);
            stmt.executeUpdate();
        }
    }
}