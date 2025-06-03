package com.cjs.model;

import java.sql.Date;
import java.sql.Timestamp;

public class CourtHearing {
    private int hearingId;
    private int caseId;
    private Date hearingDate;
    private String courtRoom;
    private String details;
    private Timestamp createdAt;

    // Constructor
    public CourtHearing() {}

    public CourtHearing(int hearingId, int caseId, Date hearingDate, String courtRoom,
                        String details, Timestamp createdAt) {
        this.hearingId = hearingId;
        this.caseId = caseId;
        this.hearingDate = hearingDate;
        this.courtRoom = courtRoom;
        this.details = details;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getHearingId() {
        return hearingId;
    }

    public void setHearingId(int hearingId) {
        this.hearingId = hearingId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public Date getHearingDate() {
        return hearingDate;
    }

    public void setHearingDate(Date hearingDate) {
        this.hearingDate = hearingDate;
    }

    public String getCourtRoom() {
        return courtRoom;
    }

    public void setCourtRoom(String courtRoom) {
        this.courtRoom = courtRoom;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CourtHearing{" +
                "hearingId=" + hearingId +
                ", caseId=" + caseId +
                ", hearingDate=" + hearingDate +
                ", courtRoom='" + courtRoom + '\'' +
                ", details='" + details + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}