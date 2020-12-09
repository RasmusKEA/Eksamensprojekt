package com.example.demo.repositories;

import com.example.demo.models.Project;
import com.example.demo.models.SubProject;
import com.example.demo.models.Task;
import com.example.demo.services.DBConnection;
import com.example.demo.services.ProjectServices;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    DBConnection connection = new DBConnection();
    ProjectServices projectServices = new ProjectServices();

    public void createNewProject(String projectName){
        try {
            SubProjectRepository spr = new SubProjectRepository();
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
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT idprojects, projectname, MIN(startDate), MAX(endDate) FROM projects, tasks WHERE idprojects = projectid GROUP BY projectid");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                listToReturn.add(new Project(rs.getString(2),rs.getInt(1), projectServices.formatDate(rs.getString(3)), projectServices.formatDate(rs.getString(4))));
            }

            ps = connection.establishConnection().prepareStatement("SELECT idprojects, projectname FROM projects");
            rs = ps.executeQuery();
            ArrayList<Project> tempList = new ArrayList<>();

            while(rs.next()){
                tempList.add(new Project(rs.getString(2), rs.getInt(1), "No date ", "set yet"));
            }

            tempList.removeAll(listToReturn);
            listToReturn.addAll(tempList);

            listToReturn.toString();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Size of list of all project names: " + listToReturn.size());

        return listToReturn;
    }










}
