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

    public void deleteTask(int taskID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM tasks WHERE (idtasks = ?)");

            ps.setInt(1, taskID);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSPTask(int spTaskID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM sptasks WHERE (idsptasks = ?)");

            ps.setInt(1, spTaskID);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTasksByProjectID(int projectID){

        ArrayList<Task> listToReturn = new ArrayList<>();
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT taskName, taskHours, taskEmployees, startDate, endDate, idtasks FROM tasks WHERE projectid = ?");
            ps.setInt(1, projectID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                listToReturn.add(new Task(rs.getString(1), rs.getInt(2), rs.getInt(3), projectServices.formatDate(rs.getString(4)), projectServices.formatDate(rs.getString(5)), rs.getInt(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listToReturn;

    }

}
