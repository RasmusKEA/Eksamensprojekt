package com.example.demo.services;

public class UserServices {

    //tjekker at de to passwords der bruges til at oprette en konto er ens
    public boolean registerPasswordMatch(String password1, String password2){
        if(password1.equals(password2)){
            return true;
        }
        return false;
    }

}
