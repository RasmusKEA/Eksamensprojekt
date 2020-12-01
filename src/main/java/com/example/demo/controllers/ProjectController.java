package com.example.demo.controllers;

import com.example.demo.models.Project;
import com.example.demo.models.User;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class ProjectController {
    UserRepository ur = new UserRepository();
    ProjectRepository pr = new ProjectRepository();

    @GetMapping("dashboard")
    public String dashboard(Model model){
        ArrayList<Project> listOfProjects = pr.getProjectsFromDB();
        model.addAttribute("listOfProjects", listOfProjects);

        return "dashboard";
    }

    @PostMapping("createPost")
    public String createPost(HttpServletRequest projectRequest){
        String projectName = projectRequest.getParameter("createProjectName");
        pr.createNewProject(projectName);
        return "redirect:/dashboard";
    }
}
