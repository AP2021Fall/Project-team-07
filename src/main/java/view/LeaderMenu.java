package view;

import controller.Controller;
import model.Task;
import model.Team;
import model.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;

public class LeaderMenu {
    public static final LeaderMenu leaderMenu = new LeaderMenu();
    private Team team;

    public void runSpecialCommandsForLeaderMenu(User user) throws ParseException {
        while (true) {
            String input = View.scanner.nextLine().trim();
            if (Controller.controller.getCommandMatcher("show --teams", input).matches()) {
                showTeams(user);
            } else if (Controller.controller.getCommandMatcher("show --team ([^ ]+)", input).matches()) {
                showSpecialTeam(user, Controller.controller.getCommandMatcher("show --team ([^ ]+)", input).group(1));
            } else if (Controller.controller.getCommandMatcher("create --team ([^ ]+)", input).matches()) {
                creatTeam(user, Controller.controller.getCommandMatcher("create --team ([^ ]+)", input).group(1));
            } else if (Controller.controller.getCommandMatcher("sudo show  --all --tasks", input).matches()) {
                showAllTasks(user, team);
            } else if (Controller.controller.getCommandMatcher("create task --title ([^ ]+) --startTime ([^ ]+) --deadline ([^ ]+)", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("create task --title ([^ ]+) --startTime ([^ ]+) --deadline ([^ ]+)", input);
                creatTask(user, team, matcher.group(1), matcher.group(2), matcher.group(3));
            } else if (Controller.controller.getCommandMatcher("show --members", input).matches()) {
                showMembers(user, team);
            } else if (Controller.controller.getCommandMatcher("Add member --username ([^ ]+)", input).matches()) {
                addMember(user, team, Controller.controller.getCommandMatcher("Add member --username ([^ ]+)", input).group(1));
            } else if (Controller.controller.getCommandMatcher("delete member --username ([^ ]+)", input).matches()) {
                deleteMember(user, team, Controller.controller.getCommandMatcher("delete member --username ([^ ]+)", input).group(1));
            } else if (Controller.controller.getCommandMatcher("suspend member --username ([^ ]+)", input).matches()) {
                suspendMember(user, team, Controller.controller.getCommandMatcher("suspend member --username ([^ ]+)", input).group(1));
            } else if (Controller.controller.getCommandMatcher("promote --username ([^ ]+) --rate ([^ ]+)", input).matches()) {
                promoteMember(user, team, Controller.controller.getCommandMatcher("promote --username ([^ ]+) --rate ([^ ]+)", input).group(1));
            } else if (Controller.controller.getCommandMatcher("assign member --task ([^ ]+) --username ([^ ]+)", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("assign member --task ([^ ]+) --username ([^ ]+)", input);
                assignMember(user, team, matcher.group(1), matcher.group(2));
            } else if (Controller.controller.getCommandMatcher("show --scoreboard", input).matches()) {
                showScoreBoard(user, team);
            } else if (Controller.controller.getCommandMatcher("send --notification (.+) --user ([^ ]+)", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("send --notification (.+) --user ([^ ]+)", input);
                sendNotificationForUser(user, matcher.group(2), matcher.group(1));
            } else if (Controller.controller.getCommandMatcher("send --notification (.+) --team ([^ ]+)", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("send --notification (.+) --team ([^ ]+)", input);
                sendNotificationForTeam(user, matcher.group(2), matcher.group(1));
            }
            else if (input.equals("back"))
                return;
            else {
                View.print("Invalid command!");
            }
        }
    }

    public void showTeams(User user) {
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
    public void showSpecialTeam(User user, String command) throws ParseException {
        Team foundTeam = Controller.controller.showSpecialTeam(user, command);
        if (foundTeam == null)
            View.print("Team not found!");
        else {
            this.team = foundTeam;
        }
    }

    public void creatTeam(User user, String command) {
        int status = Controller.controller.creatTeam(user, command);
        if (status == 1)
            View.print("There is another team with this name!");
        else if (status == 2)
            View.print("Team name is invalid!");
        else if (status == 3)
            View.print("Team created successfully! Waiting For Admin’s confirmation…");

    }


    public void showAllTasks(User user, Team team) {
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

    public void creatTask(User user, Team team, String title, String dateOfCreation, String deadline) throws ParseException {
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

    public void showMembers(User user, Team team) {
        ArrayList<String> names = Controller.controller.showMembers(user, team);
        for (String name : names) {
            View.print(name);
        }
    }

    public void addMember(User user, Team team, String command) {
        int status = Controller.controller.addMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username !");
        else if (status == 2) {
            View.print("User successfully added");
        }
    }

    public void deleteMember(User user, Team team, String command) {
        int status = Controller.controller.deleteMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            View.print("User successfully removed");
        }
    }

    public void suspendMember(User user, Team team, String command) {
        int status = Controller.controller.suspendMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            View.print("User successfully suspended");
        }

    }

    public void promoteMember(User user, Team team, String command) {
        int status = Controller.controller.promoteMember(user, team, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            View.print("The user was promoted to team leader");
        }
    }

    public void assignMember(User user, Team team, String command1, String command2) {
        int status = Controller.controller.assignMember(user, team, command1, command2);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            View.print("No Task exists with this id!");
        } else if (status == 3) {
            View.print("User successfully added to activity");
        }

    }

    public void showScoreBoard(User user, Team team) {
        ArrayList<User> forPrint = Controller.controller.showScoreBoardForLeader(user, team);
        int rank = 1;
        View.print("Rank Username Score");
        for (User user1 : forPrint) {
            View.print("" + rank + " " + user1.getUserName() + " " + user1.getScore());
        }
    }

    public void sendNotificationForUser(User sender, String receiver, String command) {
        int status = Controller.controller.sendNotificationForUser(sender, receiver, command);
        if (status == 1)
            View.print("No user exists with this username!");
        else if (status == 2) {
            View.print("The notification was sent to the user successfully");
        }
    }

    public void sendNotificationForTeam(User sender, String team, String command) {
        int status = Controller.controller.sendNotificationForTeam(sender, team, command);
        if (status == 1)
            View.print("No team exists with this name !");
        else if (status == 2) {
            View.print("The notification was sent to the team successfully");
        }
    }
}
