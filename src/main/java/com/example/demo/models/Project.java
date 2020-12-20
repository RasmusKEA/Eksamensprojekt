package com.example.demo.models;

import java.util.Objects;

public class Project {
    private String projectName;
    private int projectID;
    private String projectStartDate, projectEndDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectID == project.projectID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", projectID=" + projectID +
                ", projectStartDate='" + projectStartDate + '\'' +
                ", projectEndDate='" + projectEndDate + '\'' +
                '}';
    }
}
