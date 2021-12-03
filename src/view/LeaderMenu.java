package view;

import controller.Controller;
import model.Team;
import model.User;

import java.util.ArrayList;

public class LeaderMenu {

    public static void showTeams(User user) {
        ArrayList<String> teams = Controller.controller.showTeams(user);
        int rank = 1;
        if (teams.size() == 0)
            View.print("There is no team for you!");
        else {
            for (String team : teams) {
                View.print("" + rank + "- " + team);
                rank++;
            }
        }
    }

    //The team must also be sent
    public static void showSpecialTeam(User user, String command) {
        Team team = Controller.controller.showSpecialTeam(user, command);
        if (team == null)
            View.print("Team not found!");
        else {
            TeamMenu teamMenu = new TeamMenu(user);
            teamMenu.runTeamMenu();
        }
    }

    public static void creatTeam(User user, String command) {
        int status = Controller.controller.creatTeam(user, command);
        if(status==1)
            View.print("There is another team with this name!");
        else if (status==2)
            View.print("Team name is invalid!");
        else if (status==3)
            View.print("Team created successfully! Waiting For Admin’s confirmation…");

    }

    public static void runTeamMenu(User user, Team team) {

    }

    public static void showAllTasks(User user, Team team) {

    }

    public static void creatTask(User user, Team team, String command) {

    }

    public static void showMembers(User user, Team team) {
        ArrayList<String> names = Controller.controller.showMembers(user, team);
        for (String name : names) {
            View.print(name);
        }
    }

    public static void addMember(User user, Team team, String command) {
        int status = Controller.controller.addMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username !");
        else if (status == 2) {
            //View.print("User successfully added");
        }
    }

    public static void deleteMember(User user, Team team, String command) {
        int status = Controller.controller.deleteMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            //View.print("User successfully removed");
        }
    }

    public static void suspendMember(User user, Team team, String command) {
        int status = Controller.controller.suspendMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            //View.print("User successfully suspended");
        }

    }

    public static void promoteMember(User user, Team team, String command) {
        int status = Controller.controller.promoteMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            //View.print("The user was promoted to team leader");
        }
    }

    public static void assignMember(User user, Team team, String command1, String command2) {
        int status = Controller.controller.assignMember(user, team, command1, command2);
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

    public static void sendNotificationForUser(User sender, String receiver, String command) {
        int status = Controller.controller.sendNotificationForUser(sender, receiver, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            //View.print("The notification was sent to the user successfully");
        }
    }

    public static void sendNotificationForTeam(User sender, String team, String command) {
        int status = Controller.controller.sendNotificationForTeam(sender, team, command);
        if (status == 1)
            View.print("No team exists with this name !");
        else if (status == 2) {
            //View.print("The notification was sent to the team successfully");
        }
    }
}
