package view;

import controller.Controller;
import model.Notification;
import model.Team;
import model.User;

import java.util.regex.Matcher;

public class AdminMenu {

    public static void showProfile(String username) {
        int answer = Controller.controller.showProfile(username);
        if (answer == 1) {
            View.print("There is no user with this username");
        } else {
            View.print(User.getUserByUsername(username).getFullName());
            View.print(User.getUserByUsername(username).getUserName());
            View.print(User.getUserByUsername(username).getBirthday().toString());
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
            View.print("User " + username + "banned successfully!");
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

    public static void showScoreBoard() {
        // continue
    }

    public static void showPendingTeams() {
        String input = View.scanner.nextLine().trim();
        Matcher matcher;
        if ((matcher = Controller.controller.getCommandMatcher("accept --teams ([^ ]+)", input)).matches()) {
            // continue
        } else if ((matcher = Controller.controller.getCommandMatcher("reject --teams ([^ ]+)", input)).matches()) {
            // continue
        }
    }

    public static void acceptTeam(String command) {
        // continue
    }

    public static void rejectTeam(String command) {
        // continue
    }
}
