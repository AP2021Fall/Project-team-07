package view;

import controller.Controller;
import model.Task;
import model.Team;
import model.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class LeaderMenu {

    public static void runTeamMenu(User user, Team team) {

    }

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
        if (status == 1)
            View.print("There is another team with this name!");
        else if (status == 2)
            View.print("Team name is invalid!");
        else if (status == 3)
            View.print("Team created successfully! Waiting For Admin’s confirmation…");

    }


    public static void showAllTasks(User user, Team team) {
        ArrayList<Task> tasks = Controller.controller.showTasksForLeader(user, team);
        for (Task task : tasks) {
            View.print("" + task.getDateOfCreation() + " " + task.getPriority() + " " + task.getDeadline());
            if (task.getAssignedUser().size() != 0) {
                View.print("------------Related users------------");
                for (User user1 : task.getAssignedUser()) {
                    View.print(user1.getUserName());
                }
            }
        }
    }

    public static void creatTask(User user, Team team, String title, String dateOfCreation, String deadline) throws ParseException {
        int status = Controller.controller.creatTask(user, team, title, dateOfCreation, deadline);
        if (status == 1) {
            View.print("There is another task with this title!");
        } else if (status == 2) {
            View.print("Invalid start date!");
        } else if (status == 3) {
            View.print("Invalid deadline!");
        } else if (status == 4) {
            View.print("Task created successfully!");
        }

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
        ArrayList<User> forPrint = Controller.controller.showScoreBoardForLeader(user, team);
        int rank = 1;
        View.print("Rank Username Score");
        for (User user1 : forPrint) {
            View.print("" + rank + " " + user1.getUserName() + " " + user1.getScore());
        }
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
