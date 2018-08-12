package com.rtlabs.acidrain.pojo;

import java.util.Date;

public class ApplicationView {
    private int applicationId;
    private Date created;
    private String userFullName;
    private Date userBirthDate;
    private String userContacts;
    private String serviceName;
    private String departmentName;

    public ApplicationView(int applicationId, Date created, String userFullName, Date userBirthDate, String userContacts, String serviceName, String departmentName) {
        this.applicationId = applicationId;
        this.created = created;
        this.userFullName = userFullName;
        this.userBirthDate = userBirthDate;
        this.userContacts = userContacts;
        this.serviceName = serviceName;
        this.departmentName = departmentName;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Date getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userBirthDate = userBirthDate;
    }

    public String getUserContacts() {
        return userContacts;
    }

    public void setUserContacts(String userContacts) {
        this.userContacts = userContacts;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
