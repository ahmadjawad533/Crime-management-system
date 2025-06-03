package com.cjs.model;

import java.sql.Timestamp;

public class Criminal {
    private int criminalId;
    private int caseId;
    private String name;
    private String cnic;
    private String address;
    private String charges; // Linked to Pakistani Penal Code sections
    private Timestamp createdAt;

    // Constructor
    public Criminal() {}

    public Criminal(int criminalId, int caseId, String name, String cnic, String address,
                    String charges, Timestamp createdAt) {
        this.criminalId = criminalId;
        this.caseId = caseId;
        this.name = name;
        this.cnic = cnic;
        this.address = address;
        this.charges = charges;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getCriminalId() {
        return criminalId;
    }

    public void setCriminalId(int criminalId) {
        this.criminalId = criminalId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCharges() {
        return charges;
    }

    public void setCharges(String charges) {
        this.charges = charges;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Criminal{" +
                "criminalId=" + criminalId +
                ", caseId=" + caseId +
                ", name='" + name + '\'' +
                ", cnic='" + cnic + '\'' +
                ", address='" + address + '\'' +
                ", charges='" + charges + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}