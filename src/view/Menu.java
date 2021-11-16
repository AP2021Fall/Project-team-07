package view;

import model.User;

public class Menu {

    private User user;

    public Menu(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
