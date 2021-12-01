package model;

import java.util.ArrayList;

public class Category {

    private Board board;
    private String name;
    private int column;
    private ArrayList<Task> categoryTask;

    public Category(Board board, String name,
                    int column) {
        this.board = board;
        this.name = name;
        this.column = column;
    }
}
