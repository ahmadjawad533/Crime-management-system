package com.cjs.dao;

import com.cjs.model.LegalAct; // Import the existing LegalAct class
import com.cjs.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LegalActDAO {
    public List<LegalAct> getAllLegalActs() throws SQLException { // Change Exception to SQLException
        List<LegalAct> legalActs = new ArrayList<>();
        String sql = "SELECT * FROM legal_acts";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                LegalAct act = new LegalAct();
                act.setActId(rs.getInt("act_id")); // Correct column name: act_id
                act.setCrimeName(rs.getString("crime_name"));
                act.setSectionCodes(rs.getString("section_codes"));
                act.setPunishment(rs.getString("punishment"));
                act.setFineAmount(rs.getDouble("fine_amount"));
                legalActs.add(act);
            }
        }
        return legalActs;
    }

    public LegalAct getLegalActByCrimeName(String crimeName) throws SQLException { // Change Exception to SQLException
        String sql = "SELECT * FROM legal_acts WHERE crime_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, crimeName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LegalAct act = new LegalAct();
                    act.setActId(rs.getInt("act_id")); // Correct column name: act_id
                    act.setCrimeName(rs.getString("crime_name"));
                    act.setSectionCodes(rs.getString("section_codes"));
                    act.setPunishment(rs.getString("punishment"));
                    act.setFineAmount(rs.getDouble("fine_amount"));
                    return act;
                }
            }
        }
        return null;
    }
}