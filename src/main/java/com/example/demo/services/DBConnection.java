package com.example.demo.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public Connection establishConnection() throws SQLException {
        //Opret forbindelse til database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/alphasolutions?serverTimezone=UTC", "user", "password");
        return conn;
    }
}
