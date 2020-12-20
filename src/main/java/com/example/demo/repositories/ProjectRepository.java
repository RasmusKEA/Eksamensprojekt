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
        ArrayList<Project> tempList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT p.idprojects, p.projectname, \n" +
                    "       MIN(least(coalesce(t.startDate,s.startdate),\n" +
                    "                 coalesce(s.startDate,t.startdate))) as startdate,\n" +
                    "       MAX(greatest(coalesce(t.endDate,s.enddate),\n" +
                    "                    coalesce(s.endDate,t.enddate))) as enddate\n" +
                    "  FROM projects p \n" +
                    "  left join tasks t on p.idprojects = t.projectid\n" +
                    "  left join sptasks s on p.idprojects = s.projectid\n" +
                    "GROUP BY p.idprojects, p.projectname;");

            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String startDate = rs.getString(3);
                String endDate = rs.getString(4);
                if(startDate == null || endDate == null){
                    tempList.add(new Project(rs.getString(2), rs.getInt(1), "No date", "set yet"));
                }else{
                    listToReturn.add(new Project(rs.getString(2),rs.getInt(1), projectServices.formatDate(rs.getString(3)), projectServices.formatDate(rs.getString(4))));
                }
            }

            tempList.removeAll(listToReturn);
            listToReturn.addAll(tempList);

        } catch (SQLException e) {
            e.printStackTrace();
        }


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
