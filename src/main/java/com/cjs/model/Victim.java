package com.cjs.model;

import java.sql.Timestamp;

public class Victim {
    private int victimId;
    private int caseId;
    private String name;
    private String cnic;
    private String contact;
    private String statement;
    private boolean protectionRequested;
    private Timestamp createdAt;

    // Constructor
    public Victim() {}

    public Victim(int victimId, int caseId, String name, String cnic, String contact,
                  String statement, boolean protectionRequested, Timestamp createdAt) {
        this.victimId = victimId;
        this.caseId = caseId;
        this.name = name;
        this.cnic = cnic;
        this.contact = contact;
        this.statement = statement;
        this.protectionRequested = protectionRequested;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getVictimId() {
        return victimId;
    }

    public void setVictimId(int victimId) {
        this.victimId = victimId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public boolean isProtectionRequested() {
        return protectionRequested;
    }

    public void setProtectionRequested(boolean protectionRequested) {
        this.protectionRequested = protectionRequested;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Victim{" +
                "victimId=" + victimId +
                ", caseId=" + caseId +
                ", name='" + name + '\'' +
                ", cnic='" + cnic + '\'' +
                ", contact='" + contact + '\'' +
                ", statement='" + statement + '\'' +
                ", protectionRequested=" + protectionRequested +
                ", createdAt=" + createdAt +
                '}';
    }
}