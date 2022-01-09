package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Team;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class AdminMenuView {
    public static User user;
    public static String searchUser;
    public Button users;
    public Button notifications;
    public Button scoreBoard;
    public Button pendingTeams;
    public Button statistic;
    public Label listsOfUsers;
    public Button updateList;
    public Button logOut;
    public Button search;
    public Button back;
    public Label usernameButton;
    public Label emailButton;
    public Label roleButton;
    public Label scoreButton;
    public Label foundUsername;
    public Label foundEmail;
    public Label foundRole;
    public Label foundScore;
    public TextField findUser;
    public Button finding;
    public Button back2;
    public Button ban;
    public Button changeRole;
    public TextField newRole;
    public Label numberOfUsers;
    public Label numberOfTeams;
    public Label numberOfDoneTasks;
    public Label numberOfFailedTasks;
    public Button back3;
    public Button refresh;


    public void goToUsers(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Users.fxml"));
        ((Stage) users.getScene().getWindow()).setScene(new Scene(root));
    }

    public void goToNotification(ActionEvent actionEvent) {
    }

    public void goToScoreBoard(ActionEvent actionEvent) {
    }

    public void showPendingTeams(ActionEvent actionEvent) {
    }

    public void showStatistic(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Statistics.fxml"));
        ((Stage) statistic.getScene().getWindow()).setScene(new Scene(root));
    }

    public void showListOfUsers(ActionEvent actionEvent) {
        ArrayList<String> informationOfUser = new ArrayList<>();
        int rank = 1;
        for (User user : User.getUsers()) {
            informationOfUser.add(rank + ". " + user.getUserName() + "\t\t" + user.getRole() + "\n");
            rank++;
        }
        listsOfUsers.setText(informationOfUser.toString().replaceAll(",", "").replaceAll("\\[", "").replaceAll("\\]", ""));
    }

    public void goToLogInPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        ((Stage) logOut.getScene().getWindow()).setScene(new Scene(root));
    }

    public void goToSearch(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SearchUserForAdmin.fxml"));
        ((Stage) search.getScene().getWindow()).setScene(new Scene(root));
    }

    public void goToAdminPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminMenu.fxml"));
        ((Stage) back.getScene().getWindow()).setScene(new Scene(root));
    }

    public void findUserInPage(ActionEvent actionEvent) {
        if (findUser.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
        } else {
            searchUser = findUser.getText();
            if (User.getUserByUsername(searchUser) == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("There is no user with this username");
                alert.showAndWait();
            } else {
                user = User.getUserByUsername(searchUser);
                foundUsername.setText(user.getUserName());
                foundEmail.setText(user.getEmail());
                foundRole.setText(user.getRole());
                foundScore.setText(Integer.toString(user.getScore()));
            }
        }
    }

    public void backToAdminPage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Users.fxml"));
        ((Stage) back2.getScene().getWindow()).setScene(new Scene(root));
    }

    public void banUser(ActionEvent actionEvent) {
        if (searchUser == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
        } else {
            int response = Controller.controller.banUser(user.getUserName());
            if (response == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText(user.getUserName() + " banned successfully!");
                alert.showAndWait();
            }
        }
    }

    public void changeRole(ActionEvent actionEvent) {
        if (searchUser == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
        } else if (newRole.getText() == null || newRole.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
        } else {
            String newRole1 = newRole.getText();
            int response = Controller.controller.changeRole(user.getUserName(), newRole1);
            if (response == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText(user.getUserName() + " is " + newRole1 + " now!");
                alert.showAndWait();
            }
        }
    }

    public void goToAdminPage2(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminMenu.fxml"));
        ((Stage) back3.getScene().getWindow()).setScene(new Scene(root));
    }

    public void refresh(ActionEvent actionEvent) {
        numberOfUsers.setText(Integer.toString(User.getUsers().size()));
        numberOfTeams.setText(Integer.toString(Team.getAllTeams().size()));
        numberOfDoneTasks.setText("-");
        numberOfFailedTasks.setText("-");
    }
}
