package com.example.demo.services;

import java.beans.PropertyEditorSupport;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProjectServices {

    public String formatDate(String date){
        DateTimeFormatter OLD_FORMAT = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss");
        LocalDate localDate = LocalDate.parse(date, OLD_FORMAT);

        DateTimeFormatter NEW_FORMAT = DateTimeFormatter.ofPattern( "dd/MM/yyyy");
        String result = localDate.format(NEW_FORMAT);

        return result;
    }

    public String calcEndDate(LocalDate date, int workdays){
        if (workdays < 1) {
            return formatDate(date.toString());
        }

        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < workdays) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }


        return result.toString();
    }
}
