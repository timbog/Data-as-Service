package com.emc.daas.user;

/**
 * Created by timofb on 11/12/2015.
 */
public class DaaSUser {
    private String username;
    private String passwordHash;
    private String fullName;
    private String organization;
    private boolean isActive;
    private UserRole role;
    private double quota;
    private double usedSpace;

    public DaaSUser(String username, String passwordHash, String fullName, String organization, boolean isActive, UserRole role, double quota) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.organization = organization;
        this.isActive = isActive;
        this.role = role;
        this.quota = quota;
        this.usedSpace = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public String getOrganization() {
        return organization;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserRole getRole() {
        return role;
    }

    public double getQuota() {
        return quota;
    }

    public double getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(double usedSpace) {
        this.usedSpace = usedSpace;
    }
}