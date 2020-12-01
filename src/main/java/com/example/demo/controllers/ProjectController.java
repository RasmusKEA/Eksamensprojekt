package com.example.demo.controllers;

import com.example.demo.repositories.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProjectController {
    ProjectRepository pr = new ProjectRepository();
    @GetMapping("project")
    public String project(){
        return "project";
    }

    @PostMapping("createTaskPost")
    public String createTaskPost(HttpServletRequest taskRequest, HttpServletRequest projectRequest){

        String taskName = taskRequest.getParameter("taskName");
        int taskHours = Integer.parseInt(taskRequest.getParameter("taskHours"));
        int taskEmployees = Integer.parseInt(taskRequest.getParameter("taskEmployees"));
        int projectID = (int) projectRequest.getSession().getAttribute("projectID");

        pr.createTasks(taskName, taskHours, taskEmployees, projectID);


        return "redirect:/project";
    }
}
