package com.cjs.model;

import java.sql.Timestamp;

public class Log {
    private int logId;
    private int userId;
    private String action;
    private Timestamp actionTime;

    // Constructor
    public Log() {}

    public Log(int logId, int userId, String action, Timestamp actionTime) {
        this.logId = logId;
        this.userId = userId;
        this.action = action;
        this.actionTime = actionTime;
    }

    // Getters and Setters
    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getActionTime() {
        return actionTime;
    }

    public void setActionTime(Timestamp actionTime) {
        this.actionTime = actionTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", userId=" + userId +
                ", action='" + action + '\'' +
                ", actionTime=" + actionTime +
                '}';
    }
}