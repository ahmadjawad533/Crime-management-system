package com.cjs.model;

import java.sql.Timestamp;

public class Evidence {
    private int evidenceId;
    private int caseId;
    private String description;
    private String filePath;
    private String type; // Document, Image, Video
    private Timestamp uploadedAt;

    // Constructor
    public Evidence() {}

    public Evidence(int evidenceId, int caseId, String description, String filePath,
                    String type, Timestamp uploadedAt) {
        this.evidenceId = evidenceId;
        this.caseId = caseId;
        this.description = description;
        this.filePath = filePath;
        this.type = type;
        this.uploadedAt = uploadedAt;
    }

    // Getters and Setters
    public int getEvidenceId() {
        return evidenceId;
    }

    public void setEvidenceId(int evidenceId) {
        this.evidenceId = evidenceId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(Timestamp uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    @Override
    public String toString() {
        return "Evidence{" +
                "evidenceId=" + evidenceId +
                ", caseId=" + caseId +
                ", description='" + description + '\'' +
                ", filePath='" + filePath + '\'' +
                ", type='" + type + '\'' +
                ", uploadedAt=" + uploadedAt +
                '}';
    }
}