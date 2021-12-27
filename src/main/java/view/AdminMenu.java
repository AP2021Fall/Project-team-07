package view;

import controller.Controller;
import model.Notification;
import model.Team;
import model.User;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class AdminMenu {

    public static void showProfile(String username) {
        int answer = Controller.controller.showProfile(username);
        if (answer == 1) {
            View.print("There is no user with this username");
        } else {
            View.print(User.getUserByUsername(username).getUserName());
            View.print(User.getUserByUsername(username).getEmail());
            View.print(User.getUserByUsername(username).getRole());
            View.print(Integer.toString(User.getUserByUsername(username).getScore()));
        }
    }

    public static void banUser(String username) {
        int answer = Controller.controller.banUser(username);
        if (answer == 1) {
            View.print("There is no user with this username");
        } else {
            View.print("User " + username + " banned successfully!");
        }
    }

    public static void changeRole(String username, String role) {
        int answer = Controller.controller.changeRole(username, role);
        if (answer == 1) {
            View.print("There is no user with this username");
        } else {
            View.print("Role of user " + username + " changed successfully!");
        }
    }

    public static void sendNotificationForAll(String text, User sender) {
        Notification notification = new Notification(text, sender, 0);
        for (User user : User.getUsers()) {
            user.getNotifications().add(notification);
        }
    }

    public static void sendNotificationForUser(String username, String text, User sender) {
        int answer = Controller.controller.sendNotificationForUser(username);
        if (answer == 1) {
            View.print("There is no user with this username");
        } else {
            Notification notification = new Notification(text, sender, 0);
            User.getUserByUsername(username).getNotifications().add(notification);
        }
    }

    public static void sendNotificationForTeam(String teamName, String text, User sender) {
        int answer = Controller.controller.sendNotificationForTeam(teamName);
        if (answer == 1) {
            View.print("There is no team with this name");
        } else {
            Notification notification = new Notification(text, sender, 1);
            for (User user : Controller.controller.findTeam(teamName).getTeamMembers()) {
                user.getNotifications().add(notification);
            }
        }
    }

    public static void showScoreBoard(User user, String teamName) {
        ArrayList<String> forPrint = Controller.controller.showScoreBoard(user, Controller.controller.findTeam(teamName));
        int rank = 1;
        for (String print : forPrint) {
            View.print("" + rank + "_ " + print);
            rank++;
        }
    }


    public static void showPendingTeams() {
        int answer = Controller.controller.showPendingTeams();
        if (answer == 1) {
            View.print("There are no Teams in Pending Status!\n");
        } else {
            int rank = 1;
            for (Team team : Team.getPendingTeams()) {
                View.print("" + rank + ". " + team.getTeamName());
                rank++;
            }
            String input = View.scanner.nextLine().trim();
            Matcher matcher;
            if ((matcher = Controller.controller.getCommandMatcher("accept --teams ([A-Za-z0-9 ]+)", input)).matches()) {
                acceptTeam(matcher.group(1));
            } else if ((matcher = Controller.controller.getCommandMatcher("reject --teams ([A-Za-z0-9 ]+)", input)).matches()) {
                rejectTeam(matcher.group(1));
            }
        }
    }

    public static void acceptTeam(String teamName) {
        int answer = Controller.controller.acceptTeam(teamName);
        if (answer == 1) {
            View.print("Some teams are not in pending status! Try again");
        } else {
            View.print("Teams accepted successfully!");
        }
    }

    public static void rejectTeam(String teamName) {
        int answer = Controller.controller.rejectTeam(teamName);
        if (answer == 1) {
            View.print("Some teams are not in pending status! Try again");
        } else {
            View.print("Teams rejected successfully!");
        }
    }
}