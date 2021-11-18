package model;

import java.util.ArrayList;

public class Board {

    private static ArrayList<Board> allBoards = new ArrayList<>();
    private boolean isCreated;
    private Team team;
    private String boardName;
    private ArrayList<Category> allCategories;
    private ArrayList<Task> failed;
    private ArrayList<Task> done;
    private ArrayList<User> membersOfBoards;
    private ArrayList<Task> boardTask;
    private User creator;
    private String categoryName;
    private String column;

    public Board(String boardName, User creator) {
        this.boardName = boardName;
        this.creator = creator;
        this.failed = new ArrayList<>();
        this.done = new ArrayList<>();
        this.membersOfBoards = new ArrayList<>();
        this.allCategories = new ArrayList<>();
        this.boardTask = new ArrayList<>();
    }

    public static Board getBoardByBoardName(String boardName){
        return null;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setAllCategories(ArrayList<Category> allCategories) {
        this.allCategories = allCategories;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public void setCreated(boolean created) {
        isCreated = created;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public static ArrayList<Board> getAllBoards() {
        return allBoards;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public ArrayList<Task> getBoardTask() {
        return boardTask;
    }

    public Team getTeam() {
        return team;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getColumn() {
        return column;
    }

    public String getBoardName() {
        return boardName;
    }

    public ArrayList<Task> getFailed() {
        return failed;
    }

    public ArrayList<Task> getDone() {
        return done;
    }

    public ArrayList<User> getMembersOfBoards() {
        return membersOfBoards;
    }

    public User getCreator() {
        return creator;
    }
}
