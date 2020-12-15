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


    //Post mapping for når man trykker på knappen "Register" på register.html
    @PostMapping("registerPost")
    public String registerPost(HttpServletRequest request, RedirectAttributes redirectAttributes){
        UserServices us = new UserServices();
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if(us.registerPasswordMatch(password1, password2)){
            boolean succCreate = ur.createUser(request.getParameter("fullname"), request.getParameter("email"), password1);
            if(succCreate){
                redirectAttributes.addFlashAttribute("createMessage", "Your user has been created, feel free to login");
                return "redirect:/login";
            }
        }else if(!us.registerPasswordMatch(password1, password2)){
            redirectAttributes.addFlashAttribute("failCreateMsg", "Passwords did not match");
            return "redirect:/register";
        }
        redirectAttributes.addFlashAttribute("failCreateMsg", "Email is already in use");
        return "redirect:/register";
    }

    //post mapping for når man trykker på "login" knappen efter at have udfyldt login credentials
    //tjekker at disse credentials matcher med hvad der er i databasen
    @PostMapping("loginPost")
    public String loginPost(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean correctLogin = ur.loginUser(email, password);
        if(correctLogin){
            return "redirect:/dashboard";
        }
        redirectAttributes.addFlashAttribute("loginMessage", "Wrong email and/or password. Try again");
        return "redirect:/login";
    }
}
