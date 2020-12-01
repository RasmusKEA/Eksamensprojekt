package com.example.demo.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Task {
    private String taskName;
    private int taskHours, taskEmployees;
    private Date startDate;

    public Task(String taskName, int taskHours, int taskEmployees) {
        this.taskName = taskName;
        this.taskHours = taskHours;
        this.taskEmployees = taskEmployees;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
