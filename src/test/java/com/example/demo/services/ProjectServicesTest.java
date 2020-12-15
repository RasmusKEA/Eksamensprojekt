package com.example.demo.services;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProjectServicesTest {

    @Test
    void formatDate() {
        // arrange
        ProjectServices ps = new ProjectServices();
        // act
        String result = ps.formatDate("2020-12-12 00:00:00");
        // assert
        assertEquals("12/12/2020", result);
    }

    @Test
    void calcEndDate() {
        // arrange
        ProjectServices ps = new ProjectServices();
        // act
        LocalDate date = LocalDate.of(2020, 12, 14);
        String endDate = date.plusDays(3).toString();
       String result = ps.calcEndDate(date, 3);
        // assert
        assertEquals(endDate, result);
    }

    @Test
    void reverseDate() {
        // arrange
        ProjectServices ps = new ProjectServices();
        // act
        LocalDate result = ps.reverseDate("2020-12-12");
        LocalDate date = LocalDate.of(2020, 12, 12);
        // assert
        assertEquals(date, result);
    }
}