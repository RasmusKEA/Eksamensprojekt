package com.example.demo.controllers;

import com.example.demo.models.SubProject;
import com.example.demo.models.Task;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.SubProjectRepository;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.ProjectServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProjectController {
    ProjectRepository pr = new ProjectRepository();
    ProjectServices ps = new ProjectServices();
    TaskRepository tr = new TaskRepository();
    SubProjectRepository spr = new SubProjectRepository();

    //getmapping for project.htm
    //tilføjer en liste af hhv. tasks, subprojects og subproject tasks til viewet
    @GetMapping("project")
    public String project(Model model, HttpServletRequest projectRequest){

        ArrayList<Task> taskList = tr.getTasksByProjectID((int) projectRequest.getSession().getAttribute("projectID"));
        ArrayList<SubProject> spList = spr.getSubProjects((int) projectRequest.getSession().getAttribute("projectID"));
        ArrayList<SubProject> spTask = spr.getEntireSubProject((int) projectRequest.getSession().getAttribute("projectID"));

        model.addAttribute("spTask", spTask);
        model.addAttribute("taskList", taskList);
        model.addAttribute("spList", spList);
        return "project";
    }

    //post mapping til når man opretter en task. kalder calcWorkHours der udgør en slutdato for en task
    @PostMapping("createTaskPost")
    public String createTaskPost(HttpServletRequest taskRequest, HttpServletRequest projectRequest){

        String taskName = taskRequest.getParameter("taskName");
        int taskHours = Integer.parseInt(taskRequest.getParameter("taskHours"));
        int taskEmployees = Integer.parseInt(taskRequest.getParameter("taskEmployees"));
        int projectID = (int) projectRequest.getSession().getAttribute("projectID");
        String startDate = taskRequest.getParameter("startDate");
        int subProjectID = Integer.parseInt(taskRequest.getParameter("subprojects"));

        double calcWorkhours;


        if (taskEmployees == 0){
            calcWorkhours = Math.ceil(((double) taskHours/1.0)/8.0);
        }else if (taskHours == 0){
            calcWorkhours = Math.ceil((1.0/(double) taskEmployees)/8.0);
        }else if (taskEmployees == 0 && taskHours == 0){
            calcWorkhours = Math.ceil((1.0/1.0)/8.0);
        }else{
            calcWorkhours = Math.ceil(((double) taskHours/(double) taskEmployees)/8.0);
        }




        int workhours = (int) calcWorkhours;


        LocalDate dateObj = LocalDate.parse(startDate);
        String endDate = ps.calcEndDate(dateObj, workhours);


        if(subProjectID != 0){
            spr.updateDates(startDate, endDate, subProjectID);
            tr.createSPTasks(taskName, taskHours, taskEmployees, projectID, startDate, endDate, subProjectID);
        }else{
            tr.createTasks(taskName, taskHours, taskEmployees, projectID, startDate, endDate);
        }

        return "redirect:/project";
    }

    //post mapping til oprettelse af et subproject
    @PostMapping("createSubProjectPost")
    public String createSubProjectPost(HttpServletRequest SPrequest, HttpServletRequest projectRequest){
        String subProjectName = SPrequest.getParameter("subProjectName");
        int projectID = (int) projectRequest.getSession().getAttribute("projectID");

        spr.createSubProject(subProjectName, projectID);

        return "redirect:/project";
    }

    //post mapping til slettelse af en task
    @PostMapping("deleteTask")
    public String deleteTask (HttpServletRequest SPrequest){

        int taskID = Integer.parseInt(SPrequest.getParameter("taskToDelete"));
        tr.deleteTask(taskID);

        return "redirect:/project";
    }

    //post mapping til slettelse af en subproject task
    @PostMapping("deleteSPTask")
    public String deleteSPTask (HttpServletRequest SPrequest){

        int spTaskID = Integer.parseInt(SPrequest.getParameter("spTaskToDelete"));
        tr.deleteSPTask(spTaskID);

        return "redirect:/project";
    }


    //post mapping til slettelse af et subproject
    @PostMapping("deleteSP")
    public String deleteSP(HttpServletRequest request){
        int subProjectID = Integer.parseInt(request.getParameter("spToDelete"));
        spr.deleteSubProject(subProjectID);

        return "redirect:/project";
    }

    @PostMapping("taskDone")
    public String taskDone(HttpServletRequest SPrequest){
        int taskID = Integer.parseInt(SPrequest.getParameter("spTaskToDelete"));

        if(SPrequest.getParameter("taskStatus") == null){
           tr.setTaskUndone(taskID);
           return "redirect:/project";
        }

        tr.setTaskDone(taskID);
        return "redirect:/project";
    }

}
