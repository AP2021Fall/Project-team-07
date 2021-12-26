package view;

import controller.Controller;
import model.Task;
import model.Team;
import model.User;

import java.text.ParseException;
import java.util.regex.Matcher;

public class TasksPage {
    public static final TasksPage tasksPage = new TasksPage();

    public void runTasksPage(User user) throws ParseException {
        while (true) {
            View.print("Enter your command: ");
            String input = View.scanner.nextLine().trim();
            if (Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --title ([^ ]+)", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --title ([^ ]+)", input);
                editTaskTitle(user, matcher.group(1), matcher.group(2));
            } else if (Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --description ([^ ]+)", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --description ([^ ]+)", input);
                editTaskDescription(user, matcher.group(1), matcher.group(2));
            } else if (Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --priority ([^ ]+)", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --priority ([^ ]+)", input);
                editTaskPriority(user, matcher.group(1), matcher.group(2));
            } else if (Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --deadline ([^ ]+)", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --deadline ([^ ]+)", input);
                editTaskDeadline(user, matcher.group(1), matcher.group(2));
            } else if (Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --assignedUsers ([^ ]+) --remove", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --assignedUsers ([^ ]+) --remove", input);
                removeAssignedUsers(user, matcher.group(1), matcher.group(2));
            } else if (Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --assignedUsers ([^ ]+) --add", input).matches()) {
                Matcher matcher = Controller.controller.getCommandMatcher("edit --task --id ([^ ]+) --assignedUsers ([^ ]+) --add", input);
                addAssignedUsers(user, matcher.group(1), matcher.group(2));
            } else if (input.equals("back")) {
                return;
            } else {
                View.print("Invalid command!");
            }
        }
    }

    public void editTaskTitle(User user, String taskId, String taskTitle) {
        Task task = Task.getTaskByIdWithoutTeam(taskId);
        int status = Controller.controller.editTaskTitle(user, task, taskTitle);
        if (status == 0) {
            View.print("You Don’t Have Access To Do This Action!");
        } else if (status == 1) {
            View.print("Title updated successfully!");
        }
    }

    public void editTaskDescription(User user, String taskId, String taskDescription) {
        Task task = Task.getTaskByIdWithoutTeam(taskId);
        int status = Controller.controller.editTaskDescription(user, task, taskDescription);
        if (status == 0) {
            View.print("You Don’t Have Access To Do This Action!");
        } else if (status == 1) {
            View.print("Description updated successfully!");
        }
    }

    public void editTaskPriority(User user, String taskId, String taskPriority) {
        Task task = Task.getTaskByIdWithoutTeam(taskId);
        int status = Controller.controller.editTaskPriority(user, task, taskPriority);
        if (status == 0) {
            View.print("You Don’t Have Access To Do This Action!");
        } else if (status == 1) {
            View.print("Priority updated successfully!");
        }
    }

    public void editTaskDeadline(User user, String taskId, String deadline) throws ParseException {
        Task task = Task.getTaskByIdWithoutTeam(taskId);
        int status = Controller.controller.editTaskDeadline(user, task, deadline);
        if (status == 0) {
            View.print("You Don’t Have Access To Do This Action!");
        } else if (status == 1) {
            View.print("New deadline is invalid!");
        } else if (status == 2) {
            View.print("Deadline updated successfully!");
        }
    }

    public void removeAssignedUsers(User user, String taskId, String username) {
        Task task = Task.getTaskByIdWithoutTeam(taskId);
        User userForEdit = User.getUserByUsername(username);
        int status = Controller.controller.removeAssignedUsers(user, task, userForEdit);
        if (status == 0)
            View.print("You Don’t Have Access To Do This Action!");
        else if (status == 1)
            View.print("There is not any user with this username " + username + " in list!");
        else if (status == 2)
            View.print("User " + username + " removed successfully!");
    }

    public void addAssignedUsers(User user, String taskId, String username) {
        Task task = Task.getTaskByIdWithoutTeam(taskId);
        User userForEdit = User.getUserByUsername(username);
        int status = Controller.controller.addAssignedUsers(user, task, userForEdit);
        if (status == 0)
            View.print("You Don’t Have Access To Do This Action!");
        else if (status == 1)
            View.print("There is not any user with this username " + username + "!");
        else if (status == 2)
            View.print("User " + username + " added successfully!");
    }
}
