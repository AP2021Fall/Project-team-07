package view;

import controller.Controller;
import model.Task;
import model.Team;
import model.User;
import controller.Controller.*;
import view.View.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.regex.Matcher;


public class TeamMenu extends Menu {

    public TeamMenu(User user) {
        super(user);
    }


    public void runTeamMenu() throws ParseException {
        showTeams();
        Team team = selectTeam();
        while (true) {
            String input = View.scanner.nextLine().trim();
            if (Controller.controller.getCommandMatcher("Scoreboard --show"
                    , input).matches()) showScoreBoard(team);
            else if (Controller.controller.getCommandMatcher("Roadmap --show"
                    , input).matches()) showRoadMap(team);
            else if (Controller.controller.getCommandMatcher("Chatroom --show"
                    , input).matches()) showChatRoom(team);
            else if (Controller.controller.getCommandMatcher("BoardMenu"
                    , input).matches()) {
                BoardMenu boardMenu = new BoardMenu(user, team);
                boardMenu.runBoardMenu();
            } else if (Controller.controller.getCommandMatcher("Show tasks", input).matches())
                showTasks(team);
            else if (Controller.controller.getCommandMatcher("show task --id (\\d+)", input).matches())
                showTask(input);
            else if (input.equals("back"))
                return;
            else System.out.println("invalid command ");
        }
    }

    private Team selectTeam() {
        Team team = null;
        while (true) {
            String input = View.scanner.nextLine().trim();
            Matcher matcher = Controller.controller.getCommandMatcher
                    ("^Enter team ([^ ]+)$", input);
            if (matcher.matches()) {
                String name = matcher.group(1);
                team = Team.getTeamByName(name, user.getUserTeams());
                if (team == null) {
                    View.print("Team with this name doesn't exist");
                    continue;
                }
                break;
            } else {
                View.print("invalid command");
            }


        }
        return team;
    }

    public void showTeams() {
        ArrayList<String> forPrint = Controller.controller.showTeams(super.user);
        int rank = 1;
        for (String print : forPrint) {
            View.print("" + rank + " " + print);
            rank++;
        }
    }

    public void showScoreBoard(Team team) {
        ArrayList<String> forPrint = Controller.controller.showScoreBoard(super.user, team);
        int rank = 1;
        for (String print : forPrint) {
            View.print("" + rank + " " + print);
            rank++;
        }
    }

    public void showRoadMap(Team team) {
        ArrayList<String> forPrint = Controller.controller.showRoadMap(super.user, team);
        for (String print : forPrint) {
            View.print(print);
        }
    }

    public void showChatRoom(Team team) {
        ArrayList<String> forPrint = Controller.controller.showChatRoom(team);
        for (String print : forPrint) {
            View.print(print);
        }
    }

    public void showTasks(Team team) throws ParseException {
        for (String string : Controller.controller.showTasks(team))
            View.print(string);
    }

    public void showTask(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher("show task --id (\\d+)", command);
        matcher.matches();
        String taskId = matcher.group(1);
        View.print(Controller.controller.showTask(taskId));
    }

}
