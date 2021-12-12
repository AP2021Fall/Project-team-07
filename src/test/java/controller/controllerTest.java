package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class controllerTest {
    @BeforeEach
    public void first(){

    }
    @Test
    public void checkRegister(){
        String username = "mmd";
        String password1 = "mmd2020";
        String password2 = "mmd2021";
        String email = "mmd@gmail.com";
        int response = controller.Controller.controller.register(username,password1,password2,email);
        assertEquals(2,response);
    }
    @Test
    public void checkLogin(){

    }
}
