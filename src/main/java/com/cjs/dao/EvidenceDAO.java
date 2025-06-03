package com.cjs.dao;

import com.cjs.model.Evidence;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EvidenceDAO {
    public void createEvidence(Evidence evidence) throws Exception {
        String sql = "INSERT INTO evidence (case_id, description, file_path, type, uploaded_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evidence.getCaseId());
            stmt.setString(2, evidence.getDescription());
            stmt.setString(3, evidence.getFilePath());
            stmt.setString(4, evidence.getType());
            stmt.setTimestamp(5, evidence.getUploadedAt() != null ? evidence.getUploadedAt() : new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public Evidence findEvidenceById(int evidenceId) throws Exception {
        String sql = "SELECT * FROM evidence WHERE evidence_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evidenceId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Evidence evidence = new Evidence();
                evidence.setEvidenceId(rs.getInt("evidence_id"));
                evidence.setCaseId(rs.getInt("case_id"));
                evidence.setDescription(rs.getString("description"));
                evidence.setFilePath(rs.getString("file_path"));
                evidence.setType(rs.getString("type"));
                evidence.setUploadedAt(rs.getTimestamp("uploaded_at"));
                return evidence;
            }
            return null;
        }
    }

    public List<Evidence> findAllEvidence() throws Exception {
        List<Evidence> evidenceList = new ArrayList<>();
        String sql = "SELECT * FROM evidence";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Evidence evidence = new Evidence();
                evidence.setEvidenceId(rs.getInt("evidence_id"));
                evidence.setCaseId(rs.getInt("case_id"));
                evidence.setDescription(rs.getString("description"));
                evidence.setFilePath(rs.getString("file_path"));
                evidence.setType(rs.getString("type"));
                evidence.setUploadedAt(rs.getTimestamp("uploaded_at"));
                evidenceList.add(evidence);
            }
        }
        return evidenceList;
    }

    public void updateEvidence(Evidence evidence) throws Exception {
        String sql = "UPDATE evidence SET case_id = ?, description = ?, file_path = ?, type = ? WHERE evidence_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evidence.getCaseId());
            stmt.setString(2, evidence.getDescription());
            stmt.setString(3, evidence.getFilePath());
            stmt.setString(4, evidence.getType());
            stmt.setInt(5, evidence.getEvidenceId());
            stmt.executeUpdate();
        }
    }

    public void deleteEvidence(int evidenceId) throws Exception {
        String sql = "DELETE FROM evidence WHERE evidence_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, evidenceId);
            stmt.executeUpdate();
        }
    }
}