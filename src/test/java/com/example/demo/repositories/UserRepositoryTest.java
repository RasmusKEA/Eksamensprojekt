package com.example.demo.repositories;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    @Test
    void createUser() {
        //arrange
        UserRepository ur = new UserRepository();

        //act
        boolean result = ur.createUser("Test User", "test@test.dk", "password");

        //assert
        assertEquals(true, result);
    }

    @Test
    void loginUser() {
        //arrange
        UserRepository ur = new UserRepository();

        //act
        boolean result = ur.loginUser("rasm937k@gmail.com", "test");

        //assert
        assertEquals(true, result);
    }
}