package com.example.demo.repositories;

import com.example.demo.models.SubProject;
import com.example.demo.models.Task;
import com.example.demo.services.DBConnection;
import com.example.demo.services.ProjectServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubProjectRepository {
    DBConnection connection = new DBConnection();
    ProjectServices projectServices = new ProjectServices();

    public void createSubProject(String subProjectName, int projectID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO subprojects (subprojectName, projectid) VALUES (?, ?)");
            ps.setString(1, subProjectName);
            ps.setInt(2, projectID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDefaultSubProject(String subProjectName, int projectID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO subprojects (idsubprojects, subprojectName, projectid) VALUES (0, ?, ?)");
            ps.setString(1, subProjectName);
            ps.setInt(2, projectID);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<SubProject> getSubProjects(int projectID){
        ArrayList<SubProject> listToReturn = new ArrayList<>();
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT subprojectname, idsubprojects FROM subprojects WHERE projectid = ? AND idsubprojects != 0");

            ps.setInt(1, projectID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                listToReturn.add(new SubProject(rs.getString(1), rs.getInt(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listToReturn;
    }

    private SubProject getSubProject( int subProjectID, List<SubProject> subProjects) {
        return subProjects.stream()
                .filter( p -> p.getSubProjectID() == subProjectID )
                .findFirst()
                .orElse( null );
    }

    public ArrayList<SubProject> getEntireSubProject1(int projectID){
        ArrayList<SubProject> listToReturn = new ArrayList<>();

        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("select idsubprojects, subprojectName, taskName, taskHours, taskEmployees, startDate, endDate, idsptasks\n" +
                    "from subprojects \n" +
                    "inner join sptasks \n" +
                    "on subprojects.idsubprojects = sptasks.subprojectid \n" +
                    "WHERE sptasks.projectid = ? AND subprojects.projectid = ? AND idsubprojects != 0\n " +
                    "order by idsubprojects, idsptasks ");

            ps.setInt(1, projectID);
            ps.setInt(2, projectID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                SubProject subProject = getSubProject(rs.getInt(1), listToReturn);
                if( subProject == null ){
                    ArrayList<Task> listOfTask = new ArrayList<>();
                    listOfTask.add(new Task(rs.getString(3), rs.getInt(4), rs.getInt(5), projectServices.formatDate(rs.getString(6)), projectServices.formatDate(rs.getString(7)), rs.getInt(8)));
                    listToReturn.add(new SubProject(rs.getInt(1), rs.getString(2), listOfTask));
                }else{
                    subProject.getTasks().add( new Task(rs.getString(3), rs.getInt(4), rs.getInt(5), projectServices.formatDate(rs.getString(6)), projectServices.formatDate(rs.getString(7)), rs.getInt(8)));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listToReturn;
    }
}
