package controller;

import model.Date;
import model.*;
import view.TeamMenu;
import view.View;

import java.net.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public static final Controller controller = new Controller();

    public int register(String username, String password1, String password2, String email) {
        if (isUsernameAvailable(username)) {
            return 1;
        } else if (!password1.equals(password2)) {
            return 2;
        } else if (isEmailAvailable(email)) {
            return 3;
        } else if (!getCommandMatcher("[A-Za-z0-9.]+(@gmail.com|@yahoo.com)", email).matches()) {
            return 4;
        }
        User user = new User(username, password1, email);
        DateTimeFormatter currentDate = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Log log = new Log(user, currentDate.format(now));
        return 0;
    }

    public int logIn(String username, String password) throws ParseException {
        if (!isUsernameAvailable(username)) {
            return 1;
        } else if (!password.equals(User.getUserByUsername(username).getPassword())) {
            return 2;
        }
        String role = User.getUserByUsername(username).getRole();
        if (role.equals("Member")) {
            View.runMemberMenu(User.getUserByUsername(username));
        } else if (role.equals("Leader")) {
            View.runLeaderMenu(User.getUserByUsername(username));
        } else if (role.equals("System Admin")) {
            View.runAdminMenu(User.getUserByUsername(username));
        }
        return 0;
    }

    public int printMenu(User user) {
        if (user.getRole().equals("Member")) {
            return 1;
        } else if (user.getRole().equals("Leader")) {
            return 2;
        } else if (user.getRole().equals("System Admin")) {
            return 3;
        }
        return 0;
    }

    public int changePassword(User user, String oldPassword, String newPassword) {
        if (!oldPassword.equals(user.getPassword())) {
            return 1;
        } else if (newPassword.equals(oldPassword)) {
            return 2;
        } else if (!getCommandMatcher("(?=.*[A-Z])(?=.*\\d)(?!.*[&%$]).{8,}", newPassword).matches()) {
            return 3;
        }
        return 0;
    }

    public int changeUserName(User user, String newUsername) {
        if (!getCommandMatcher("[A-Za-z]{4,}", newUsername).matches()) {
            return 1;
        } else if (isUsernameAvailable(newUsername)) {
            return 2;
        } else if (!getCommandMatcher("[A-Za-z0-9_]+", newUsername).matches()) {
            return 3;
        } else if (newUsername.equals(user.getUserName())) {
            return 4;
        }
        return 0;
    }

    public ArrayList<String> showTeams(User user) {
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
            for (Team team : teams) {
                names.add(team.getTeamName());
            }
            Collections.sort(names);
            for (String name : names) {
                result.add(name);
            }
        }
        return result;
    }

    public int showTeam(User user, String teamName) {
        for (Team team : Team.getAllTeams()) {
            if (teamName.equals(team.getTeamName())) {
                return 1;
            }
        }
        return 0;
    }

    public ArrayList<User> sortUsersByDate(User user) {

        return null;
    }

    public int editTaskTitle(User user, Task task, String command) {
        if (!user.getRole().equals("Leader"))
            return 0;
        else {
            task.setTitle(command);
            return 1;
        }
    }

    public int editTaskDescription(User user, Task task, String command) {
        if (!user.getRole().equals("Leader"))
            return 0;
        else {
            task.setDescription(command);
            return 1;
        }
    }

    public int editTaskPriority(User user, Task task, String command) {
        if (!user.getRole().equals("Leader"))
            return 0;
        else {
            task.setPriority(command);
            return 1;
        }
    }

    public int editTaskDeadline(User user, Task task, String command) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd|HH:mm");
        Matcher matcher = getCommandMatcher("([\\d]{4})-([\\d]{2})-([\\d]{2})[|]([\\d]{2}):([\\d]{2})", command);
        matcher.matches();
        if (!user.getRole().equals("Leader"))
            return 0;
        else if (Integer.parseInt(matcher.group(2)) > 12 ||
                Integer.parseInt(matcher.group(2)) < 0 ||
                (Integer.parseInt(matcher.group(2)) > 6 && Integer.parseInt(matcher.group(3)) > 30) ||
                (Integer.parseInt(matcher.group(2)) <= 6 && Integer.parseInt(matcher.group(3)) > 31) ||
                Integer.parseInt(matcher.group(4)) > 24 ||
                Integer.parseInt(matcher.group(5)) > 60
        )
            return 1;
        else if (new Date(command).getDate().before(task.getDeadline().getDate()))
            return 1;
        else {
            task.setDeadline(new Date(command));
            return 2;
        }
    }

    public int removeAssignedUsers(User user, Task task, User userForEdit) {
        if (!user.getRole().equals("Leader"))
            return 0;
        else if (userForEdit == null)
            return 1;
        else {
            task.getAssignedUser().remove(userForEdit);
            return 2;
        }
    }

    public int addAssignedUsers(User user, Task task, User userForEdit) {
        if (!user.getRole().equals("Leader"))
            return 0;
        else if (userForEdit == null)
            return 1;
        else {
            task.getAssignedUser().add(userForEdit);
            return 2;
        }
    }

    public void sendMessage(User user, ChatRoom chatRoom, String command) {

    }

    public ArrayList<String> showTasks(Team team) {
        return null;
    }

    public String showTask(Task task) {
        return null;
    }

    public int makeBoard(User user, Team team, String boardName) {
        if (!user.getRole().equals("Leader"))
            return 0;
        Board board = Board.getBoardByName(team.getBoards(), boardName);
        if (board != null)
            return 1;
        else {
            board = new Board(boardName, user, team);
            return 2;
        }
    }

    public int removeBoard(User user, Team team, String boardName) {
        if (!user.getRole().equals("Leader"))
            return 0;
        Board board = Board.getBoardByName(team.getBoards(), boardName);
        if (board == null)
            return 1;
        else {
            team.getBoards().remove(board);
            return 2;
        }
    }

    public int addCategory(User user, Team team, String boardName, String categoryName) {
        if (!user.getRole().equals("Leader"))
            return 0;
        Board board = Board.getBoardByName(team.getBoards(), boardName);
        if (board == null)
            return 1;
        Category category = Category.getCategoryByName(board.getAllCategories(), categoryName);
        if (category != null)
            return 2;
        else {
            category = new Category(board, categoryName);
            return 3;
        }
    }

    public int changeColumn(User user, Team team, String boardName, String categoryName, int column) {
        if (!user.getRole().equals("Leader"))
            return 0;
        Board board = Board.getBoardByName(team.getBoards(), boardName);
        if (board == null)
            return 1;
        Category category = Category.getCategoryByName(board.getAllCategories(), categoryName);
        if (category == null)
            return 2;
        if (column < 0 || column > board.getAllCategories().size() - 1)
            return 3;
        else {
            board.setAllCategories(changeArrangement(board.getAllCategories(), category, column));
            return 4;
        }

    }

    public ArrayList<Category> changeArrangement(ArrayList<Category> categories, Category category, int column) {
        ArrayList<Category> rearranged = new ArrayList<>();
        categories.remove(category);
        for (int i = 0; i < column; i++) {
            rearranged.add(categories.get(i));
        }
        rearranged.add(category);
        for (int i = column; i < categories.size(); i++) {
            rearranged.add(categories.get(i));
        }
        return rearranged;
    }

    public int boardDone(User user, Team team, String boardName) {
        if (!user.getRole().equals("Leader"))
            return 0;
        Board board = Board.getBoardByName(team.getBoards(), boardName);
        if (board == null)
            return 1;
        if (board.getAllCategories().isEmpty())
            return 2;
        else {
            board.setCreated(true);
            return 3;
        }


    }

    public int boardAddTask(User user, Team team, String boardName, String taskId) {
        if (!user.getRole().equals("Leader"))
            return 0;
        Board board = Board.getBoardByName(team.getBoards(), boardName);
        if (board == null)
            return 1;
        if (!board.isCreated())
            return 2;
        Task task = Task.getTaskById(team, taskId);
        if (task == null)
            return 3;
        if (board.getBoardTask().contains(task))
            return 4;
        if (Date.getTimeBetween(Date.getNow(), task.getDeadline()) < 0)
            return 5;
        if (task.getAssignedUser().isEmpty())
            return 6;
        else {
            board.getBoardTask().add(task);
            return 7;
        }
    }


    public int boardAssignMember(User user, Team team, String username, String boardName, String taskId) {
        if (!user.getRole().equals("Leader"))
            return 0;
        Board board = Board.getBoardByName(team.getBoards(), boardName);
        if (board == null)
            return 1;
        if (!board.isCreated())
            return 2;
        Task task = Task.getTaskById(team, taskId);
        if (task == null)
            return 3;
        if (!board.getBoardTask().contains(task))
            return 3;
        User user1 = User.getUserByUsername(username);
        if (user1 == null)
            return 4;
        if (board.getDone().contains(task))
            return 5;
        else {
            if(!task.getAssignedUser().contains(user1)){
                task.getAssignedUser().add(user1);
            }
            return 6;
        }
    }

    public int forceCategory(User user,Team team,String categoryName ,String boardName,String taskTitle) {
        if (!user.getRole().equals("Leader"))
            return 0;
        Board board = Board.getBoardByName(team.getBoards(),boardName);
        if (board == null)
            return 1;
        if (!board.isCreated())
            return 2;
        Task task = Task.getTaskByTitle(board.getBoardTask(),taskTitle);
        if (task == null)
            return 3;
        if (!board.getBoardTask().contains(task))
            return 3;
        if (Date.getTimeBetween(Date.getNow(), task.getDeadline()) < 0)
            return 4;
        Category category = Category.getCategoryByName(board.getAllCategories(),categoryName);
        if (category == null)
            return 5;
        else {
            removeFromCategories(task,board);
            category.getCategoryTasks().add(task);
            return 6;
        }
    }

    private void removeFromCategories(Task task, Board board) {
        for(Category category : board.getAllCategories()){
            if (category.getCategoryTasks().contains(task))
                category.getCategoryTasks().remove(task);
        }
    }

    //public ArrayList<Category> updateColumns(ArrayList<Category> oldColumns, Category category){
    //    return null;
    //}

    public int goToNextCategory(User user, String command) {
        return 0;
    }

    public boolean checkDeadline(Task task, Date date) {
        return true;
    }

    public int showDoneOrFailed(User user, String command) {
        return 0;
    }

    public int updateDeadline(User user, String command) {
        return 0;
    }

    public int boardShow(Board board, String command) {
        return 0;
    }

    public ArrayList<String> showChatRoom(Team team) {
        ArrayList<String> result = new ArrayList<>();
        for (Message message : team.getChatRoom().getAllMassages()) {
            result.add(message.getSender().getUserName() + " : \"" + message.getText() + "\"");
        }
        return result;
    }


    public ArrayList<java.util.Date> showDeadLines(User user) throws ParseException {

        ArrayList<java.util.Date> allTaskDate = new ArrayList<>();
        for (Task task : user.getAllTasksForUser()) {
            allTaskDate.add(task.getDeadline().getDate());
        }
        Collections.sort(allTaskDate);
        HashMap<String, java.util.Date> allTask = new HashMap<>();
        for (Task task : user.getAllTasksForUser()) {
            allTask.put(task.getTeam().getTeamName(), task.getDeadline().getDate());
        }
        return allTaskDate;

    }

    public Team showSpecialTeam(User user, String command) {
        Team selectTeam = null;
        for (Team team : Team.getAcceptedTeams()) {
            if (command.equals(String.valueOf(team.getTeamNumber())) || command.equals(team.getTeamName()))
                selectTeam = team;
        }
        return selectTeam;
    }

    public int creatTeam(User user, String command) {
        Team team = Team.getTeamByName(command, Team.getAllTeams());
        if (team != null)
            return 1;
        else if (!getCommandMatcher("((?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,12})", command).matches())
            return 2;
        else if (getCommandMatcher("\\d", command.split("")[0]).matches())
            return 2;
        else {
            Date now = new Date(LocalDateTime.now().toString());
            new Team(command, user, now);
            return 3;
        }

    }

    public ArrayList<Task> showTasksForLeader(User user, Team team) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : team.getAllTasks()) {
            tasks.add(task);
        }
        return tasks;
    }

    public int creatTask(User user, Team team, String title, String dateOfCreation, String deadline) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd|HH:mm");
        boolean isTaskAlready = false;
        for (Task task : team.getAllTasks()) {
            if (title.equals(task.getTitle())) {
                isTaskAlready = true;
                break;
            }
        }
        Matcher matcher = getCommandMatcher("([\\d]{4})-([\\d]{2})-([\\d]{2})[|]([\\d]{2}):([\\d]{2})", dateOfCreation);
        matcher.matches();
        Matcher matcher2 = getCommandMatcher("([\\d]{4})-([\\d]{2})-([\\d]{2})[|]([\\d]{2}):([\\d]{2})", deadline);
        matcher2.matches();
        if (isTaskAlready) {
            return 1;
        } else if (!matcher.matches()) {
            return 2;
        } else if (Integer.parseInt(matcher.group(2)) > 12 ||
                Integer.parseInt(matcher.group(2)) < 0 ||
                (Integer.parseInt(matcher.group(2)) > 6 && Integer.parseInt(matcher.group(3)) > 30) ||
                (Integer.parseInt(matcher.group(2)) <= 6 && Integer.parseInt(matcher.group(3)) > 31) ||
                Integer.parseInt(matcher.group(4)) > 24 ||
                Integer.parseInt(matcher.group(5)) > 60
        )
            return 2;
        else if (!matcher2.matches()) {
            return 3;
        } else if (Integer.parseInt(matcher2.group(2)) > 12 ||
                Integer.parseInt(matcher2.group(2)) < 0 ||
                (Integer.parseInt(matcher2.group(2)) > 6 && Integer.parseInt(matcher2.group(3)) > 30) ||
                (Integer.parseInt(matcher2.group(2)) <= 6 && Integer.parseInt(matcher2.group(3)) > 31) ||
                Integer.parseInt(matcher2.group(4)) > 24 ||
                Integer.parseInt(matcher2.group(5)) > 60
        )
            return 3;
        else {
            team.getAllTasks().add(new Task(title, new Date(dateOfCreation), new Date(deadline), team));
            return 4;
        }
    }

    public ArrayList<String> showMembers(User user, Team team) {
        ArrayList<String> names = new ArrayList<>();
        for (User user1 : team.getTeamMembers()) {
            names.add(user1.getFullName());
        }
        Collections.sort(names);
        return names;
    }

    public int addMember(User user, Team team, String command) {
        if (isUsernameAvailable(command))
            return 1;
        else {
            team.getTeamMembers().add(findUser(command));
            findUser(command).getUserTeams().add(team);
            return 2;
        }
    }

    public int deleteMember(User user, Team team, String command) {
        if (!isUsernameAvailable(command))
            return 1;
        else {
            team.getTeamMembers().remove(findUser(command));
            findUser(command).getUserTeams().remove(team);
            return 2;
        }
    }

    public int suspendMember(User user, Team team, String command) {
        if (!isUsernameAvailable(command))
            return 1;
        else {
            team.getSuspendedMembers().add(findUser(command));
            return 2;
        }
    }

    public int promoteMember(User user, Team team, String command) {
        if (!isUsernameAvailable(command))
            return 1;
        else {
            team.setTeamLeader(findUser(command));
            team.getTeamMembers().remove(user);
            user.getUserTeams().remove(team);
            return 2;
        }
    }

    public int assignMember(User user, Team team, String command1, String command2) {
        if (!isUsernameAvailable(command2))
            return 1;
        else if (findTask(team, command1) == null)
            return 2;
        else {
            Task task = findTask(team, command1);
            task.getAssignedUser().add(findUser(command2));
            return 3;
        }
    }

    public ArrayList<String> showScoreBoard(User user, Team team) {
        HashMap<User, Integer> scores = sortBoard
                (team.getScoreboard().getScores());
        ArrayList<String> result = new ArrayList<>();
        HashMap<Integer, ArrayList<User>> data = new HashMap<>();
        for (Map.Entry<User, Integer> unit : scores.entrySet()) {
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
            for (User user1 : users) {
                names.add(user1.getUserName());
            }
            Collections.sort(names);
            for (String name : names) {
                User user1 = User.getUserByUsername(name);
                int score = scores.get(user1);
                result.add(name + " : " + score);
            }
        }
        return result;

    }

    public ArrayList<String> showRoadMap(User user, Team team) {
        ArrayList<String> result = new ArrayList<>();
        if (team.getRoadMap().getCreationDates().isEmpty()) {
            result.add("no task yet");
            return result;
        }
        HashMap<Task, Date> creationDates = sortRoadMap(team.getRoadMap().getCreationDates());
        HashMap<Date, ArrayList<Task>> data = new HashMap<>();

        for (Map.Entry<Task, Date> unit : creationDates.entrySet()) {
            ArrayList<Task> tasks;
            tasks = data.get(unit.getValue());
            if (tasks == null) {
                tasks = new ArrayList<>();
                tasks.add(unit.getKey());
                data.put(unit.getValue(), tasks);
            } else {
                tasks.add(unit.getKey());
            }
        }

        ArrayList<Date> check = new ArrayList<Date>();
        for (Map.Entry<Task, Date> unit : creationDates.entrySet()) {
            if (check.contains(unit.getValue())) continue;
            check.add(unit.getValue());
            ArrayList<Task> tasks = data.get(unit.getValue());
            ArrayList<String> titles = new ArrayList<>();
            for (Task task : tasks) {
                titles.add(task.getTitle() + " : " + team.getRoadMap().getTasksStatus().get(task) + " % done");
            }
            Collections.sort(titles);
            for (String title : titles) {
                result.add(title);
            }
        }
        return result;
    }

    public ArrayList<User> showScoreBoardForLeader(User user, Team team) {
        ArrayList<Integer> score = new ArrayList<>();
        ArrayList<User> sort = new ArrayList<>();
        for (User user1 : team.getTeamMembers()) {
            score.add(user1.getScore());
        }
        Collections.sort(score);
        int number = 1;
        for (Integer score1 : score) {
            if (number > 1) {
                number--;
                continue;

            }
            for (User user1 : team.getTeamMembers()) {
                if (user1.getScore() == score1) {
                    sort.add(user1);
                    number++;
                }
            }
        }
        return sort;
    }

    public int sendNotificationForUser(User sender, String receiver, String command) {
        if (!isUsernameAvailable(receiver))
            return 1;
        else {
            User receiverUser = findUser(receiver);
            receiverUser.getNotifications().add(new Notification(command, sender, 0));
            return 2;
        }
    }

    //Will only be sent to accepted teams
    public int sendNotificationForTeam(User sender, String team, String command) {
        Team receiverTeam = Team.getTeamByName(team, Team.getAcceptedTeams());
        if (receiverTeam == null)
            return 1;
        else {
            receiverTeam.getNotifications().add(new Notification(command, sender, 1));
            return 2;
        }
    }

    public int showProfile(String username) {
        if (!isUsernameAvailable(username)) {
            return 1;
        }
        return 0;
    }

    public int banUser(String username) {
        if (!isUsernameAvailable(username)) {
            return 1;
        }
        User.getUsers().remove(User.getUserByUsername(username));
        return 0;
    }

    public int changeRole(String username, String role) {
        if (!isUsernameAvailable(username)) {
            return 1;
        }
        findUser(username).setRole(role);
        return 0;
    }

    public int sendNotificationForUser(String username) {
        if (!isUsernameAvailable(username)) {
            return 1;
        }
        return 0;
    }

    public int sendNotificationForTeam(String teamName) {
        if (!isTeamAvailable(teamName)) {
            return 1;
        }
        return 0;
    }

    public int showPendingTeams() {
        if(Team.getPendingTeams().size() == 0){
            return 1;
        }
        return 0;
    }

    public int acceptTeam(String teamName) {
        int counter = 0;
        String[] teamsNames = teamName.split(" ");
        for (String string : teamsNames){
            for (Team team : Team.getPendingTeams()){
                if(string.equals(team.getTeamName())){
                    counter++;
                }
            }
        }
        if(counter == teamsNames.length){
            for (String string : teamsNames){
                Team.getPendingTeams().remove(findTeam(string));
                Team.getAcceptedTeams().add(findTeam(string));
            }
            return 0;
        }
        return 1;
    }

    public int rejectTeam(String teamName) {
        int counter = 0;
        String[] teamsNames = teamName.split(" ");
        for (String string : teamsNames){
            for (Team team : Team.getPendingTeams()){
                if(string.equals(team.getTeamName())){
                    counter++;
                }
            }
        }
        if(counter == teamsNames.length){
            for (String string : teamsNames){
                Team.getPendingTeams().remove(findTeam(string));
            }
            return 0;
        }
        return 1;
    }

    public HashMap<User, Integer> sortBoard(HashMap<User, Integer> hashMap) {
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

    public HashMap<Task, Date> sortRoadMap(HashMap<Task, Date> hashMap) {
        // a random date for comparing to other dates
        Date comparingDate = new Date("1300/01/01");
        List<Map.Entry<Task, Date>> valueList =
                new LinkedList<Map.Entry<Task, Date>>(hashMap.entrySet());
        Comparator comparator = new Comparator<Map.Entry<Task, Date>>() {
            public int compare(Map.Entry<Task, Date> operand1, Map.Entry<Task, Date> operand2) {
                return (Date.getDaysBetween(operand2.getValue(), comparingDate)).compareTo
                        (Date.getDaysBetween(operand1.getValue(), comparingDate));
            }
        };
        Collections.sort(valueList, comparator);

        HashMap<Task, Date> sorted = new LinkedHashMap<Task, Date>();
        for (Map.Entry<Task, Date> unit : valueList) {
            sorted.put(unit.getKey(), unit.getValue());
        }
        return sorted;
    }

    public HashMap<Team, Date> sortJoiningDates(HashMap<Team, Date> hashMap) {
        // a random date for comparing to other dates
        Date comparingDate = new Date("1300/01/01");
        List<Map.Entry<Team, Date>> valueList =
                new LinkedList<Map.Entry<Team, Date>>(hashMap.entrySet());
        Comparator comparator = new Comparator<Map.Entry<Team, Date>>() {
            public int compare(Map.Entry<Team, Date> operand1, Map.Entry<Team, Date> operand2) {
                return (Date.getDaysBetween(operand2.getValue(), comparingDate)).compareTo
                        (Date.getDaysBetween(operand1.getValue(), comparingDate));
            }
        };
        Collections.sort(valueList, comparator);

        HashMap<Team, Date> sorted = new LinkedHashMap<Team, Date>();
        for (Map.Entry<Team, Date> unit : valueList) {
            sorted.put(unit.getKey(), unit.getValue());
        }
        return sorted;
    }

    public Matcher getCommandMatcher(String pattern, String input) {
        Pattern pattern1 = Pattern.compile(pattern);
        return pattern1.matcher(input);
    }

    public User findAssignedUsers(Team team, String command) {
        for (User user : team.getTeamMembers()) {
            if (user.getUserName().equals(command))
                return user;
        }
        return null;
    }

    public Team findTeam(String teamName) {
        for (Team team : Team.getAllTeams()) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        return null;
    }

    private boolean isUsernameAvailable(String command) {
        for (User user : User.getUsers()) {
            if (user.getUserName().equals(command))
                return true;
        }
        return false;
    }

    private boolean isEmailAvailable(String command) {
        for (User user : User.getUsers()) {
            if (user.getEmail().equals(command))
                return true;
        }
        return false;
    }

    private boolean isTeamAvailable(String command) {
        for (Team team : Team.getAllTeams()) {
            if (team.getTeamName().equals(command)) {
                return true;
            }
        }
        return false;
    }

    private User findUser(String command) {
        for (User user : User.getUsers()) {
            if (user.getUserName().equals(command))
                return user;
        }
        return null;
    }


    private Task findTask(Team team, String command) {
        for (Task task : team.getAllTasks()) {
            if (Integer.parseInt(command) == task.getCreationId())
                return task;
        }
        return null;
    }
}
