package model;

import model.Date;

import java.util.ArrayList;

public class Task {
    private static final ArrayList<Task> allTasks = new ArrayList<>();
    private static int idCreator = 1;
    private int creationId;
    private String title;
    private String description;
    private String priority;
    // model.date
    private Date dateOfCreation;
    private Date deadline;
    private ArrayList<User> assignedUser;
    private Team team;
    private ArrayList<Message> comments;

    public Task(String title, Date dateOfCreation,
                Date deadline, Team team) {
        this.title = title;
        this.dateOfCreation = dateOfCreation;
        this.deadline = deadline;
        this.team = team;
        this.creationId = idCreator;
        idCreator++;
        this.assignedUser = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.team.getRoadMap().getCreationDates().put(this, dateOfCreation);
        allTasks.add(this);
    }

    public static Task getTaskById(Team team, String id) {
        for (Task task : team.getAllTasks()) {
            if (task.getCreationId() == Integer.parseInt(id))
                return task;

        }
        return null;

    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public int getCreationId() {
        return creationId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public Date getDeadline() {
        return deadline;
    }

    public ArrayList<User> getAssignedUser() {
        return assignedUser;
    }

    public Team getTeam() {
        return team;
    }

    public ArrayList<Message> getComments() {
        return comments;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
