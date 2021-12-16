package controller;

import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.com.google.gson.reflect.TypeToken;
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
            String json = new String(Files.readAllBytes(Paths.get("src\\main\\resources\\json.json")));
            if (json.length() > 0) {
                ArrayList<User> users = new YaGson().fromJson(json, new TypeToken<List<User>>() {
                }.getType());
                for (User user : users) {
                    User.getUsers().add(user);
                }
            }
        } catch (IOException e) {
            System.out.println("can't read from json");
        }
    }


    public void updateJson() {
        try {
            FileWriter writer = new FileWriter("src\\main\\resources\\json.json");
            writer.write(new YaGson().toJson(User.getUsers()));
            writer.close();
        } catch (IOException e) {
            System.out.println("can't create or update json");
        }
    }
}
