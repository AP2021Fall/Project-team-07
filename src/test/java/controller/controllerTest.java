package controller;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.reflect.TypeToken;
import model.Date;
import model.Task;
import model.Team;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    public void testYaGson() {
        String username = "mmd";
        String password = "mmd2020";
        String email = "mmd@gmail.com";
        User user = new User(username, password, email);
        Team team = new Team("team1", user, Date.getNow());
        user.getUserTeams().add(team);
        JsonController.getInstance().updateJson();
    }

    @Test
    public void testGson() {
        JsonController.getInstance().readFromJson();
        assertEquals(true, Team.getAllTeams().get(0) == Team.getAcceptedTeams().get(0));
    }

    @Test
    public void testTask() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Team5", Team.getAllTeams());
        assertEquals(false, team.getAllTasks().isEmpty());
    }

    // BoardMenu tests
    @Test
    void testCreatingBoard() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        int response = Controller.controller.makeBoard(user, team, "board");
        assertEquals(2, response);
    }

    @Test
    void testCreatingBoard2() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("Amir");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        int response = Controller.controller.makeBoard(user, team, "board");
        assertEquals(0, response);
    }

    @Test
    void testRemoveBoard() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        int response = Controller.controller.makeBoard(user, team, "board");
        assertEquals(1, response);
    }

    @Test
    void boardAddTask() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        Task task = new Task("task1", new Date("1400/10/05|17:40"), new Date("1400/10/30|17:40"), team);
        Controller.controller.makeBoard(user, team, "board");
        int response = Controller.controller.boardAddTask(user, team, "board",
                Integer.toString(task.getCreationId()));
        assertEquals(7, response);
    }

    @Test
    void checkBoardDone() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        Controller.controller.makeBoard(user, team, "board");
        int response = Controller.controller.boardDone(user, team, "board");
        assertEquals(1, response);
    }
    @Test
    void boardAssignMember(){
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        Controller.controller.makeBoard(user, team, "board");
        int response = Controller.controller.boardAssignMember
                (user,team,"Amir","board","invalid");
        assertEquals(3,response);
    }

    @Test
    void createTeam() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        int response = Controller.controller.creatTeam(user, "Yakuza1");
        assertEquals(1, response);
        int response2 = Controller.controller.creatTeam(user, "Yaku");
        assertEquals(2, response2);
        int response3 = Controller.controller.creatTeam(user, "Yakuza20");
        assertEquals(3, response3);
    }

    @Test
    void createTask() throws ParseException {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        int response = Controller.controller.creatTask(user, team, "Task1", "1400/13/05|17:40", "1400/10/04|17:40");
        assertEquals(2, response);
        int response2 = Controller.controller.creatTask(user, team, "Task1", "1400/10/05|17:40", "1400/13/04|17:40");
        assertEquals(3, response2);
        int response3 = Controller.controller.creatTask(user, team, "Task1", "1400/10/05|17:40", "1400/10/30|17:40");
        assertEquals(4, response3);
    }

    @Test
    void addMember() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        int response = Controller.controller.addMember(user, team, "AmirReza");
        assertEquals(2, response);
    }

    @Test
    void deleteMember() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        int response = Controller.controller.deleteMember(user, team, "AmirReza");
        assertEquals(2, response);
        int response2 = Controller.controller.deleteMember(user, team, "Maryam");
        assertEquals(1, response2);
    }

    @Test
    void suspendMember() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        int response = Controller.controller.suspendMember(user, team, "AmirReza");
        assertEquals(2, response);
        int response2 = Controller.controller.deleteMember(user, team, "Maryam");
        assertEquals(1, response2);
    }

    @Test
    void promoteMember() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        int response = Controller.controller.promoteMember(user, team, "Amir");
        assertEquals(2, response);
        int response2 = Controller.controller.promoteMember(user, team, "Maryam");
        assertEquals(1, response2);
    }

    @Test
    void assignMember() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        int response = Controller.controller.assignMember(user, team, "Task1", "Amir");
        assertEquals(2, response);
//        int response2 = Controller.controller.assignMember(user, team, "Task1","Maryam");
//        assertEquals(1, response);
//        int response3 = Controller.controller.assignMember(user, team, "Task1","Amir");
//        assertEquals(3, response);
    }

    @Test
    void showSpecialTeam() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        Team response = Controller.controller.showSpecialTeam(user, "Yakuza1");
        assertEquals(team, response);
        Team response2 = Controller.controller.showSpecialTeam(user, "Yakuza10");
        assertNull(response2);
    }

    @Test
    void editTaskTitle() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        User user1 = User.getUserByUsername("Amir");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        Task task = Task.getTaskById(team, "1");
        int response = Controller.controller.editTaskTitle(user1, task, "Test");
        assertEquals(1, response);
        int response2 = Controller.controller.editTaskTitle(user, task, "Test");
        assertEquals(0, response2);
    }

    @Test
    void editTaskDescription() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        User user1 = User.getUserByUsername("Amir");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        Task task = Task.getTaskById(team, "1");
        int response = Controller.controller.editTaskDescription(user1, task, "Test");
        assertEquals(1, response);
        int response2 = Controller.controller.editTaskDescription(user, task, "Test");
        assertEquals(0, response2);
    }

    @Test
    void editTaskDeadline() throws ParseException {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        User user1 = User.getUserByUsername("Amir");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        Task task = Task.getTaskById(team, "1");
        int response = Controller.controller.editTaskDeadline(user1, task, "1400/10/01|17:40");
        assertEquals(1, response);
        int response3 = Controller.controller.editTaskDeadline(user1, task, "1400/10/31|17:40");
        assertEquals(1, response3);
        int response4 = Controller.controller.editTaskDeadline(user1, task, "1400/11/04|17:40");
        assertEquals(2, response4);
        int response2 = Controller.controller.editTaskDescription(user, task, "Test");
        assertEquals(0, response2);
    }

    @Test
    void removeAssignedUsers() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        User user1 = User.getUserByUsername("Amir");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        Task task = Task.getTaskById(team, "1");
        int response = Controller.controller.removeAssignedUsers(user1, task, user);
        assertEquals(2, response);
        int response2 = Controller.controller.removeAssignedUsers(user1, task, null);
        assertEquals(1, response2);
        int response3 = Controller.controller.removeAssignedUsers(user, task, user);
        assertEquals(0, response3);
    }

    @Test
    void addAssignedUsers() {
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        User user1 = User.getUserByUsername("Amir");
        Team team = Team.getTeamByName("Yakuza1", Team.getAllTeams());
        Task task = Task.getTaskById(team, "1");
        int response = Controller.controller.removeAssignedUsers(user1, task, user);
        assertEquals(2, response);
        int response2 = Controller.controller.removeAssignedUsers(user1, task, null);
        assertEquals(1, response2);
        int response3 = Controller.controller.removeAssignedUsers(user, task, user);
        assertEquals(0, response3);
    }
    @Test
    void changePassword(){
        JsonController.getInstance().readFromJson();
        User user = User.getUserByUsername("AmirReza");
        int response = Controller.controller.changePassword(user,"invalid","invalid");
        assertEquals(1,response);
    }


}
