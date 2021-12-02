package controller;

import model.Date;
import model.*;

import java.util.*;
import java.util.regex.Matcher;

public class Controller {
    public static final Controller controller  = new  Controller();

    public int register(String command){
        return 0;
    }

    public int logIn(String command){
        return 0;
    }

    public int printMenu(User user){
        return 0;
    }

    public int changePassword(User user, String command){
        return 0;
    }

    public int changeUserName(User user, String command){
        return 0;
    }

    public ArrayList<String> showTeams(User user){
        ArrayList<String> result = new ArrayList<>();
        HashMap<Team, Date> joiningDate = sortJoiningDates(user.getJoiningDate());
        HashMap<Date, ArrayList<Team>> data = new HashMap<>();
        for (Map.Entry<Team, Date> unit : joiningDate.entrySet()) {
            ArrayList<Team> teams;
            teams = data.get(unit.getValue());
            if (teams == null) {
                teams = new ArrayList<>();
                teams.add(unit.getKey());
                data.put(unit.getValue(), teams);
            } else {
                teams.add(unit.getKey());
            }
        }
        ArrayList<Date> check = new ArrayList<Date>();
        for (Map.Entry<Team, Date> unit : joiningDate.entrySet()) {
            if (check.contains(unit.getValue())) continue;
            check.add(unit.getValue());
            ArrayList<Team> teams = data.get(unit.getValue());
            ArrayList<String> names = new ArrayList<>();
            for (Team team : teams){
                names.add(team.getTeamName());
            }
            Collections.sort(names);
            for (String name  : names) {
                result.add(name);
            }
        }
        return result;
    }

    public int showTeam(User user, String command){
        return 0;
    }

    public ArrayList<User> sortUsersByDate(User user){

        return null;
    }

    public int getDaysBetween(Date date1, Date date2){
        return 0;
    }

    public int editTaskTitle(Task task, String command){
        return 0;
    }

    public int editTaskDescription(Task task, String command){
        return 0;
    }

    public int editTaskPriority(Task task, String command){
        return 0;
    }

    public int editTaskDeadline(Task task, String command){
        return 0;
    }

    public int editAssignedUsers(Task task, String command){
        return 0;
    }

    public void sendMessage(User user, ChatRoom chatRoom, String command){

    }

    public ArrayList<String> showTasks(Team team){
        return null;
    }

    public String showTask(Task task){
        return null;
    }

    public int makeBoard(User user, String command){
        return 0;
    }

    public int removeBoard(User user, String command){
        return 0;
    }

    public int boardDone(User user, String command){
        return 0;
    }

    public int boardAddTask(User user, String command){
        return 0;
    }

    public int boardAssignMember(User user, String command){
        return 0;
    }

    public int forceCategory(User user, String command){
        return 0;
    }

    //public ArrayList<Category> updateColumns(ArrayList<Category> oldColumns, Category category){
    //    return null;
    //}

    public int goToNextCategory(User user, String command){
        return 0;
    }

    public boolean checkDeadline(Task task, Date date){
        return true;
    }

    public int showDoneOrFailed(User user, String command){
        return 0;
    }

    public int updateDeadline(User user, String command){
        return 0;
    }

    public int boardShow(Board board, String command){
        return 0;
    }

    public int showDeadLines(User user, String command){
        return 0;
    }

    public int showTeamsOfLeader(User user){
        return 0;
    }

    public int showSpecialTeam(User user, String command){
        return 0;
    }

    public int creatTeam(User user, String command){
        return 0;
    }

    public int creatTask(User user, Team team, String command){
        return 0;
    }

    public ArrayList<String> showMembers(User user, Team team){
        return null;
    }

    public int addMember(User user, Team team, String command){
        return 0;
    }

    public int deleteMember(User user, Team team, String command){
        return 0;
    }

    public int suspendMember(User user, Team team, String command){
        return 0;
    }

