package com.cjs.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Judgment {
    private int judgmentId;
    private int caseId;
    private String verdict;
    private String actSection; // e.g., PPC Section 302
    private String punishment;
    private int fine;
    private Date judgmentDate;
    private Timestamp createdAt;

    // Constructor
    public Judgment() {}

    public Judgment(int judgmentId, int caseId, String verdict, String actSection, String punishment,
                    int fine, Date judgmentDate, Timestamp createdAt) {
        this.judgmentId = judgmentId;
        this.caseId = caseId;
        this.verdict = verdict;
        this.actSection = actSection;
        this.punishment = punishment;
        this.fine = fine;
        this.judgmentDate = judgmentDate;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getJudgmentId() {
        return judgmentId;
    }

    public void setJudgmentId(int judgmentId) {
        this.judgmentId = judgmentId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getActSection() {
        return actSection;
    }

    public void setActSection(String actSection) {
        this.actSection = actSection;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public Date getJudgmentDate() {
        return judgmentDate;
    }

    public void setJudgmentDate(Date judgmentDate) {
        this.judgmentDate = judgmentDate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Judgment{" +
                "judgmentId=" + judgmentId +
                ", caseId=" + caseId +
                ", verdict='" + verdict + '\'' +
                ", actSection='" + actSection + '\'' +
                ", punishment='" + punishment + '\'' +
                ", fine=" + fine +
                ", judgmentDate=" + judgmentDate +
                ", createdAt=" + createdAt +
                '}';
    }
}