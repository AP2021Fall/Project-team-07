package view;

import controller.Controller;
import model.User;

import java.text.ParseException;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;

public class View {
    public static Scanner scanner = new Scanner(System.in);
    public static final View view = new View();

    public void run() throws ParseException {
        print("Welcome to our program\n");
        while (true) {
            print("1. Sign in\n2. Login\n3. Quit\n");
            print("Enter your choice: ");
            String input = scanner.nextLine().trim();
            if (input.equals("1") || input.equals("Sign in")) {
                print("Enter your command: ");
                String command = scanner.nextLine().trim();
                register(command);
            } else if (input.equals("2") || input.equals("Login")) {
                print("Enter your command: ");
                String command = scanner.nextLine().trim();
                logIn(command);
            } else if (input.equals("3") || input.equals("Quit")) {
                print("Are you sure you want to quit?\n1. Yes\n2. No");
                String choose = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                if (choose.equals("1") || choose.equals("yes")) {
                    print("Have a nice day!");
                    break;
                } else if (choose.equals("2") || choose.equals("no")) {
                    System.out.println();
                } else {
                    print("Invalid command!");
                }
            } else if (Controller.controller.getCommandMatcher("show profile --username ([^ ]+)", input).matches()) {
                print("You are not logged in\n");
            } else if (Controller.controller.getCommandMatcher("ban user --user ([^ ]+)", input).matches()) {
                print("You are not logged in\n");
            } else if (Controller.controller.getCommandMatcher("change role --user ([^ ]+) --role ([^ ]+)", input).matches()) {
                print("You are not logged in\n");
            } else if (Controller.controller.getCommandMatcher("send --notification ([^ ]+) --all", input).matches()) {
                print("You are not logged in\n");
            } else if (Controller.controller.getCommandMatcher("send --notification ([^ ]+) --user ([^ ]+)", input).matches()) {
                print("You are not logged in\n");
            } else if (Controller.controller.getCommandMatcher("send --notification ([^ ]+) --team ([^ ]+)", input).matches()) {
                print("You are not logged in\n");
            } else {
                print("Invalid command!");
            }
        }
    }

