package view;

import controller.Controller;
import model.Team;
import model.User;
import view.View;
import view.Menu;

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
            else if (Controller.controller.getCommandMatcher("^board --done --name (.+)$",command).matches())
                boardDone(command);

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
            case 1:
                View.print("There is already a board with this name");
            case 2:
                View.print("board created successfully");
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
            case 1:
                View.print("There is no board with this name");
            case 2:
                View.print("board removed successfully");
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
            case 1:
                View.print("There is no board with this name");
            case 2:
                View.print("The name is already taken for a category!");
            case 3:
                View.print("category created ");
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
            case 1:
                View.print("There is no board with this name");
            case 2:
                View.print("There is no category with this name!");
            case 3:
                View.print("wrong column");
            case 4:
                View.print("column changed successfully");
        }
    }

    public void boardDone(String command) {
        Matcher matcher = Controller.controller.getCommandMatcher("^board --done --name (.+)$",command);
        matcher.matches();
        String boardName = matcher.group(1);
        int response = Controller.controller.boardDone(super.user,this.team,boardName);
        switch (response) {
            case 0:
                View.print("You do not have the permission to do this action!");
            case 1:
                View.print("There is no board with this name");
            case 2:
                View.print("plz make a category first");
            case 3:
                View.print("board completed successfully");
        }

    }

    public void boardAddTask(String command) {

    }

    public void boardAssignMember(String command) {

    }

    public void forceCategory(String command) {

    }

    public void goToNextCategory(String command) {

    }

    public void showDoneOrFailed(String command) {

    }

    public void updateDeadline(String command) {

    }

    public void boardShow(String command) {

    }
}
