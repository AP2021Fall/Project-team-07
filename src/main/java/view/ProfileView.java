package view;

import controller.Controller;
import controller.LoggedController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileView {
    public ImageView pictureOfUser;
    public Button editProfile;
    public Button notification;
    public Label userNameInProfilePage;
    public Label emailInProfilePage;
    public Label roleInProfilePage;
    public Label scoreInProfilePage;
    public Button back;
    public Button update;
    public Button changeUsername;
    public TextField currentUsername;
    public TextField newUsername;
    public TextField currentPassword;
    public TextField newPassword;
    public Button changePassword;
    public Button back2;
    public Button team;
    public Button log;

    public void editProfile(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/EditProfile.fxml"));
        ((Stage) editProfile.getScene().getWindow()).setScene(new Scene(root));
    }

    public void goToNotification(ActionEvent actionEvent) {
    }

    public void backToMenu(ActionEvent actionEvent) throws IOException {
        if (LoggedController.getInstance().getLoggedInUser().getRole().equals("Member")) {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MemberMenu.fxml"));
            ((Stage) back.getScene().getWindow()).setScene(new Scene(root));
        } else if (LoggedController.getInstance().getLoggedInUser().getRole().equals("Leader")) {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/LeaderMenu.fxml"));
            ((Stage) back.getScene().getWindow()).setScene(new Scene(root));
        }
    }

    public void updateProfile(ActionEvent actionEvent) {
        userNameInProfilePage.setText(LoggedController.getInstance().getLoggedInUser().getUserName());
        emailInProfilePage.setText(LoggedController.getInstance().getLoggedInUser().getEmail());
        roleInProfilePage.setText(LoggedController.getInstance().getLoggedInUser().getRole());
        scoreInProfilePage.setText(Integer.toString(LoggedController.getInstance().getLoggedInUser().getScore()));
    }

    public void showTeams(ActionEvent actionEvent) {
    }

    public void showLogs(ActionEvent actionEvent) {
    }

    public void changeUsername(ActionEvent actionEvent) {
        if (currentUsername.getText().equals("") || newUsername.getText().equals("") || currentUsername.getText() == null || newUsername.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
            return;
        }
        int response = Controller.controller.changeUserName(LoggedController.getInstance().getLoggedInUser(), newUsername.getText());
        if (response == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Your new username must include at least 4 characters!");
            alert.showAndWait();
        } else if (response == 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("username already taken !");
            alert.showAndWait();
        } else if (response == 3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("New username contains Special Characters! Please remove them and try again");
            alert.showAndWait();
        } else if (response == 4) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("you already have this username !");
            alert.showAndWait();
        } else if (response == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Change username successfully!");
            alert.showAndWait();
        }

    }

    public void changePassword(ActionEvent actionEvent) {
        if (currentPassword.getText().equals("") || newPassword.getText().equals("") || currentPassword.getText() == null || newPassword.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
            return;
        }
        int response = Controller.controller.changePassword(LoggedController.getInstance().getLoggedInUser(), currentPassword.getText(), newPassword.getText());
        if (response == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("wrong old password !");
            alert.showAndWait();
        } else if (response == 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Please type a New Password !");
            alert.showAndWait();
        } else if (response == 3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Please Choose A strong Password (Containing at least 8 characters including 1 digit \" +\n" +
                    "                    \"and 1 Capital Letter)");
            alert.showAndWait();
        } else if (response == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Change password successfully !");
            alert.showAndWait();
        }
    }
    public void backToProfilePage(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Profile.fxml"));
        ((Stage) back2.getScene().getWindow()).setScene(new Scene(root));
    }
}
