package com.example.demo.controllers;

import com.example.demo.models.Task;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.services.ProjectServices;
import org.apache.tomcat.jni.Local;
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
        model.addAttribute("taskList", taskList);
        return "project";
    }

    @PostMapping("createTaskPost")
    public String createTaskPost(HttpServletRequest taskRequest, HttpServletRequest projectRequest){

        String taskName = taskRequest.getParameter("taskName");
        int taskHours = Integer.parseInt(taskRequest.getParameter("taskHours"));
        int taskEmployees = Integer.parseInt(taskRequest.getParameter("taskEmployees"));
        int projectID = (int) projectRequest.getSession().getAttribute("projectID");
        String startDate = taskRequest.getParameter("startDate");

        double calcWorkhours = Math.ceil(((double) taskHours/(double) taskEmployees)/8.0);
        int workhours = (int) calcWorkhours;


        LocalDate dateObj = LocalDate.parse(startDate);
        String endDate = ps.calcEndDate(dateObj, workhours);

        pr.createTasks(taskName, taskHours, taskEmployees, projectID, startDate, endDate);

        return "redirect:/project";
    }

    @PostMapping("createSubProjectPost")
    public String createSubProjectPost(HttpServletRequest SPrequest, HttpServletRequest projectRequest){
        String subProjectName = SPrequest.getParameter("subProjectName");
        int projectID = (int) projectRequest.getSession().getAttribute("projectID");

        pr.createSubProject(subProjectName, projectID);


        return "redirect:/project";
    }

}
