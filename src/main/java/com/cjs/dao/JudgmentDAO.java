package com.cjs.dao;

import com.cjs.model.Judgment;
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class JudgmentDAO {
    public void createJudgment(Judgment judgment) throws Exception {
        String sql = "INSERT INTO judgments (case_id, verdict, act_section, punishment, fine, judgment_date, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, judgment.getCaseId());
            stmt.setString(2, judgment.getVerdict());
            stmt.setString(3, judgment.getActSection());
            stmt.setString(4, judgment.getPunishment());
            stmt.setInt(5, judgment.getFine());
            stmt.setDate(6, judgment.getJudgmentDate());
            stmt.setTimestamp(7, judgment.getCreatedAt() != null ? judgment.getCreatedAt() : new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public Judgment findJudgmentById(int judgmentId) throws Exception {
        String sql = "SELECT * FROM judgments WHERE judgment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, judgmentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Judgment judgment = new Judgment();
                judgment.setJudgmentId(rs.getInt("judgment_id"));
                judgment.setCaseId(rs.getInt("case_id"));
                judgment.setVerdict(rs.getString("verdict"));
                judgment.setActSection(rs.getString("act_section"));
                judgment.setPunishment(rs.getString("punishment"));
                judgment.setFine(rs.getInt("fine"));
                judgment.setJudgmentDate(rs.getDate("judgment_date"));
                judgment.setCreatedAt(rs.getTimestamp("created_at"));
                return judgment;
            }
            return null;
        }
    }

    public List<Judgment> findAllJudgments() throws Exception {
        List<Judgment> judgments = new ArrayList<>();
        String sql = "SELECT * FROM judgments";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Judgment judgment = new Judgment();
                judgment.setJudgmentId(rs.getInt("judgment_id"));
                judgment.setCaseId(rs.getInt("case_id"));
                judgment.setVerdict(rs.getString("verdict"));
                judgment.setActSection(rs.getString("act_section"));
                judgment.setPunishment(rs.getString("punishment"));
                judgment.setFine(rs.getInt("fine"));
                judgment.setJudgmentDate(rs.getDate("judgment_date"));
                judgment.setCreatedAt(rs.getTimestamp("created_at"));
                judgments.add(judgment);
            }
        }
        return judgments;
    }

    public void updateJudgment(Judgment judgment) throws Exception {
        String sql = "UPDATE judgments SET case_id = ?, verdict = ?, act_section = ?, punishment = ?, fine = ?, judgment_date = ? WHERE judgment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, judgment.getCaseId());
            stmt.setString(2, judgment.getVerdict());
            stmt.setString(3, judgment.getActSection());
            stmt.setString(4, judgment.getPunishment());
            stmt.setInt(5, judgment.getFine());
            stmt.setDate(6, judgment.getJudgmentDate());
            stmt.setInt(7, judgment.getJudgmentId());
            stmt.executeUpdate();
        }
    }

    public void deleteJudgment(int judgmentId) throws Exception {
        String sql = "DELETE FROM judgments WHERE judgment_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, judgmentId);
            stmt.executeUpdate();
        }
    }
}