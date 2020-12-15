package com.example.demo.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProjectServices {

    //Formaterer datoer fra "yyyy-MM-dd" til "dd/MM/yyyy"
    public String formatDate(String date){
        DateTimeFormatter OLD_FORMAT = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss");
        LocalDate localDate = LocalDate.parse(date, OLD_FORMAT);

        DateTimeFormatter NEW_FORMAT = DateTimeFormatter.ofPattern( "dd/MM/yyyy");
        String result = localDate.format(NEW_FORMAT);

        return result;
    }

    //Udregner slutdato for enhver task der oprettes i et projekt. Her tages hensyn til weekend
    public String calcEndDate(LocalDate date, int workdays){
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

    //Laver LocalDate objekt af dato sådan at isBefore og isAfter kan bruges til at sammenligne datoer
    public LocalDate reverseDate(String date){
        LocalDate localDate = LocalDate.parse(date);
        return localDate;
    }
}
