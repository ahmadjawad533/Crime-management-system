package com.cjs.dao;

import com.cjs.model.Case;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CaseDAO {
    public void createCase(Case caseObj) throws Exception {
        String sql = "INSERT INTO cases (fir_number, police_officer_id, judge_id, lawyer_id, status, crime_type, incident_date, location, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caseObj.getFirNumber());
            stmt.setObject(2, caseObj.getPoliceOfficerId() == 0 ? null : caseObj.getPoliceOfficerId());
            stmt.setObject(3, caseObj.getJudgeId() == 0 ? null : caseObj.getJudgeId());
            stmt.setObject(4, caseObj.getLawyerId() == 0 ? null : caseObj.getLawyerId());
            stmt.setString(5, caseObj.getStatus());
            stmt.setString(6, caseObj.getCrimeType());
            stmt.setDate(7, caseObj.getIncidentDate());
            stmt.setString(8, caseObj.getLocation());
            stmt.setTimestamp(9, caseObj.getCreatedAt() != null ? caseObj.getCreatedAt() : new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public Case findCaseById(int caseId) throws Exception {
        String sql = "SELECT * FROM cases WHERE case_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, caseId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Case caseObj = new Case();
                caseObj.setCaseId(rs.getInt("case_id"));
                caseObj.setFirNumber(rs.getString("fir_number"));
                caseObj.setPoliceOfficerId(rs.getInt("police_officer_id"));
                caseObj.setJudgeId(rs.getInt("judge_id"));
                caseObj.setLawyerId(rs.getInt("lawyer_id"));
                caseObj.setStatus(rs.getString("status"));
                caseObj.setCrimeType(rs.getString("crime_type"));
                caseObj.setIncidentDate(rs.getDate("incident_date"));
                caseObj.setLocation(rs.getString("location"));
                caseObj.setCreatedAt(rs.getTimestamp("created_at"));
                return caseObj;
            }
            return null;
        }
    }

    public List<Case> findAllCases() throws Exception {
        List<Case> cases = new ArrayList<>();
        String sql = "SELECT * FROM cases";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Case caseObj = new Case();
                caseObj.setCaseId(rs.getInt("case_id"));
                caseObj.setFirNumber(rs.getString("fir_number"));
                caseObj.setPoliceOfficerId(rs.getInt("police_officer_id"));
                caseObj.setJudgeId(rs.getInt("judge_id"));
                caseObj.setLawyerId(rs.getInt("lawyer_id"));
                caseObj.setStatus(rs.getString("status"));
                caseObj.setCrimeType(rs.getString("crime_type"));
                caseObj.setIncidentDate(rs.getDate("incident_date"));
                caseObj.setLocation(rs.getString("location"));
                caseObj.setCreatedAt(rs.getTimestamp("created_at"));
                cases.add(caseObj);
            }
        }
        return cases;
    }

    public void updateCase(Case caseObj) throws Exception {
        String sql = "UPDATE cases SET fir_number = ?, police_officer_id = ?, judge_id = ?, lawyer_id = ?, status = ?, crime_type = ?, incident_date = ?, location = ? WHERE case_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caseObj.getFirNumber());
            stmt.setObject(2, caseObj.getPoliceOfficerId() == 0 ? null : caseObj.getPoliceOfficerId());
            stmt.setObject(3, caseObj.getJudgeId() == 0 ? null : caseObj.getJudgeId());
            stmt.setObject(4, caseObj.getLawyerId() == 0 ? null : caseObj.getLawyerId());
            stmt.setString(5, caseObj.getStatus());
            stmt.setString(6, caseObj.getCrimeType());
            stmt.setDate(7, caseObj.getIncidentDate());
            stmt.setString(8, caseObj.getLocation());
            stmt.setInt(9, caseObj.getCaseId());
            stmt.executeUpdate();
        }
    }

    public void deleteCase(int caseId) throws Exception {
        String sql = "DELETE FROM cases WHERE case_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, caseId);
            stmt.executeUpdate();
        }
    }
}