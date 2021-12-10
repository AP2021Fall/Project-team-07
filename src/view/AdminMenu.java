package view;

import controller.Controller;
import model.Team;
import model.User;

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

    public static void sendNotificationForAll(String command) {

    }

    public static void sendNotificationForUser(User user, String command) {

    }

    public static void sendNotificationForTeam(Team team, String command) {

    }

    public static void showPendingTeams() {

    }

    public static void acceptTeam(String command) {

    }

    public static void rejectTeam(String command) {

    }
}
