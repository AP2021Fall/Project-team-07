package view;

import controller.Controller;
import model.Task;
import model.Team;
import model.User;
import controller.Controller.*;
import view.View.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.regex.Matcher;


public class TeamMenu extends Menu{

    public TeamMenu(User user) {
        super(user);
    }


    public void runTeamMenu(){
        showTeams();
        Team team = selectTeam();
        while (true){
            String input = View.scanner.nextLine().trim();
            if (Controller.controller.getCommandMatcher("Scoreboard --show"
                    ,input).matches())showScoreBoard(team);
            // here for continue
        }
    }

    private Team selectTeam() {
        Team team = null;
        while (true){
            String input = View.scanner.nextLine().trim();
            Matcher matcher = Controller.controller.getCommandMatcher
                    ("^Enter Team ([^ ]+)$",input);
            if (matcher.matches()){
                String name = matcher.group(1);
                team = Team.getTeamByName(name,user.getUserTeams());
                if(team == null){
                    View.print("Team with this name doesn't exist");
                    continue;
                }
                break;
            }
            else { View.print("invalid command");}


        }
        return team;
    }

    public void showTeams(){
        ArrayList<String> forPrint = Controller.controller.showTeams(super.user);
        int rank = 1;
        for (String print : forPrint ){
            View.print(""+rank+" "+print);
        }
    }

    public void showScoreBoard(Team team){
        ArrayList<String> forPrint = Controller.controller.showScoreBoard(super.user,team);
        int rank = 1;
        for (String print : forPrint ){
            View.print(""+rank+" "+print);
        }
    }

    public void showRoadMap(Team team){

    }

    public void showChatRoom(Team team){

    }

    public void showTasks(Team team){

    }

    public void showTask(Task task){

    }

}
