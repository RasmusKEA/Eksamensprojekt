package com.example.demo.models;

public class Task {
    private String taskName;
    private int taskHours, taskEmployees;

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

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", taskHours=" + taskHours +
                ", taskEmployees=" + taskEmployees +
                '}';
    }
}
