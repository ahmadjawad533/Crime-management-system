package com.cjs.service;

import com.cjs.model.Case;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaseService {

    // Create a new case
    public void createCase(Case caseObj) throws SQLException {
        String sql = "INSERT INTO cases (fir_number, police_officer_id, judge_id, lawyer_id, status, crime_type, incident_date, location) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caseObj.getFirNumber());
            stmt.setObject(2, caseObj.getPoliceOfficerId() > 0 ? caseObj.getPoliceOfficerId() : null);
            stmt.setObject(3, caseObj.getJudgeId() > 0 ? caseObj.getJudgeId() : null);
            stmt.setObject(4, caseObj.getLawyerId() > 0 ? caseObj.getLawyerId() : null);
            stmt.setString(5, caseObj.getStatus());
            stmt.setString(6, caseObj.getCrimeType());
            stmt.setObject(7, caseObj.getIncidentDate()); // Direct use of java.sql.Date
            stmt.setString(8, caseObj.getLocation());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error creating case: " + e.getMessage(), e);
        }
    }

    // Get a case by ID
    public Case getCaseById(int caseId) throws SQLException {
        String sql = "SELECT * FROM cases WHERE case_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, caseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCase(rs);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching case by ID: " + e.getMessage(), e);
        }
        return null;
    }

    // Get all cases
    public List<Case> getAllCases() throws SQLException {
        List<Case> cases = new ArrayList<>();
        String sql = "SELECT * FROM cases";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cases.add(mapResultSetToCase(rs));
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching all cases: " + e.getMessage(), e);
        }
        return cases;
    }

    // Update a case
    public void updateCase(Case caseObj) throws SQLException {
        String sql = "UPDATE cases SET fir_number = ?, police_officer_id = ?, judge_id = ?, lawyer_id = ?, status = ?, crime_type = ?, incident_date = ?, location = ? WHERE case_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, caseObj.getFirNumber());
            stmt.setObject(2, caseObj.getPoliceOfficerId() > 0 ? caseObj.getPoliceOfficerId() : null);
            stmt.setObject(3, caseObj.getJudgeId() > 0 ? caseObj.getJudgeId() : null);
            stmt.setObject(4, caseObj.getLawyerId() > 0 ? caseObj.getLawyerId() : null);
            stmt.setString(5, caseObj.getStatus());
            stmt.setString(6, caseObj.getCrimeType());
            stmt.setObject(7, caseObj.getIncidentDate()); // Direct use of java.sql.Date
            stmt.setString(8, caseObj.getLocation());
            stmt.setInt(9, caseObj.getCaseId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Case not found with ID: " + caseObj.getCaseId());
            }
        } catch (SQLException e) {
            throw new SQLException("Error updating case: " + e.getMessage(), e);
        }
    }

    // Delete a case
    public void deleteCase(int caseId) throws SQLException {
        String sql = "DELETE FROM cases WHERE case_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, caseId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Case not found with ID: " + caseId);
            }
        } catch (SQLException e) {
            throw new SQLException("Error deleting case: " + e.getMessage(), e);
        }
    }

    // Get cases by status
    public List<Case> getCasesByStatus(String status) throws SQLException {
        List<Case> cases = new ArrayList<>();
        String sql = "SELECT * FROM cases WHERE status = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cases.add(mapResultSetToCase(rs));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching cases by status: " + e.getMessage(), e);
        }
        return cases;
    }

    // Get cases by police officer ID
    public List<Case> getCasesByOfficer(int policeOfficerId) throws SQLException {
        List<Case> cases = new ArrayList<>();
        String sql = "SELECT * FROM cases WHERE police_officer_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, policeOfficerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cases.add(mapResultSetToCase(rs));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching cases by officer: " + e.getMessage(), e);
        }
        return cases;
    }

    // Get cases by crime type
    public List<Case> getCasesByCrimeType(String crimeType) throws SQLException {
        List<Case> cases = new ArrayList<>();
        String sql = "SELECT * FROM cases WHERE crime_type = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, crimeType);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    cases.add(mapResultSetToCase(rs));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error fetching cases by crime type: " + e.getMessage(), e);
        }
        return cases;
    }

    // Helper method to map ResultSet to Case object
    private Case mapResultSetToCase(ResultSet rs) throws SQLException {
        Case caseObj = new Case();
        caseObj.setCaseId(rs.getInt("case_id"));
        caseObj.setFirNumber(rs.getString("fir_number"));
        caseObj.setPoliceOfficerId(rs.getInt("police_officer_id"));
        caseObj.setJudgeId(rs.getInt("judge_id"));
        caseObj.setLawyerId(rs.getInt("lawyer_id"));
        caseObj.setStatus(rs.getString("status"));
        caseObj.setCrimeType(rs.getString("crime_type"));
        java.sql.Date incidentDate = rs.getDate("incident_date");
        caseObj.setIncidentDate(incidentDate); // Set java.sql.Date directly
        caseObj.setLocation(rs.getString("location"));
        return caseObj;
    }
}