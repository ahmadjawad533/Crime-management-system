package com.cjs.model;

public class LegalAct {
    private int actId; // Assuming an ID column exists
    private String crimeName;
    private String sectionCodes;
    private String punishment;
    private double fineAmount;

    // Constructor
    public LegalAct() {}

    public LegalAct(int actId, String crimeName, String sectionCodes, String punishment, double fineAmount) {
        this.actId = actId;
        this.crimeName = crimeName;
        this.sectionCodes = sectionCodes;
        this.punishment = punishment;
        this.fineAmount = fineAmount;
    }

    // Getters and Setters
    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getCrimeName() {
        return crimeName;
    }

    public void setCrimeName(String crimeName) {
        this.crimeName = crimeName;
    }

    public String getSectionCodes() {
        return sectionCodes;
    }

    public void setSectionCodes(String sectionCodes) {
        this.sectionCodes = sectionCodes;
    }

    public String getPunishment() {
        return punishment;
    }

    public void setPunishment(String punishment) {
        this.punishment = punishment;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }

    @Override
    public String toString() {
        return "LegalAct{" +
                "actId=" + actId +
                ", crimeName='" + crimeName + '\'' +
                ", sectionCodes='" + sectionCodes + '\'' +
                ", punishment='" + punishment + '\'' +
                ", fineAmount=" + fineAmount +
                '}';
    }
}