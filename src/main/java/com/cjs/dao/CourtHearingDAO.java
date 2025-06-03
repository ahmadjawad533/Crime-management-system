package com.cjs.dao;

import com.cjs.model.CourtHearing;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CourtHearingDAO {
    public void createCourtHearing(CourtHearing hearing) throws Exception {
        String sql = "INSERT INTO court_hearings (case_id, hearing_date, court_room, details, created_at) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hearing.getCaseId());
            stmt.setDate(2, hearing.getHearingDate());
            stmt.setString(3, hearing.getCourtRoom());
            stmt.setString(4, hearing.getDetails());
            stmt.setTimestamp(5, hearing.getCreatedAt() != null ? hearing.getCreatedAt() : new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public CourtHearing findCourtHearingById(int hearingId) throws Exception {
        String sql = "SELECT * FROM court_hearings WHERE hearing_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hearingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CourtHearing hearing = new CourtHearing();
                hearing.setHearingId(rs.getInt("hearing_id"));
                hearing.setCaseId(rs.getInt("case_id"));
                hearing.setHearingDate(rs.getDate("hearing_date"));
                hearing.setCourtRoom(rs.getString("court_room"));
                hearing.setDetails(rs.getString("details"));
                hearing.setCreatedAt(rs.getTimestamp("created_at"));
                return hearing;
            }
            return null;
        }
    }

    public List<CourtHearing> findAllCourtHearings() throws Exception {
        List<CourtHearing> hearings = new ArrayList<>();
        String sql = "SELECT * FROM court_hearings";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                CourtHearing hearing = new CourtHearing();
                hearing.setHearingId(rs.getInt("hearing_id"));
                hearing.setCaseId(rs.getInt("case_id"));
                hearing.setHearingDate(rs.getDate("hearing_date"));
                hearing.setCourtRoom(rs.getString("court_room"));
                hearing.setDetails(rs.getString("details"));
                hearing.setCreatedAt(rs.getTimestamp("created_at"));
                hearings.add(hearing);
            }
        }
        return hearings;
    }

    public void updateCourtHearing(CourtHearing hearing) throws Exception {
        String sql = "UPDATE court_hearings SET case_id = ?, hearing_date = ?, court_room = ?, details = ? WHERE hearing_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hearing.getCaseId());
            stmt.setDate(2, hearing.getHearingDate());
            stmt.setString(3, hearing.getCourtRoom());
            stmt.setString(4, hearing.getDetails());
            stmt.setInt(5, hearing.getHearingId());
            stmt.executeUpdate();
        }
    }

    public void deleteCourtHearing(int hearingId) throws Exception {
        String sql = "DELETE FROM court_hearings WHERE hearing_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, hearingId);
            stmt.executeUpdate();
        }
    }
}