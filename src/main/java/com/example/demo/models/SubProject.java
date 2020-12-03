package com.example.demo.models;

public class SubProject {
    private int projctID, subProjectHours, subProjectEmployees, subProjectID;
    private String subProjectName;

    public SubProject(int projctID, int subProjectHours, int subProjectEmployees, int subProjectID, String subProjectName) {
        this.projctID = projctID;
        this.subProjectHours = subProjectHours;
        this.subProjectEmployees = subProjectEmployees;
        this.subProjectID = subProjectID;
        this.subProjectName = subProjectName;
    }

    public int getProjctID() {
        return projctID;
    }

    public void setProjctID(int projctID) {
        this.projctID = projctID;
    }

    public int getSubProjectHours() {
        return subProjectHours;
    }

    public void setSubProjectHours(int subProjectHours) {
        this.subProjectHours = subProjectHours;
    }

    public int getSubProjectEmployees() {
        return subProjectEmployees;
    }

    public void setSubProjectEmployees(int subProjectEmployees) {
        this.subProjectEmployees = subProjectEmployees;
    }

    public int getSubProjectID() {
        return subProjectID;
    }

    public void setSubProjectID(int subProjectID) {
        this.subProjectID = subProjectID;
    }

    public String getSubProjectName() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName = subProjectName;
    }
}
