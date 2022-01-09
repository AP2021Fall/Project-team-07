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
import model.Notification;
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
    public Label listOfPendingTeams;
    public Button updateList2;
    public Button accept;
    public Button reject;
    public TextField acceptTeams;
    public TextField rejectTeams;
    public Button back4;
    public Button back5;
    public TextField textOfNotification;
    public Button sendToAll;
    public Button sendToTeam;
    public Button sendToUser;
    public TextField nameOfTeam;
    public TextField nameOfUser;


    public void goToUsers(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Users.fxml"));
        ((Stage) users.getScene().getWindow()).setScene(new Scene(root));
    }

    public void goToNotification(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SendNotification.fxml"));
        ((Stage) notifications.getScene().getWindow()).setScene(new Scene(root));
    }

    public void goToScoreBoard(ActionEvent actionEvent) {
        // go to scoreBoard page
    }

    public void showPendingTeams(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/PendingTeams.fxml"));
        ((Stage) pendingTeams.getScene().getWindow()).setScene(new Scene(root));
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

    public void showListOfPendingTeams(ActionEvent actionEvent) {
        int response = Controller.controller.showPendingTeams();
        if (response == 1){
            listOfPendingTeams.setText("There are no Teams in Pending Status!");
        }
        else {
            ArrayList<String> pending = new ArrayList<>();
            int rank = 1;
            for (Team team : Team.getPendingTeams()){
                pending.add(rank + ". " + team.getTeamName() + "\n");
                rank++;
            }
            listOfPendingTeams.setText(pending.toString().replaceAll(",", "").replaceAll("\\[", "").replaceAll("\\]", ""));
        }
    }

    public void accept(ActionEvent actionEvent) {
        if (acceptTeams.getText() == null || acceptTeams.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
            return;
        }
        int response = Controller.controller.acceptTeam(acceptTeams.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (response == 1){
            alert.setTitle("Error");
            alert.setContentText("Some teams are not in pending status! Try again");
        }else {
            alert.setTitle("Success");
            alert.setContentText("Teams accepted successfully!");
        }
        alert.showAndWait();
    }

    public void reject(ActionEvent actionEvent) {
        if (rejectTeams.getText() == null || rejectTeams.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
            return;
        }
        int response = Controller.controller.rejectTeam(rejectTeams.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (response == 1){
            alert.setTitle("Error");
            alert.setContentText("Some teams are not in pending status! Try again");
        }else {
            alert.setTitle("Success");
            alert.setContentText("Teams rejected successfully!");
        }
        alert.showAndWait();
    }

    public void goToAdminPage3(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/AdminMenu.fxml"));
        ((Stage) back5.getScene().getWindow()).setScene(new Scene(root));
    }

    public void sendToAll(ActionEvent actionEvent) {
        if (textOfNotification.getText() == null || textOfNotification.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
            return;
        }
        Notification notification = new Notification(textOfNotification.getText(), LoginView.LoginUser, 0);
        for (User user : User.getUsers()) {
            user.getNotifications().add(notification);
        }
    }

    public void sendToTeam(ActionEvent actionEvent) {
        if (textOfNotification.getText() == null || textOfNotification.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
            return;
        }
        int response = Controller.controller.sendNotificationForTeam(nameOfTeam.getText());
        if (response == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("There is no team with this name");
            alert.showAndWait();
            return;
        }
        else {
            Notification notification = new Notification(textOfNotification.getText(), LoginView.LoginUser, 1);
            for (User user : Controller.controller.findTeam(nameOfTeam.getText()).getTeamMembers()) {
                user.getNotifications().add(notification);
            }
        }
    }

    public void sendToUser(ActionEvent actionEvent) {
        if (textOfNotification.getText() == null || textOfNotification.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
            return;
        }
        int response = Controller.controller.sendNotificationForUser(nameOfUser.getText());
        if (response == 1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("There is no team with this name");
            alert.showAndWait();
        }
        else {
            Notification notification = new Notification(textOfNotification.getText(), LoginView.LoginUser, 1);
            User.getUserByUsername(nameOfUser.getText()).getNotifications().add(notification);
        }
    }
}
