package com.example.demo.controllers;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    UserRepository ur = new UserRepository();
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @GetMapping("register")
    public String register(){
        return "register";
    }

    @GetMapping("dashboard")
    public String dashboard(){
        return "dashboard";
    }

    @PostMapping("registerPost")
    public String registerPost(HttpServletRequest request, RedirectAttributes redirectAttributes){
        UserServices us = new UserServices();
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if(us.registerPasswordMatch(password1, password2)){
            boolean succCreate = ur.createUser(request.getParameter("fullname"), request.getParameter("email"), password1);
            if(succCreate){
                redirectAttributes.addFlashAttribute("createMessage", "Din konto er nu blevet oprettet og kan nu logge ind");
                return "redirect:/login";
            }
        }
        redirectAttributes.addFlashAttribute("failCreateMsg", "Email er allerede i systemet");
        return "redirect:/register";
    }

    @PostMapping("loginPost")
    public String loginPost(HttpServletRequest request, RedirectAttributes redirectAttributes){

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        boolean correctLogin = ur.loginUser(email, password);

        if(correctLogin){
            return "redirect:/dashboard";
        }
        redirectAttributes.addFlashAttribute("loginMessage", "Forkert email eller kodeord. Prøv igen");
        return "redirect:/login";
    }
}
