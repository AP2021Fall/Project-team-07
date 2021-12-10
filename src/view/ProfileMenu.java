package view;

import controller.Controller;
import model.Log;
import model.Notification;
import model.Team;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;

public class ProfileMenu extends Menu {

    public ProfileMenu(User user) {
        super(user);
    }

    public void runProfileMenu() {
        while (true){
            View.print("Enter your command: ");
            String input = View.scanner.nextLine().trim();
            Matcher matcher;
            if((matcher = Controller.controller.getCommandMatcher("Profile --change --oldPassword ([^ ]+) --newPassword ([^ ]+)"
                    , input)).matches()){
                String oldPassword = matcher.group(1);
                String newPassword = matcher.group(2);
                changePassword(oldPassword, newPassword);
            }
            else if((matcher = Controller.controller.getCommandMatcher("Profile --change --username ([^ ]+)", input)).matches()){
                String newUsername = matcher.group(1);
                changeUserName(newUsername);
            }
            else if(Controller.controller.getCommandMatcher("Profile --showTeams", input).matches()){
                showTeams();
            }
            else if((matcher = Controller.controller.getCommandMatcher("Profile --showTeam ([^ ]+)", input)).matches()){
                String teamName = matcher.group(1);
                showTeam(teamName);
            }
            else if(Controller.controller.getCommandMatcher("Profile --show --myProfile", input).matches()){
                showMyProfile();
            }
            else if(Controller.controller.getCommandMatcher("profile --show logs", input).matches()){
                showLogs();
            }
            else if(Controller.controller.getCommandMatcher("profile --show notifications", input).matches()){
                showNotifications();
            }
            else{
                View.print("Invalid command!");
            }
        }
    }

    public void showProfile() {
        // ???
    }

    public void changePassword(String oldPassword, String newPassword) {
        int answer = Controller.controller.changePassword(user, oldPassword, newPassword);
        if(answer == 1){
            View.print("wrong old password !");
        }
        else if(answer == 2){
            View.print("Please type a New Password !");
        }
        else if(answer == 3){
            View.print("Please Choose A strong Password (Containing at least 8 characters including 1 digit " +
                    "and 1 Capital Letter)");
        }
        else {
            View.print("Change password successfully !");
            View.view.run();
        }
    }

    public void changeUserName(String newUsername) {
        int answer = Controller.controller.changeUserName(user, newUsername);
        if(answer == 1){
            View.print("Your new username must include at least 4 characters!");
        }
        else if(answer == 2){
            View.print("username already taken !");
        }
        else if(answer == 3){
            View.print("New username contains Special Characters! Please remove them and try again");
        }
        else if(answer == 4){
            View.print("you already have this username !");
        }
        else{
            View.print("Change username successfully!");
        }
    }

    // showTeams ???
    public void showTeams() {
        ArrayList<String> teams = Controller.controller.showTeams(user);
        int rank = 1;
        for(String print : teams){
            View.print("" + rank + ". " + print);
            rank++;
        }
    }

    // ask about relation between model and view and member or leader
    public void showTeam(String teamName) {
        int answer = Controller.controller.showTeam(user, teamName);
        if(answer == 0){
            View.print("This team does not exist.");
        }
        else{
            View.print(teamName + " :");
            Team team = Controller.controller.findTeam(teamName);
            View.print(team.getTeamLeader().getUserName());
            if(!user.getUserName().equals(team.getTeamLeader().getUserName())){
                System.out.println(user.getUserName());
            }
            int rank = 1;
            ArrayList<String> teamMembers = new ArrayList<>();
            for (User user : team.getTeamMembers()){
                teamMembers.add(user.getUserName());
            }
            Collections.sort(teamMembers);
            for(String print : teamMembers){
                View.print("" + rank + ". " + print);
            }
        }
    }

    public void showMyProfile() {
        View.print(user.getFullName());
        View.print(user.getUserName());
        View.print(user.getBirthday().toString());
        View.print(user.getEmail());
        View.print(user.getRole());
        View.print(Integer.toString(user.getScore()));
    }

    public void showLogs() {
        int rank = 1;
        for(Log log : user.getAllLogs()){
            View.print("" + rank + ". " + log.toString());
        }
    }

    public void showNotifications() {
        int rank = 1;
        for(Notification notification : user.getNotifications()){
            View.print("" + rank + ". " + notification);
        }
    }
}
