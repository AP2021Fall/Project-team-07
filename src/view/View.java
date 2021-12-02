package view;

import controller.Controller;
import model.User;

import java.util.Scanner;
import java.util.regex.Matcher;

public class View {
    public static Scanner scanner = new Scanner(System.in);

    public void run() {
        print("Welcome to our program\n");
        while (true) {
            print("1. Sign in\n2. Login\n3. Quit\n");
            print("Enter your choice: ");
            String input = scanner.nextLine().trim();
            if (input.equals("1")) {
                print("Enter your command");
                String command = scanner.nextLine().trim();
                register(command);
            } else if (input.equals("2")) {
                String command = scanner.nextLine().trim();
                logIn(command);
            } else if (input.equals("3")) {
                break;
            } else {
                print("Invalid command!");
            }
        }
    }

    public void runMemberMenu(User user) {

    }

    public void runLeaderMenu(User user) {

    }

    public void runAdminMenu(User user) {

    }

    public void register(String command) {
        Matcher matcher;
        if ((matcher = Controller.controller.getCommandMatcher("^user creat --username ([^ ]+) --password1 ([^ ]+) --password2 ([^ ]+)" +
                " --email Address ([^ ]+)$", command)).matches()) {
            int answer = Controller.controller.register(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
            if(answer == 1){
                print("user with username " + matcher.group(1) + " already exists!");
            }
            else if(answer == 2){
                print("Your passwords are not the same!");
            }
            else if(answer == 3){
                print("User with this email already exists!");
            }
            else if(answer == 4){
                print("Email address is invalid!");
            }
            else if(answer == 0){
                print("user created successfully!");
            }
        } else {
            print("Invalid command!");
        }
    }

    public void logIn(String command) {

    }

    public void printMenu(User user) {

    }

    public static void print(String string) {
        System.out.println(string);
    }
}
