package com.example.demo.repositories;

import com.example.demo.services.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserRepository {

    public void createUser(String fullname, String email, String password){
        DBConnection connection = new DBConnection();

        try {
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

    }
}