    public int promoteMember(User user, Team team, String command){
        return 0;
    }

    public int assignMember(User user, Team team, String command){
        return 0;
    }

    public ArrayList<String> showScoreBoard(User user, Team team){
        HashMap<User,Integer> scores = sortBoard
                (team.getScoreboard().getScores());
        ArrayList<String> result = new ArrayList<>();
        HashMap<Integer, ArrayList<User>> data = new HashMap<>();
        for (Map.Entry<User,Integer> unit : scores.entrySet()) {
            ArrayList<User> users;
            users = data.get(unit.getValue());
            if (users == null) {
                users = new ArrayList<>();
                users.add(unit.getKey());
                data.put(unit.getValue(), users);
            } else {
                users.add(unit.getKey());
            }
        }
        ArrayList<Integer> check = new ArrayList<Integer>();
        for (Map.Entry<User, Integer> unit : scores.entrySet()) {
            if (check.contains(unit.getValue())) continue;
            check.add(unit.getValue());
            ArrayList<User> users = data.get(unit.getValue());
            ArrayList<String> names = new ArrayList<>();
            for (User user1 : users){
                names.add(user1.getUserName());
            }
            Collections.sort(names);
            for (String name  : names) {
                User user1 = User.getUserByUsername(name);
                int score = scores.get(user1);
                result.add(name+" : "+score);
            }
        }
        return result;

    }

    public int sendNotificationForUser(User sender, User receiver, String command){
        return 0;
    }

    public int sendNotificationForTeam(User sender, Team team, String command){
        return 0;
    }

    public int showProfile(User user){
        return 0;
    }

    public int banUser(User user){
        return 0;
    }

    public int changeRole(User user, String command){
        return 0;
    }

    public int sendNotificationForAll(String command){
        return 0;
    }

    public int showPendingTeams(){
        return 0;
    }

    public int acceptTeam(String command){
        return 0;
    }

    public int rejectTeam(String command){
        return 0;
    }

    public HashMap<User, Integer> sortBoard(HashMap<User, Integer> hashMap){
        List<Map.Entry<User, Integer>> valueList =
                new LinkedList<Map.Entry<User, Integer>>(hashMap.entrySet());
        Comparator comparator = new Comparator<Map.Entry<User, Integer>>() {
            public int compare(Map.Entry<User, Integer> operand1, Map.Entry<User, Integer> operand2) {
                return (operand2.getValue()).compareTo(operand1.getValue());
            }
        };
        Collections.sort(valueList, comparator);

        HashMap<User, Integer> sorted = new LinkedHashMap<User, Integer>();
        for (Map.Entry<User, Integer> unit : valueList) {
            sorted.put(unit.getKey(), unit.getValue());
        }
        return sorted;

    }

    public HashMap<Task, Integer> sortRoadMap(HashMap<Task, Integer> hashMap){
        return null;
    }

    public HashMap<Team,Date> sortJoiningDates (HashMap<Team,Date> hashMap){
        // a random date for comparing to other dates
        Date comparingDate = new Date("1300/01/01");
        List<Map.Entry<Team, Date>> valueList =
                new LinkedList<Map.Entry<Team, Date>>(hashMap.entrySet());
        Comparator comparator = new Comparator<Map.Entry<Team, Date>>() {
            public int compare(Map.Entry<Team, Date> operand1, Map.Entry<Team, Date> operand2) {
                return (Date.getDaysBetween(operand2.getValue(),comparingDate)).compareTo
                        (Date.getDaysBetween(operand1.getValue(),comparingDate));
            }
        };
        Collections.sort(valueList, comparator);

        HashMap<Team, Date> sorted = new LinkedHashMap<Team, Date>();
        for (Map.Entry<Team, Date> unit : valueList) {
            sorted.put(unit.getKey(), unit.getValue());
        }
        return sorted;
    }

    public Matcher getCommandMatcher(String pattern, String input){
        return null;
    }
}
