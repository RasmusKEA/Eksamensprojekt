package com.example.demo.repositories;

import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.services.DBConnection;
import com.example.demo.services.ProjectServices;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProjectRepository {
    DBConnection connection = new DBConnection();
    public void createNewProject(String projectName){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO projects (projectname) VALUES (?)");
            ps.setString(1, projectName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Project> getProjectsFromDB(){
        //Returnerer en liste af alle projektnavne.
        //TODO Exception handling hvis der ikke er oprettet nogle projekter endnu
        ArrayList<Project> listToReturn = new ArrayList<>();

        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT idprojects, projectname FROM projects");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                listToReturn.add(new Project(rs.getString(2),rs.getInt(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Size of list of all project names: " + listToReturn.size());

        return listToReturn;
    }


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

    public ArrayList<Task> getTasksByProjectID(int projectID){
        ProjectServices projectServices = new ProjectServices();
        ArrayList<Task> listToReturn = new ArrayList<>();
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT taskName, taskHours, taskEmployees, startDate, endDate FROM tasks WHERE projectid = ?");
            ps.setInt(1, projectID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                listToReturn.add(new Task(rs.getString(1), rs.getInt(2), rs.getInt(3), projectServices.formatDate(rs.getString(4)), projectServices.formatDate(rs.getString(5))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listToReturn;

    }

    public ArrayList<Project> getProjectDisplay(){
        ArrayList<Project> listToReturn = new ArrayList<>();
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT idprojects, projectname, projectid, startDate, DATE(STR_TO_DATE(startDate, '%d/%m/%Y' )) AS startDate FROM tasks, projects WHERE projectid = idprojects AND 1 ORDER BY startDate ASC LIMIT 1");

            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listToReturn;
    }



}
