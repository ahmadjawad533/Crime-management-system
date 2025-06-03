package com.cjs.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Case {
    private int caseId;
    private String firNumber;
    private int policeOfficerId;
    private int judgeId;
    private int lawyerId;
    private String status;
    private String crimeType;
    private Date incidentDate;
    private String location;
    private Timestamp createdAt;

    // Constructor
    public Case() {}

    public Case(int caseId, String firNumber, int policeOfficerId, int judgeId, int lawyerId,
                String status, String crimeType, Date incidentDate, String location, Timestamp createdAt) {
        this.caseId = caseId;
        this.firNumber = firNumber;
        this.policeOfficerId = policeOfficerId;
        this.judgeId = judgeId;
        this.lawyerId = lawyerId;
        this.status = status;
        this.crimeType = crimeType;
        this.incidentDate = incidentDate;
        this.location = location;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getCaseId() { return caseId; }
    public void setCaseId(int caseId) { this.caseId = caseId; }
    public String getFirNumber() { return firNumber; }
    public void setFirNumber(String firNumber) { this.firNumber = firNumber; }
    public int getPoliceOfficerId() { return policeOfficerId; }
    public void setPoliceOfficerId(int policeOfficerId) { this.policeOfficerId = policeOfficerId; }
    public int getJudgeId() { return judgeId; }
    public void setJudgeId(int judgeId) { this.judgeId = judgeId; }
    public int getLawyerId() { return lawyerId; }
    public void setLawyerId(int lawyerId) { this.lawyerId = lawyerId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCrimeType() { return crimeType; }
    public void setCrimeType(String crimeType) { this.crimeType = crimeType; }
    public Date getIncidentDate() { return incidentDate; }
    public void setIncidentDate(Date incidentDate) { this.incidentDate = incidentDate; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    // Add methods to convert to/from LocalDate
    public LocalDate getIncidentDateAsLocalDate() {
        return incidentDate != null ? incidentDate.toLocalDate() : null;
    }

    public void setIncidentDateFromLocalDate(LocalDate localDate) {
        this.incidentDate = localDate != null ? Date.valueOf(localDate) : null;
    }

    @Override
    public String toString() {
        return "Case{" +
                "caseId=" + caseId +
                ", firNumber='" + firNumber + '\'' +
                ", policeOfficerId=" + policeOfficerId +
                ", judgeId=" + judgeId +
                ", lawyerId=" + lawyerId +
                ", status='" + status + '\'' +
                ", crimeType='" + crimeType + '\'' +
                ", incidentDate=" + incidentDate +
                ", location='" + location + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}