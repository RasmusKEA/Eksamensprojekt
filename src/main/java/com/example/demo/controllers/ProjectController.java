package com.example.demo.controllers;

import com.example.demo.models.Task;
import com.example.demo.repositories.ProjectRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ProjectController {
    ProjectRepository pr = new ProjectRepository();
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date date;

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
        String date = taskRequest.getParameter("startDate");
        System.out.println(date);

        pr.createTasks(taskName, taskHours, taskEmployees, projectID);

        return "redirect:/project";
    }


}
