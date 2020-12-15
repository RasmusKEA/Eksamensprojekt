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

    //opretter et subproject i databasen
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

    //returnerer en liste af subprojects hentet i databasen
    public ArrayList<SubProject> getSubProjects(int projectID){
        ArrayList<SubProject> listToReturn = new ArrayList<>();
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT subprojectname, idsubprojects, SPstartDate, SPendDate FROM subprojects WHERE projectid = ? AND idsubprojects != 0");

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

    //kører igennem en liste af subprojects og tjekker om dette subProjectID allerede er i listen
    private SubProject getSubProject( int subProjectID, List<SubProject> subProjects) {
        return subProjects.stream()
                .filter( p -> p.getSubProjectID() == subProjectID )
                .findFirst()
                .orElse( null );
    }

    //henter alle subprojects og tilknytter dem en liste af de tasks der hører til hvert subproject
    public ArrayList<SubProject> getEntireSubProject(int projectID){
        ArrayList<SubProject> listToReturn = new ArrayList<>();

        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("select idsubprojects, subprojectName, taskName, taskHours, taskEmployees, startDate, endDate, idsptasks, SPstartDate, SPendDate\n" +
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
                    listToReturn.add(new SubProject(rs.getInt(1), rs.getString(2), listOfTask, projectServices.formatDate(rs.getString(9)), projectServices.formatDate(rs.getString(10))));
                }else{
                    subProject.getTasks().add( new Task(rs.getString(3), rs.getInt(4), rs.getInt(5), projectServices.formatDate(rs.getString(6)), projectServices.formatDate(rs.getString(7)), rs.getInt(8)));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listToReturn;
    }

    //opdaterer start og slut datoer til subprojects
    public void updateDates(String startDate, String endDate, int subProjectID){
        try {
            //if startDate < dbStartDate == UPDATE
            //if endDate > dbEndDate == update

            System.out.println(projectServices.reverseDate(startDate));

            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT SPstartDate, SPendDate FROM subprojects WHERE idsubprojects = ?");
            ps.setInt(1, subProjectID);

            ResultSet rs = ps.executeQuery();

                rs.next();
                String SPstartDate = rs.getString(1).substring(0, 10);
                String SPendDate = rs.getString(2).substring(0, 10);
                System.out.println(projectServices.reverseDate(SPstartDate));

                if (projectServices.reverseDate(startDate).isBefore(projectServices.reverseDate(SPstartDate))) {
                    ps = connection.establishConnection().prepareStatement("UPDATE subprojects SET SPstartDate = ? WHERE (idsubprojects = ?)");
                    ps.setString(1, startDate);
                    ps.setInt(2, subProjectID);
                    ps.executeUpdate();
                }

                if (projectServices.reverseDate(endDate).isAfter(projectServices.reverseDate(SPendDate))) {
                    ps = connection.establishConnection().prepareStatement("UPDATE subprojects SET SPendDate = ? WHERE (idsubprojects = ?)");
                    ps.setString(1, endDate);
                    ps.setInt(2, subProjectID);
                    ps.executeUpdate();
                }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sletter et subproject
    public void deleteSubProject(int subProjectID){

        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("DELETE FROM subprojects WHERE idsubprojects = ?");
            ps.setInt(1, subProjectID);
            ps.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
