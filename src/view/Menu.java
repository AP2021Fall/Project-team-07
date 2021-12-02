package view;

import model.User;

public class Menu {

    protected User user;

    public Menu(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
