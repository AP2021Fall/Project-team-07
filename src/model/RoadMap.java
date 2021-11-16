package model;

import java.util.HashMap;

public class RoadMap {

    private Team team;
    private HashMap<Task, Integer> tasksStatus;

    public RoadMap(Team team) {
        this.team = team;
        this.tasksStatus = new HashMap<>();
        this.team.setRoadMap(this);
    }

    public HashMap<Task, Integer> getTasksStatus() {
        return tasksStatus;
    }
}
