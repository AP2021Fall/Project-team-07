package view;

import controller.Controller;
import model.Task;
import model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalenderMenu {
    public static final CalenderMenu calenderMenu = new CalenderMenu();
    Controller controller = new Controller();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public void runCalenderMenu(User user) throws ParseException {
        showDeadLines(user);
    }

    public void showDeadLines(User user) throws ParseException {
        ArrayList<java.util.Date> allTaskDate = controller.showDeadLines(user);
        if (user.getAllTasksForUser().size() == 0)
            System.out.println("no deadlines");
        java.util.Date currentTime = new java.util.Date();
        for (java.util.Date date : allTaskDate) {
            if (date.compareTo(currentTime) < 4 && date.compareTo(currentTime) > 4)
                System.out.println("***" + formatter.parse(date.toString()) + "_remaining days: " + date.compareTo(currentTime));
            else if (date.compareTo(currentTime) >= 4 && date.compareTo(currentTime) <= 10)
                System.out.println("**" + formatter.parse(date.toString()) + "_remaining days: " + date.compareTo(currentTime));
            else if (date.compareTo(currentTime) > 10)
                System.out.println("*" + formatter.parse(date.toString()) + "_remaining days: " + date.compareTo(currentTime));
        }
    }
}
