package com.example.demo.controllers;

import com.example.demo.models.SubProject;
import com.example.demo.models.Task;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.SubProjectRepository;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.ProjectServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class ProjectController {
    ProjectRepository pr = new ProjectRepository();
    ProjectServices ps = new ProjectServices();
    TaskRepository tr = new TaskRepository();
    SubProjectRepository spr = new SubProjectRepository();

    @GetMapping("project")
    public String project(Model model, HttpServletRequest projectRequest){

        ArrayList<Task> taskList = tr.getTasksByProjectID((int) projectRequest.getSession().getAttribute("projectID"));
        ArrayList<SubProject> spList = spr.getSubProjects((int) projectRequest.getSession().getAttribute("projectID"));
        ArrayList<SubProject> spTask = spr.getEntireSubProject1((int) projectRequest.getSession().getAttribute("projectID"));

        model.addAttribute("spTask", spTask);
        model.addAttribute("taskList", taskList);
        model.addAttribute("spList", spList);
        return "project";
    }

    @PostMapping("createTaskPost")
    public String createTaskPost(HttpServletRequest taskRequest, HttpServletRequest projectRequest){

        String taskName = taskRequest.getParameter("taskName");
        int taskHours = Integer.parseInt(taskRequest.getParameter("taskHours"));
        int taskEmployees = Integer.parseInt(taskRequest.getParameter("taskEmployees"));
        int projectID = (int) projectRequest.getSession().getAttribute("projectID");
        String startDate = taskRequest.getParameter("startDate");
        int subProjectID = Integer.parseInt(taskRequest.getParameter("subprojects"));

        double calcWorkhours = Math.ceil(((double) taskHours/(double) taskEmployees)/8.0);
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

    @PostMapping("createSubProjectPost")
    public String createSubProjectPost(HttpServletRequest SPrequest, HttpServletRequest projectRequest){
        String subProjectName = SPrequest.getParameter("subProjectName");
        int projectID = (int) projectRequest.getSession().getAttribute("projectID");

        spr.createSubProject(subProjectName, projectID);

        return "redirect:/project";
    }

    @PostMapping("deleteTask")
    public String deleteTask (HttpServletRequest SPrequest){
        int taskID = Integer.parseInt(SPrequest.getParameter("taskToDelete"));
        tr.deleteTask(taskID);

        return "redirect:/project";
    }

    @PostMapping("deleteSPTask")
    public String deleteSPTask (HttpServletRequest SPrequest){
        int spTaskID = Integer.parseInt(SPrequest.getParameter("spTaskToDelete"));
        tr.deleteSPTask(spTaskID);

        return "redirect:/project";
    }

}
