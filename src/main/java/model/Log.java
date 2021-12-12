package model;

public class Log {

    private User user;
    private String logText;
    private Date date;

    public Log(User user, Date date) {
        this.user = user;
        this.date = date;
        this.user.getAllLogs().add(this);
    }
}
