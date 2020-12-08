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


    public void createTasks(String taskName, int taskHours, int taskEmployees, int projectID, String startDate, String endDate, int subProjectID){
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO tasks (taskName, taskHours, taskEmployees, startDate, endDate, projectid, subprojectid) values (?, ?, ?, ?, ?, ?, ?)");
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

    public ArrayList<Task> getTasksByProjectID(int projectID){

        ArrayList<Task> listToReturn = new ArrayList<>();
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT taskName, taskHours, taskEmployees, startDate, endDate, subProjectID, idtasks FROM tasks WHERE projectid = ? AND subprojectid = 0");
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

    public ArrayList<SubProject> getSubProjects(int projectID){
        ArrayList<SubProject> listToReturn = new ArrayList<>();
        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT subprojectname, idsubprojects FROM subprojects WHERE projectid = ?");

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
            PreparedStatement ps = connection.establishConnection().prepareStatement("select idsubprojects, subprojectName, taskName, taskHours, taskEmployees, startDate, endDate, idtasks\n" +
                    "from subprojects \n" +
                    "inner join tasks \n" +
                    "on subprojects.idsubprojects = tasks.subprojectid \n" +
                    "WHERE tasks.projectid = ? AND subprojects.projectid = ?\n" +
                    "order by idsubprojects, idtasks ");

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