    public static void runMemberMenu(User user) throws ParseException {
        while (true) {
            printMenu(user);
            print("\nEnter your command: ");
            String input = scanner.nextLine().trim();
            Matcher matcher = Controller.controller.getCommandMatcher("^enter menu ([^ ]+)$", input);
            input = matcher.group(1);

            if (input.equals("1") || input.equals("Profile Menu")) {
                ProfileMenu profileMenu = new ProfileMenu(user);
                profileMenu.runProfileMenu();
            } else if (input.equals("2") || input.equals("Team Menu")) {
                TeamMenu teamMenu = new TeamMenu(user);
                teamMenu.runTeamMenu();
            } else if (input.equals("3") || input.equals("Tasks Page")) {
                TasksPage.tasksPage.runTasksPage(user);
            } else if (input.equals("4") || input.equals("Calender Menu")) {
                CalenderMenu.calenderMenu.runCalenderMenu(user);
            } else if (input.equals("5") || input.equals("Notification Bar")) {
                NotificationBar.notificationBar.runNotificationBar();
            } else if (input.equals("6") || input.equals("Quit")) {
                print("Are you sure you want to quit?\n1. Yes\n2. No");
                String choose = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                if (choose.equals("1") || choose.equals("yes")) {
                    print("Have a nice day!");
                    break;
                } else if (choose.equals("2") || choose.equals("no")) {

                } else {
                    print("Invalid command!");
                }
            } else if (Controller.controller.getCommandMatcher("show profile --username ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("ban user --user ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("change role --user ([^ ]+) --role ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("send --notification ([^ ]+) --all", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("send --notification ([^ ]+) --user ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("send --notification ([^ ]+) --team ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (input.equals("back")) {
                break;
            } else {
                print("Invalid command!");
            }
        }
    }

    public static void runLeaderMenu(User user) throws ParseException {
        while (true) {
            printMenu(user);
            print("\nEnter your command: ");
            String input = scanner.nextLine().trim();
            Matcher matcher = Controller.controller.getCommandMatcher("^enter menu ([^ ]+)$", input);
            input = matcher.group(1);

            if (input.equals("1") || input.equals("Profile Menu")) {
                ProfileMenu profileMenu = new ProfileMenu(user);
                profileMenu.runProfileMenu();
            } else if (input.equals("2") || input.equals("Team Menu")) {
                TeamMenu teamMenu = new TeamMenu(user);    // Leader Menu?????
                teamMenu.runTeamMenu();
            } else if (input.equals("3") || input.equals("Tasks Page")) {
                TasksPage.tasksPage.runTasksPage(user);
            } else if (input.equals("4") || input.equals("Calender Menu")) {
                CalenderMenu.calenderMenu.runCalenderMenu(user);
            } else if (input.equals("5") || input.equals("Notification Bar")) {
                NotificationBar.notificationBar.runNotificationBar();
            } else if (input.equals("6") || input.equals("Special Commands")) {
                LeaderMenu.leaderMenu.runSpecialCommandsForLeaderMenu(user);
            } else if (input.equals("7") || input.equals("Quit")) {
                print("Are you sure you want to quit?\n1. Yes\n2. No");
                String choose = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                if (choose.equals("1") || choose.equals("yes")) {
                    print("Have a nice day!");
                    break;
                } else if (choose.equals("2") || choose.equals("no")) {

                } else {
                    print("Invalid command!");
                }
            } else if (Controller.controller.getCommandMatcher("show profile --username ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("ban user --user ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("change role --user ([^ ]+) --role ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("send --notification ([^ ]+) --all", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("send --notification ([^ ]+) --user ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (Controller.controller.getCommandMatcher("send --notification ([^ ]+) --team ([^ ]+)", input).matches()) {
                print("You do not have access to this section");
            } else if (input.equals("back")) {
                break;
            } else {
                print("Invalid command!");
            }
        }
    }

    public static void runAdminMenu(User user) {
        while (true) {
            printMenu(user);
            String input = scanner.nextLine().trim();
            Matcher matcher;

            if ((matcher = Controller.controller.getCommandMatcher("show profile --username ([^ ]+)", input)).matches()) {
                AdminMenu.showProfile(matcher.group(1));
            } else if ((matcher = Controller.controller.getCommandMatcher("ban user --user ([^ ]+)", input)).matches()) {
                AdminMenu.banUser(matcher.group(1));
            } else if ((matcher = Controller.controller.getCommandMatcher("change role --user ([^ ]+) --role ([^ ]+)", input)).matches()) {
                AdminMenu.changeRole(matcher.group(1), matcher.group(2));
            } else if ((matcher = Controller.controller.getCommandMatcher("send --notification ([^ ]+) --all", input)).matches()) {
                AdminMenu.sendNotificationForAll(matcher.group(1), user);
            } else if ((matcher = Controller.controller.getCommandMatcher("send --notification ([^ ]+) --user ([^ ]+)", input)).matches()) {
                AdminMenu.sendNotificationForUser(matcher.group(2), matcher.group(1), user);
            } else if ((matcher = Controller.controller.getCommandMatcher("send --notification ([^ ]+) --team ([^ ]+)", input)).matches()) {
                AdminMenu.sendNotificationForTeam(matcher.group(2), matcher.group(1), user);
            } else if (Controller.controller.getCommandMatcher("Scoreboard --show --team ([^ ]+)", input).matches()) {
                AdminMenu.showScoreBoard(user, matcher.group(1));
            } else if (Controller.controller.getCommandMatcher("show --pendingTeams", input).matches()) {
                AdminMenu.showPendingTeams();
            } else if (input.equals("back")) {
                break;
            } else {
                print("Invalid command!");
            }
        }
    }

    public void register(String command) {
        Matcher matcher;
        if ((matcher = Controller.controller.getCommandMatcher("user create --username ([^ ]+) --password1 ([^ ]+) --password2 ([^ ]+)" +
                " --email Address ([^ ]+)$", command)).matches()) {
            int answer = Controller.controller.register(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
            if (answer == 1) {
                print("user with username " + matcher.group(1) + " already exists!");
            } else if (answer == 2) {
                print("Your passwords are not the same!");
            } else if (answer == 3) {
                print("User with this email already exists!");
            } else if (answer == 4) {
                print("Email address is invalid!");
            } else if (answer == 0) {
                print("user created successfully!");
            }
        } else {
            print("Invalid command!");
        }
    }

    public void logIn(String command) throws ParseException {
        Matcher matcher;
        if ((matcher = Controller.controller.getCommandMatcher("^user login --username ([^ ]+) --password ([^ ]+)$", command)).matches()) {
            int answer = Controller.controller.logIn(matcher.group(1), matcher.group(2));
            if (answer == 1) {
                print("There is not any user with username: " + matcher.group(1) + "!");
            } else if (answer == 2) {
                print("Username and password didn’t match!");
            } else if (answer == 0) {
                print("user logged in successfully!");
            }
        } else {
            print("Invalid command!");
        }
    }

    public static void printMenu(User user) {
        int answer = Controller.controller.printMenu(user);
        if (answer == 1) {
            print("1. Profile Menu\n2. Team Menu\n3. Tasks Page\n4. Calender Menu\n5. Notification Bar\n6. Quit");
        } else if (answer == 2) {
            print("1. Profile Menu\n2. Team Menu\n3. Tasks Page\n4. Calender Menu\n5. Notification Bar\n6. Special Commands" +
                    "\n7. Quit");
        } else if (answer == 3) {
            print("Enter your command :");
        }
    }

    public static void print(String string) {
        System.out.println(string);
    }
}
