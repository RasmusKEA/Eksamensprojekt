package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

public class SubProject {
    private int projctID, subProjectHours, subProjectEmployees, subProjectID;
    private String subProjectName, startDate, endDate;
    private Task task;
    private ArrayList<Task> tasks;

    public SubProject(int projctID, int subProjectHours, int subProjectEmployees, int subProjectID, String subProjectName) {
        this.projctID = projctID;
        this.subProjectHours = subProjectHours;
        this.subProjectEmployees = subProjectEmployees;
        this.subProjectID = subProjectID;
        this.subProjectName = subProjectName;
    }

    public SubProject(String subProjectName) {
        this.subProjectName = subProjectName;
    }

    public SubProject(String subProjectName, Task task, int subProjectID){
        this.subProjectName = subProjectName;
        this.task = task;
        this.subProjectID = subProjectID;
    }

    public SubProject(String subProjectName, int subProjectID){
        this.subProjectName = subProjectName;
        this.subProjectID = subProjectID;

    }

    public SubProject(int subProjectID, String subProjectName, ArrayList<Task> tasks, String startDate, String endDate){
        this.subProjectName = subProjectName;
        this.subProjectID = subProjectID;
        this.tasks = tasks;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "SubProject{" +
                "subProjectID=" + subProjectID +
                ", subProjectName='" + subProjectName + '\'' +
                '}';
    }
}
