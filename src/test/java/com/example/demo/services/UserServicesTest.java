package com.example.demo.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServicesTest {

    @Test
    void registerPasswordMatch() {
        //arrange
        UserServices us = new UserServices();

        //act
        boolean result = us.registerPasswordMatch("passwordTest", "passwordTest");

        //assert
        assertEquals(true, result);
    }
}