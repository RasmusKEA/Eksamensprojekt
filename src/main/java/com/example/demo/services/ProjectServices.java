package com.example.demo.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProjectServices {

    public String formatDate(String date){
        DateTimeFormatter OLD_FORMAT = DateTimeFormatter.ofPattern( "yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, OLD_FORMAT);

        DateTimeFormatter NEW_FORMAT = DateTimeFormatter.ofPattern( "dd/MM/yyyy");
        String result = localDate.format(NEW_FORMAT);

        return result;
    }
}
