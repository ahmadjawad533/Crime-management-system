package com.cjs.model;

import java.sql.Timestamp;

public class Witness {
    private int witnessId;
    private int caseId;
    private String name;
    private String cnic;
    private String statement;
    private boolean protectionRequested;
    private boolean verified;
    private Timestamp createdAt;

    // Constructor
    public Witness() {}

    public Witness(int witnessId, int caseId, String name, String cnic, String statement,
                   boolean protectionRequested, boolean verified, Timestamp createdAt) {
        this.witnessId = witnessId;
        this.caseId = caseId;
        this.name = name;
        this.cnic = cnic;
        this.statement = statement;
        this.protectionRequested = protectionRequested;
        this.verified = verified;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getWitnessId() {
        return witnessId;
    }

    public void setWitnessId(int witnessId) {
        this.witnessId = witnessId;
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Witness{" +
                "witnessId=" + witnessId +
                ", caseId=" + caseId +
                ", name='" + name + '\'' +
                ", cnic='" + cnic + '\'' +
                ", statement='" + statement + '\'' +
                ", protectionRequested=" + protectionRequested +
                ", verified=" + verified +
                ", createdAt=" + createdAt +
                '}';
    }
}