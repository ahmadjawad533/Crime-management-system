package com.cjs.dao;

import com.cjs.model.Witness;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WitnessDAO {
    public void createWitness(Witness witness) throws Exception {
        String sql = "INSERT INTO witnesses (case_id, name, cnic, statement, protection_requested, verified, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, witness.getCaseId());
            stmt.setString(2, witness.getName());
            stmt.setString(3, witness.getCnic());
            stmt.setString(4, witness.getStatement());
            stmt.setBoolean(5, witness.isProtectionRequested());
            stmt.setBoolean(6, witness.isVerified());
            stmt.setTimestamp(7, witness.getCreatedAt() != null ? witness.getCreatedAt() : new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public Witness findWitnessById(int witnessId) throws Exception {
        String sql = "SELECT * FROM witnesses WHERE witness_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, witnessId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Witness witness = new Witness();
                witness.setWitnessId(rs.getInt("witness_id"));
                witness.setCaseId(rs.getInt("case_id"));
                witness.setName(rs.getString("name"));
                witness.setCnic(rs.getString("cnic"));
                witness.setStatement(rs.getString("statement"));
                witness.setProtectionRequested(rs.getBoolean("protection_requested"));
                witness.setVerified(rs.getBoolean("verified"));
                witness.setCreatedAt(rs.getTimestamp("created_at"));
                return witness;
            }
            return null;
        }
    }

    public List<Witness> findAllWitnesses() throws Exception {
        List<Witness> witnesses = new ArrayList<>();
        String sql = "SELECT * FROM witnesses";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Witness witness = new Witness();
                witness.setWitnessId(rs.getInt("witness_id"));
                witness.setCaseId(rs.getInt("case_id"));
                witness.setName(rs.getString("name"));
                witness.setCnic(rs.getString("cnic"));
                witness.setStatement(rs.getString("statement"));
                witness.setProtectionRequested(rs.getBoolean("protection_requested"));
                witness.setVerified(rs.getBoolean("verified"));
                witness.setCreatedAt(rs.getTimestamp("created_at"));
                witnesses.add(witness);
            }
        }
        return witnesses;
    }

    public void updateWitness(Witness witness) throws Exception {
        String sql = "UPDATE witnesses SET case_id = ?, name = ?, cnic = ?, statement = ?, protection_requested = ?, verified = ? WHERE witness_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, witness.getCaseId());
            stmt.setString(2, witness.getName());
            stmt.setString(3, witness.getCnic());
            stmt.setString(4, witness.getStatement());
            stmt.setBoolean(5, witness.isProtectionRequested());
            stmt.setBoolean(6, witness.isVerified());
            stmt.setInt(7, witness.getWitnessId());
            stmt.executeUpdate();
        }
    }

    public void deleteWitness(int witnessId) throws Exception {
        String sql = "DELETE FROM witnesses WHERE witness_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, witnessId);
            stmt.executeUpdate();
        }
    }
}