package com.cjs.model;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String role; // Admin, Police, Judge, Lawyer, Criminal, Victim, Witness
    private String name;
    private String designation; // e.g., SSP, District Judge, Advocate
    private String contact;
    private String email;
    private Timestamp createdAt;

    // Constructor
    public User() {}

    public User(int userId, String username, String passwordHash, String role, String name,
                String designation, String contact, String email, Timestamp createdAt) {
        this.userId = userId;
        setUsername(username);
        setPasswordHash(passwordHash);
        setRole(role);
        setName(name);
        setDesignation(designation);
        setContact(contact);
        setEmail(email);
        this.createdAt = createdAt != null ? createdAt : new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters with Validation
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        if (userId < 0) {
            throw new IllegalArgumentException("User ID cannot be negative");
        }
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username.trim();
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        }
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        this.role = role.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.trim().isEmpty()) {
            this.designation = "N/A";
        } else {
            this.designation = designation.trim();
        }
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if (contact == null || contact.trim().isEmpty()) {
            this.contact = "N/A";
        } else {
            this.contact = contact.trim();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null && !email.trim().isEmpty() && !email.matches("^[\\w-\\.]+@cjs\\.pk$")) {
            throw new IllegalArgumentException("Email must be in the format username@cjs.pk");
        }
        this.email = email != null ? email.trim() : null;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", contact='" + contact + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}