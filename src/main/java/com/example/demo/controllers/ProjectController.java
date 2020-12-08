package com.example.demo.controllers;

import com.example.demo.models.SubProject;
import com.example.demo.models.Task;
import com.example.demo.repositories.ProjectRepository;
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

    @GetMapping("project")
    public String project(Model model, HttpServletRequest projectRequest){

        ArrayList<Task> taskList = pr.getTasksByProjectID((int) projectRequest.getSession().getAttribute("projectID"));
        ArrayList<SubProject> spList = pr.getSubProjects((int) projectRequest.getSession().getAttribute("projectID"));
        ArrayList<SubProject> spTask = pr.getEntireSubProject1((int) projectRequest.getSession().getAttribute("projectID"));

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
        String test = taskRequest.getParameter("subprojects");
        System.out.println(test);
        int subProjectID = Integer.parseInt(taskRequest.getParameter("subprojects"));

        double calcWorkhours = Math.ceil(((double) taskHours/(double) taskEmployees)/8.0);
        int workhours = (int) calcWorkhours;


        LocalDate dateObj = LocalDate.parse(startDate);
        String endDate = ps.calcEndDate(dateObj, workhours);

        pr.createTasks(taskName, taskHours, taskEmployees, projectID, startDate, endDate, subProjectID);

        return "redirect:/project";
    }

    @PostMapping("createSubProjectPost")
    public String createSubProjectPost(HttpServletRequest SPrequest, HttpServletRequest projectRequest){
        String subProjectName = SPrequest.getParameter("subProjectName");
        int projectID = (int) projectRequest.getSession().getAttribute("projectID");

        pr.createSubProject(subProjectName, projectID);

        return "redirect:/project";
    }

    @PostMapping("deleteTask")
    public String deleteTask (HttpServletRequest SPrequest){
        int taskID = Integer.parseInt(SPrequest.getParameter("test"));

        pr.deleteTask(taskID);

        return "redirect:/project";
    }

}
