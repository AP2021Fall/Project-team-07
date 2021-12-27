package view;

import controller.Controller;
import model.Board;
import model.Team;
import model.User;
import view.View;
import view.Menu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class BoardMenu extends Menu {
    private Team team;

    public BoardMenu(User user, Team team) {
        super(user);
        this.team = team;
    }

    public void runBoardMenu() {
        while (true) {
            String command = View.scanner.nextLine().trim();
            if (Controller.controller.getCommandMatcher
                    ("^board --new --name (.+)$", command).matches())
                makeBoard(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --remove --name (.+)$", command).matches())
                removeBoard(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --new  --category (.+) --name (.+)$", command).matches())
                addCategory(command);
                // columns start from zero
            else if (Controller.controller.getCommandMatcher
                    ("^board --category (.+) --column (\\d+) --name (.+)$", command).matches())
                changeColumn(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --done --name (.+)$", command).matches())
                boardDone(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --add (\\d+) --name (\\S+)$", command).matches())
                boardAddTask(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --assign (\\S+) --task (\\S+) --name (\\S+)$", command).matches())
                boardAssignMember(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --force --category (\\S+) --task (\\S+) --name (\\S+)$", command).matches())
                forceCategory(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --category next --task (\\S+) --name (\\S+)$", command).matches())
                goToNextCategory(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --show (done|failed) --name --board (\\S+)$", command).matches())
                showDoneOrFailed(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --open --task (\\S+) --deadline (\\S+) --name (\\S+)$", command).matches())
                updateDeadline(command);
            else if (Controller.controller.getCommandMatcher
                    ("^board --show --name (\\S+)$", command).matches())
                boardShow(command);
            else if (command.equals("back"))
                return;
            else System.out.println("invalid command ");


        }

    }

    public void makeBoard(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --new --name (.+)$", command);
        matcher.matches();
        String boardName = matcher.group(1);
        int response = Controller.controller.makeBoard(super.user, this.team, boardName);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is already a board with this name");
                break;
            case 2:
                View.print("board created successfully");
                break;
        }
    }

    public void removeBoard(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --remove --name (.+)$", command);
        matcher.matches();
        String boardName = matcher.group(1);
        int response = Controller.controller.removeBoard(super.user, this.team, boardName);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is no board with this name");
                break;
            case 2:
                View.print("board removed successfully");
                break;
        }
    }

    public void addCategory(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --new  --category (.+) --name (.+)$", command);
        matcher.matches();
        String categoryName = matcher.group(1);
        String boardName = matcher.group(2);
        int response = Controller.controller.addCategory(super.user, this.team, boardName, categoryName);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is no board with this name");
                break;
            case 2:
                View.print("The name is already taken for a category!");
                break;
            case 3:
                View.print("category created ");
                break;
        }
    }

    public void changeColumn(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --category (.+) --column (\\d+) --name (.+)$", command);
        matcher.matches();
        String categoryName = matcher.group(1);
        int column = Integer.parseInt(matcher.group(2));
        String boardName = matcher.group(3);
        int response = Controller.controller.changeColumn(super.user, this.team, boardName, categoryName, column);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is no board with this name");
                break;
            case 2:
                View.print("There is no category with this name!");
                break;
            case 3:
                View.print("wrong column");
                break;
            case 4:
                View.print("column changed successfully");
                break;
        }
    }

    public void boardDone(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher("^board --done --name (.+)$", command);
        matcher.matches();
        String boardName = matcher.group(1);
        int response = Controller.controller.boardDone(super.user, this.team, boardName);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is no board with this name");
                break;
            case 2:
                View.print("plz make a category first");
                break;
            case 3:
                View.print("board completed successfully");
                break;
        }

    }

    public void boardAddTask(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --add (\\d+) --name (\\S+)$", command);
        matcher.matches();
        String taskId = matcher.group(1);
        String boardName = matcher.group(2);
        int response = Controller.controller.boardAddTask(super.user, this.team, boardName, taskId);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is no board with this name");
                break;
            case 2:
                View.print("Please finish creating the board first");
                break;
            case 3:
                View.print("Invalid task id in this team!");
                break;
            case 4:
                View.print("This task has already been added to this board");
                break;
            case 5:
                View.print("The deadline of this task has already passed");
                break;
            case 6:
                View.print("Please assign this task to someone first");
                break;
            case 7:
                View.print("Task added successfully");
                break;
        }
    }

    public void boardAssignMember(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --assign (\\S+) --task (\\d+) --name (\\S+)$", command);
        matcher.matches();
        String username = matcher.group(1);
        String taskId = matcher.group(2);
        String boardName = matcher.group(3);
        int response = Controller.controller.boardAssignMember(super.user, this.team, username, boardName, taskId);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is no board with this name");
                break;
            case 2:
                View.print("Please finish creating the board first");
                break;
            case 3:
                View.print("Invalid task id in this board!");
                break;
            case 4:
                View.print("invalid teammate");
                break;
            case 5:
                View.print("This task has already finished");
                break;
            case 6:
                View.print("user assigned to the task");
                break;

        }
    }

    public void forceCategory(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --force --category (\\S+) --task (\\S+) --name (\\S+)$", command);
        matcher.matches();
        String category = matcher.group(1);
        String taskTitle = matcher.group(2);
        String boardName = matcher.group(3);
        int response = Controller.controller.forceCategory(super.user, this.team, category, boardName, taskTitle);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is no board with this name");
                break;
            case 2:
                View.print("Please finish creating the board first");
                break;
            case 3:
                View.print("Invalid task title in this board!");
                break;
            case 4:
                View.print("This task has already finished");
                break;
            case 5:
                View.print("invalid category");
                break;
            case 6:
                View.print("task put to category");
                break;
        }
    }

    public void goToNextCategory(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --category next --task (\\S+) --name (\\S+)$", command);
        matcher.matches();
        String taskTitle = matcher.group(1);
        String boardName = matcher.group(2);
        int response = Controller.controller.goToNextCategory(super.user, this.team, boardName, taskTitle);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is no board with this name");
                break;
            case 2:
                View.print("Please finish creating the board first");
                break;
            case 3:
                View.print("Invalid task title in this board!");
                break;
            case 4:
                View.print("This task has already finished");
                break;
            case 5:
                View.print("this task is not in any category now!");
                break;
            case 6:
                View.print("task put to the next category");
                break;
        }
    }

    public void showDoneOrFailed(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --show (done|failed) --name --board (\\S+)$", command);
        matcher.matches();
        String doneOrFailed = matcher.group(1);
        String boardName = matcher.group(2);
        ArrayList<String> response = Controller.controller.showDoneOrFailed(team, doneOrFailed, boardName);
        for (String string : response)
            View.print(string);
    }

    public void updateDeadline(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --open --task (\\S+) --deadline (\\S+) --name (\\S+)$", command);
        matcher.matches();
        String taskTitle = matcher.group(1);
        String deadLine = matcher.group(2);
        String boardName = matcher.group(3);
        int response = Controller.controller.updateDeadline(super.user, this.team, taskTitle, deadLine, boardName);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
                break;
            case 1:
                View.print("There is no board with this name");
                break;
            case 2:
                View.print("Please finish creating the board first");
                break;
            case 3:
                View.print("Invalid task title in this board!");
                break;
            case 4:
                View.print("This task is not in failed section");
                break;
            case 5:
                View.print("wrong date format");
                break;
            case 6:
                View.print("deadline changed and ");
                break;
        }
    }

    public void boardShow(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher
                ("^board --show --name (\\S+)$", command);
        matcher.matches();
        String boardName = matcher.group(1);
        ArrayList<String> response = Controller.controller.boardShow(this.team, boardName);
        for (String string : response)
            View.print(string);
    }
}
