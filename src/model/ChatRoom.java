package model;

import java.util.ArrayList;
import java.util.HashMap;

public class ChatRoom {

    private Team team;
    private HashMap<User, ArrayList<Message>> chatRoom;

    public ChatRoom(Team team) {
        this.team = team;
        this.chatRoom = new HashMap<>();
        this.team.setChatRoom(this);
    }

    public HashMap<User, ArrayList<Message>> getChatRoom() {
        return chatRoom;
    }
}
