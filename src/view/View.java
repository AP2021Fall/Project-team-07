package view;

import controller.Controller;
import model.User;

import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;

public class View {
    public static Scanner scanner = new Scanner(System.in);

    public void run() {
        print("Welcome to our program\n");
        label:
        while (true) {
            print("1. Sign in\n2. Login\n3. Quit\n");
            print("Enter your choice: ");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1", "Sign in" -> {
                    print("Enter your command: ");
                    String command = scanner.nextLine().trim();
                    register(command);
                }
                case "2", "Login" -> {
                    print("Enter your command: ");
                    String command = scanner.nextLine().trim();
                    logIn(command);
                }
                case "3", "Quit" -> {
                    print("Are you sure you want to quit?\n1. Yes\n2. No");
                    String choose = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                    if (choose.equals("1") || choose.equals("yes")) {
                        print("Have a nice day!");
                        break label;
                    } else if (choose.equals("2") || choose.equals("no")) {

                    } else {
                        print("Invalid command!");
                    }
                }
                default -> print("Invalid command!");
            }
        }
    }

    public static void runMemberMenu(User user) {
        while (true) {
            print("1. Profile Menu\n2. Team Menu\n3. Tasks Page\n4. Calender Menu\n5. Notification Bar\n6. Quit");
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
                // continue
            } else if (input.equals("4") || input.equals("Calender Menu")) {
                // continue
            } else if (input.equals("5") || input.equals("Notification Bar")) {
                // continue
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
            } else {
                print("Invalid command!");
            }
        }
    }

    public static void runLeaderMenu(User user) {

    }

    public static void runAdminMenu(User user) {

    }

    public void register(String command) {
        Matcher matcher;
        if ((matcher = Controller.controller.getCommandMatcher("^user creat --username ([^ ]+) --password1 ([^ ]+) --password2 ([^ ]+)" +
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

    public void logIn(String command) {
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

    public void printMenu(User user) {

    }

    public static void print(String string) {
        System.out.println(string);
    }
}
