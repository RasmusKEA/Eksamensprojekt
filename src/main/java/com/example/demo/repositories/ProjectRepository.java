package com.example.demo.repositories;

import com.example.demo.models.Project;
import com.example.demo.services.DBConnection;
import com.example.demo.services.ProjectServices;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectRepository {
    DBConnection connection = new DBConnection();
    ProjectServices projectServices = new ProjectServices();

    //opretter et project i databasen
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

    //Returnerer en liste af alle projektnavne.
    public ArrayList<Project> getProjectsFromDB(){
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

    //sletter et project i databasen
    public void deleteProject(int projectID){

        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM projects WHERE idprojects = ?");
            ps.setInt(1, projectID);
            ps.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //opdaterer projectname i databasen
    public void updateProjectName(String projectName, int projectID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("UPDATE projects SET projectname = ? WHERE (idprojects = ?)");
            ps.setString(1, projectName);
            ps.setInt(2, projectID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
