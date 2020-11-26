package com.example.demo.services;

public class UserServices {

    public boolean registerPasswordMatch(String password1, String password2){
        if(password1.equals(password2)){
            return true;
        }
        return false;
    }

}
