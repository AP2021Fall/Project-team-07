package controller;

import model.Date;
import model.*;

import java.util.*;
import java.util.regex.Matcher;

public class Controller {

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
        return null;
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

    public int showScoreBoard(User user, Team team){
        return 0;
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
        return null;
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
