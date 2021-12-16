package controller;

import model.Date;
import model.Team;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class controllerTest {
    @BeforeEach
    public void first() {

    }

    @Test
    public void checkRegister() {
        String username = "mmd";
        String password1 = "mmd2020";
        String password2 = "mmd2021";
        String email = "mmd@gmail.com";
        int response = controller.Controller.controller.register(username, password1, password2, email);
        assertEquals(1, response);
    }

    @Test
    public void checkLogin() throws ParseException {
        String username = "mmd";
        String password = "mmd2020";
        String email = "mmd@gmail.com";
        new User(username, password, email);
        int response1 = controller.Controller.controller.logIn(username, "mmd2021");
        assertEquals(2, response1);
        int response2 = controller.Controller.controller.logIn("mmd2", password);
        assertEquals(1, response2);
    }
    @Test public void testYaGson (){
        String username = "mmd";
        String password = "mmd2020";
        String email = "mmd@gmail.com";
        User user = new User(username, password, email);
        Team team = new Team("team1",user, Date.getNow());
        user.getUserTeams().add(team);
        JsonController.getInstance().updateJson();
    }
}
