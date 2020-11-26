package com.example.demo.controllers;

import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("registerPost")

    public String registerPost(HttpServletRequest request){
        UserServices us = new UserServices();
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        if(us.registerPasswordMatch(password1, password2)){
            ur.createUser(request.getParameter("fullname"), request.getParameter("email"), password1);
            return "login";
        }

        return "register";
    }
}
