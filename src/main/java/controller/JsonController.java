package controller;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.reflect.TypeToken;
import model.Board;
import model.Task;
import model.Team;
import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonController {

    private static JsonController JsonController;

    public static JsonController getInstance() {
        if (JsonController == null)
            JsonController = new JsonController();
        return JsonController;
    }

    public void readFromJson() {
        try {
            String jsonUser = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\json\\user.json")));
            if (jsonUser.length() > 0) {
                ArrayList<User> users = new YaGson().fromJson(jsonUser, new TypeToken<List<User>>() {
                }.getType());
                for (User user : users) {
                    User.getUsers().add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("can't read from json");
        }
        try {
            String jsonTask = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\json\\Task.json")));
            if (jsonTask.length() > 0) {
                ArrayList<Task> tasks = new YaGson().fromJson(jsonTask, new TypeToken<List<Task>>() {
                }.getType());
                for (Task task : tasks) {
                    Task.getAllTasks().add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("can't read from json");
        }
        try {
            String jsonTeam = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\json\\Team.json")));
            if (jsonTeam.length() > 0) {
                ArrayList<Team> teams = new YaGson().fromJson(jsonTeam, new TypeToken<List<Team>>() {
                }.getType());
                for (Team team : teams) {
                    Team.getAllTeams().add(team);
                }
            }
        } catch (IOException e) {
            System.out.println("can't read from json");
        }
        try {
            String jsonBoard = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\json\\Board.json")));
            if (jsonBoard.length() > 0) {
                ArrayList<Board> boards = new YaGson().fromJson(jsonBoard, new TypeToken<List<Board>>() {
                }.getType());
                for (Board board : boards) {
                    Board.getAllBoards().add(board);
                }
            }
        } catch (IOException e) {
            System.out.println("can't read from json");
        }
    }


    public void updateJson() {
        try {
            FileWriter writerUser = new FileWriter("src\\main\\resources\\json\\user.json");
            writerUser.write(new YaGson().toJson(User.getUsers()));
            writerUser.close();
        } catch (IOException e) {
            System.out.println("can't create or update user");
        }
        try {
            FileWriter writerTask = new FileWriter("src\\main\\resources\\json\\Task.json");
            writerTask.write(new YaGson().toJson(Task.getAllTasks()));
            writerTask.close();
        } catch (IOException e) {
            System.out.println("can't create or update task");
        }
        try {
            FileWriter writerTeam = new FileWriter("src\\main\\resources\\json\\Team.json");
            writerTeam.write(new YaGson().toJson(Team.getAllTeams()));
            writerTeam.close();
        } catch (IOException e) {
            System.out.println("can't create or update team");
        }
        try {
            FileWriter writerBoard = new FileWriter("src\\main\\resources\\json\\Board.json");
            writerBoard.write(new YaGson().toJson(Board.getAllBoards()));
            writerBoard.close();
        } catch (IOException e) {
            System.out.println("can't create or update board");
        }
    }
}
