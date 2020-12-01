package com.example.demo.repositories;

import com.example.demo.models.Project;
import com.example.demo.services.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT projectname FROM projects");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                listToReturn.add(new Project(rs.getString(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Size of list of all project names: " + listToReturn.size());

        return listToReturn;
    }




}
