package view;

import model.Notification;
import model.User;

public class NotificationBar {
    public static final NotificationBar notificationBar = new NotificationBar();

    public void runNotificationBar(User user) {
        int rank = 1;
        for (Notification notification : user.getNotifications()) {
            View.print("------------------------------");
            View.print(rank + ". " + notification.getText() + ". " + " (from: " + notification.getSender() + ")");
            View.print("------------------------------");
            rank++;
        }
    }
}
