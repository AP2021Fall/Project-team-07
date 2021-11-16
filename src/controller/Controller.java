package controller;

import model.ChatRoom;
import model.Task;
import model.Team;
import model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;

public class Controller {

    public int register(String command){
        return 0;
    }

    public int logIn(String command){
        return 0;
    }

    public int printMenu(User user){
        return 0;
    }

    public int changePassword(User user, String command){
        return 0;
    }

    public int changeUserName(User user, String command){
        return 0;
    }

    public int showTeams(User user){
        return 0;
    }

    public int showTeam(User user, String command){
        return 0;
    }

    public ArrayList<User> sortUsersByDate(User user){
        return null;
    }

    public int getDaysBetween(Date date1, Date date2){
        return 0;
    }

    public int editTaskTitle(Task task, String command){
        return 0;
    }

    public int editTaskDescription(Task task, String command){
        return 0;
    }

    public int editTaskPriority(Task task, String command){
        return 0;
    }

    public int editTaskDeadline(Task task, String command){
        return 0;
    }

    public int editAssignedUsers(Task task, String command){
        return 0;
    }

    public void sendMessage(User user, ChatRoom chatRoom, String command){

    }

    public ArrayList<String> showTasks(Team team){
        return null;
    }

    public String showTask(Task task){
        return null;
    }

    public HashMap<User, Integer> sortBoard(HashMap<User, Integer> hashMap){
        return null;
    }

    public HashMap<Task, Integer> sortRoadMap(HashMap<Task, Integer> hashMap){
        return null;
    }

    public Matcher getCommandMatcher(String pattern, String input){
        return null;
    }
}
