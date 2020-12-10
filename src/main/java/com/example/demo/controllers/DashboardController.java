package com.example.demo.controllers;

import com.example.demo.models.Project;
import com.example.demo.models.Task;
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
public class DashboardController {
    ProjectRepository pr = new ProjectRepository();

    //Get Mappingen til dashboard.html. Tilføjer en liste af projekter til viewet.
    @GetMapping("dashboard")
    public String dashboard(Model model){
        ArrayList<Project> listOfProjects = pr.getProjectsFromDB();
        model.addAttribute("listOfProjects", listOfProjects);

        return "dashboard";
    }

    //Post mappingen når man trykker på "CREATE"-project knappen. Denne kalder createNewProject() i ProjectRepository
    //Og giver user input projekt navnet med som parameter
    @PostMapping("createPost")
    public String createPost(HttpServletRequest projectRequest){
        String projectName = projectRequest.getParameter("createProjectName");
        pr.createNewProject(projectName);
        return "redirect:/dashboard";
    }

    //Post mappingen når man vælger et project og trykker "OPEN".
    //Tilføjer projectID til Session sådan at man kan bruge projectID'et i project view
    @PostMapping("projectPost")
    public String projectPost(HttpServletRequest projectRequest){
        String projectIDString = (projectRequest.getParameter("projectID"));
        int projectID = Integer.parseInt(projectIDString.substring(13));

        HttpSession projectSession = projectRequest.getSession();
        projectSession.setAttribute("projectID", projectID);

        return "redirect:/project";
    }

    @PostMapping("deleteProjectPost")
    public String deleteProject(HttpServletRequest request){
        int projectID = Integer.parseInt(request.getParameter("deleteID"));
        pr.deleteProject(projectID);

        return "redirect:/dashboard";
    }

}
