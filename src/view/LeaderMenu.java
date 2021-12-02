package view;

import controller.Controller;
import model.Team;
import model.User;

import java.util.ArrayList;

public class LeaderMenu {
    Controller controller = new Controller();

    public static void showTeams(User user) {

    }

    public static void showSpecialTeam(User user, String command) {

    }

    public static void creatTeam(User user, String command) {

    }

    public static void runTeamMenu(User user, Team team) {

    }

    public static void showAllTasks(User user, Team team) {

    }

    public static void creatTask(User user, Team team, String command) {

    }

    public static void showMembers(User user, Team team) {
        Controller controller = new Controller();
        ArrayList<String> names = controller.showMembers(user, team);
        for (String name : names) {
            View.print(name);
        }
    }

    public static void addMember(User user, Team team, String command) {
        Controller controller = new Controller();
        int status = controller.addMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username !");
        else if (status == 2) {
            //View.print("User successfully added");
        }
    }

    public static void deleteMember(User user, Team team, String command) {
        Controller controller = new Controller();
        int status = controller.deleteMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            //View.print("User successfully removed");
        }
    }

    public static void suspendMember(User user, Team team, String command) {
        Controller controller = new Controller();
        int status = controller.suspendMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            //View.print("User successfully suspended");
        }

    }

    public static void promoteMember(User user, Team team, String command) {
        Controller controller = new Controller();
        int status = controller.promoteMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            //View.print("The user was promoted to team leader");
        }
    }

    public static void assignMember(User user, Team team, String command1, String command2) {
        Controller controller = new Controller();
        int status = controller.assignMember(user, team, command1, command2);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            View.print("No Task exists with this id!");
        } else if (status == 3) {
            //View.print("User successfully added to activity");
        }

    }

    public static void showScoreBoard(User user, Team team) {

    }

    public static void sendNotificationForUser(User sender, User receiver, String command) {

    }

    public static void sendNotificationForTeam(User sender, Team team, String command) {

    }
}
