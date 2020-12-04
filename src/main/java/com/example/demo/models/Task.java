package com.example.demo.models;

public class Task {
    private String taskName;
    private int taskHours, taskEmployees, subProjectID;
    private String startDate, endDate;


    public Task(String taskName, int taskHours, int taskEmployees, String startDate, String endDate, int subProjectID) {
        this.taskName = taskName;
        this.taskHours = taskHours;
        this.taskEmployees = taskEmployees;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subProjectID = subProjectID;
    }

    public Task(String taskName, int taskHours, int taskEmployees, String startDate, String endDate) {
        this.taskName = taskName;
        this.taskHours = taskHours;
        this.taskEmployees = taskEmployees;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task(String taskName, int taskHours, int taskEmployees, String startDate) {
        this.taskName = taskName;
        this.taskHours = taskHours;
        this.taskEmployees = taskEmployees;
        this.startDate = startDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskHours() {
        return taskHours;
    }

    public void setTaskHours(int taskHours) {
        this.taskHours = taskHours;
    }

    public int getTaskEmployees() {
        return taskEmployees;
    }

    public void setTaskEmployees(int taskEmployees) {
        this.taskEmployees = taskEmployees;
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

    public int getSubProjectID() {
        return subProjectID;
    }

    public void setSubProjectID(int subProjectID) {
        this.subProjectID = subProjectID;
    }

    @Override
    public String toString() {
        return taskName;
    }
}
