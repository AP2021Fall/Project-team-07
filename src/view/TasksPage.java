package view;

import controller.Controller;
import model.Task;
import model.Team;
import model.User;

import java.text.ParseException;

public class TasksPage {
    public static final TasksPage tasksPage = new TasksPage();

    public void runTasksPage() {

    }

    public void editTaskTitle(User user, Team team, String taskId, String taskTitle) {
        Task task = Task.getTaskById(team, taskId);
        int status = Controller.controller.editTaskTitle(user, task, taskTitle);
        if (status == 0) {
            View.print("You Don’t Have Access To Do This Action!");
        } else if (status == 1) {
            View.print("Title updated successfully!");
        }
    }

    public void editTaskDescription(User user, Team team, String taskId, String taskDescription) {
        Task task = Task.getTaskById(team, taskId);
        int status = Controller.controller.editTaskDescription(user, task, taskDescription);
        if (status == 0) {
            View.print("You Don’t Have Access To Do This Action!");
        } else if (status == 1) {
            View.print("Description updated successfully!");
        }
    }

    public void editTaskPriority(User user, Team team, String taskId, String taskPriority) {
        Task task = Task.getTaskById(team, taskId);
        int status = Controller.controller.editTaskPriority(user, task, taskPriority);
        if (status == 0) {
            View.print("You Don’t Have Access To Do This Action!");
        } else if (status == 1) {
            View.print("Priority updated successfully!");
        }
    }

    public void editTaskDeadline(User user, Team team, String taskId, String deadline) throws ParseException {
        Task task = Task.getTaskById(team, taskId);
        int status = Controller.controller.editTaskDeadline(user, task, deadline);
        if (status == 0) {
            View.print("You Don’t Have Access To Do This Action!");
        } else if (status == 1) {
            View.print("New deadline is invalid!");
        } else if (status == 2) {
            View.print("Deadline updated successfully!");
        }
    }

    public void removeAssignedUsers(User user, Team team, String taskId, String username) {
        Task task = Task.getTaskById(team, taskId);
        User userForEdit = Controller.controller.findAssignedUsers(team, username);
        int status = Controller.controller.removeAssignedUsers(user, task, userForEdit);
        if (status == 0)
            View.print("You Don’t Have Access To Do This Action!");
        else if (status == 1)
            View.print("There is not any user with this username " + username + " in list!");
        else if (status == 2)
            View.print("User " + username + " removed successfully!");
    }

    public void addAssignedUsers(User user, Team team, String taskId, String username) {
        Task task = Task.getTaskById(team, taskId);
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
