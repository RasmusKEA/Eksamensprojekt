package com.example.demo.repositories;

import com.example.demo.models.Task;
import com.example.demo.services.DBConnection;
import com.example.demo.services.ProjectServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TaskRepository {
    DBConnection connection = new DBConnection();
    ProjectServices projectServices = new ProjectServices();

    //opretter en task i databasen
    public void createTasks(String taskName, int taskHours, int taskEmployees, int projectID, String startDate, String endDate){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO tasks (taskName, taskHours, taskEmployees, startDate, endDate, projectid) values (?, ?, ?, ?, ?, ?)");
            ps.setString(1, taskName);
            ps.setInt(2, taskHours);
            ps.setInt(3, taskEmployees);
            ps.setString(4, startDate);
            ps.setString(5, endDate);
            ps.setInt(6, projectID);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //opretter en subproject task i databasen
    public void createSPTasks(String taskName, int taskHours, int taskEmployees, int projectID, String startDate, String endDate, int subProjectID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO sptasks (taskName, taskHours, taskEmployees, startDate, endDate, projectid, subprojectid) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, taskName);
            ps.setInt(2, taskHours);
            ps.setInt(3, taskEmployees);
            ps.setString(4, startDate);
            ps.setString(5, endDate);
            ps.setInt(6, projectID);
            ps.setInt(7, subProjectID);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sletter en task i databasen
    public void deleteTask(int taskID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM tasks WHERE (idtasks = ?)");

            ps.setInt(1, taskID);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sletter en subproject task i databasen
    public void deleteSPTask(int spTaskID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM sptasks WHERE (idsptasks = ?)");

            ps.setInt(1, spTaskID);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //laver en liste af alle tasks i databasen
    public ArrayList<Task> getTasksByProjectID(int projectID){

        ArrayList<Task> listToReturn = new ArrayList<>();
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT taskName, taskHours, taskEmployees, startDate, endDate, idtasks, taskStatus FROM tasks WHERE projectid = ?");
            ps.setInt(1, projectID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                listToReturn.add(new Task(rs.getString(1), rs.getInt(2), rs.getInt(3), projectServices.formatDate(rs.getString(4)), projectServices.formatDate(rs.getString(5)), rs.getInt(6), rs.getInt(7)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listToReturn;
    }

    public void setSPTaskDone(int taskID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("UPDATE sptasks SET taskStatus = '1' WHERE (idsptasks = ?)");
            ps.setInt(1, taskID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setSPTaskUndone(int taskID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("UPDATE sptasks SET taskStatus = '0' WHERE (idsptasks = ?)");
            ps.setInt(1, taskID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTaskDone(int taskID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("UPDATE tasks SET taskStatus = '1' WHERE (idtasks = ?)");
            ps.setInt(1, taskID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setTaskUndone(int taskID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("UPDATE tasks SET taskStatus = '0' WHERE (idtasks = ?)");
            ps.setInt(1, taskID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
