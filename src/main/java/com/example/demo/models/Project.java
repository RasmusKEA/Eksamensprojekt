package com.example.demo.models;

public class Project {
    private String projectName;
    private int projectID;
    private String projectStartDate, projectEndDate;

    public Project(String projectName) {
        this.projectName = projectName;
    }

    public Project(String projectName, int projectID) {
        this.projectName = projectName;
        this.projectID = projectID;
    }

    public Project(String projectName, int projectID, String projectStartDate, String projectEndDate) {
        this.projectName = projectName;
        this.projectID = projectID;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(String projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public String getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(String projectEndDate) {
        this.projectEndDate = projectEndDate;
    }
}
