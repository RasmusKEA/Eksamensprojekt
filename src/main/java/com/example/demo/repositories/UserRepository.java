package com.example.demo.repositories;

import com.example.demo.services.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserRepository {

    //gemmer en brugers oplysninger i databasen
    public boolean createUser(String fullname, String email, String password){
        DBConnection connection = new DBConnection();

        try {
            PreparedStatement ps1 = connection.establishConnection().prepareStatement("SELECT email FROM users");
            ResultSet rs = ps1.executeQuery();

            while (rs.next()){
                if(email.equals(rs.getString(1))){
                    return false;
                }
            }
            PreparedStatement ps = connection.establishConnection().prepareStatement("INSERT INTO users (fullname, email, password) VALUES (?, ?, ?)");
            ps.setString(1,fullname);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
        } catch (SQLException e) {
            if(e instanceof SQLIntegrityConstraintViolationException){
                System.out.println("Email is already in use " + e.getMessage());
            }
            e.printStackTrace();
        }
        return true;
    }

    //tjekker at email og password stemmer hvad der er i databasen
    public boolean loginUser(String email, String password){
        DBConnection connection = new DBConnection();

        try {
            PreparedStatement ps = connection.establishConnection().prepareStatement("SELECT password FROM users WHERE email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                if(rs.getString(1).equals(password)){
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
