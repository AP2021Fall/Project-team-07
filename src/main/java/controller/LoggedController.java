package controller;

import model.Team;
import model.User;

public class LoggedController {
    private static LoggedController instance = null;
    private User loggedInUser;
    private Team loggedTeam;

    private LoggedController() {

    }

    public static LoggedController getInstance() {
        if (instance == null)
            instance = new LoggedController();
        return instance;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public Team getLoggedTeam() {
        return loggedTeam;
    }

    public void setLoggedTeam(Team loggedTeam) {
        this.loggedTeam = loggedTeam;
    }
}
