package model;

import java.util.ArrayList;
import java.util.Date;

public class User {

    private static ArrayList<User> users = new ArrayList<>();
    private static int idCreator = 1;
    private int creationId;
    private String fullName;
    private Date birthday;
    private String userName;
    private String password;
    private String email;
    private String role;
    private ArrayList<Log> allLogs;

    public User(String userName, String password,
                String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.creationId = idCreator;
        idCreator++;
        users.add(this);
        this.allLogs = new ArrayList<>();
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Log> getAllLogs() {
        return allLogs;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
