package view;

import controller.Controller;
import controller.JsonController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.text.ParseException;

public class LoginView extends Application {

    public static User LoginUser;
    public TextField username;
    public PasswordField password;
    public Button login;
    public Label errorLabel;
    public TextField welcomeText;
    public Button register;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        primaryStage.setTitle("phase2");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        JsonController.getInstance().readFromJson();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void login(ActionEvent actionEvent) throws ParseException {
        String username1 = username.getText();
        String password1 = password.getText();
        int response = Controller.controller.logIn(username1, password1);
        if (username1.isEmpty() || password1.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("You should fill the fields!");
            alert.showAndWait();
        }
        else if (response == 1){
            errorLabel.setText("There is not any user with username: " + username1 + "!");
        }
        else if (response == 2){
            errorLabel.setText("Username and password didn’t match!");
        }
        else if (response == 3){
            // go to memberMenu page
        }
        else if (response == 4){
            // go to leaderMenu page
        }
        else if (response == 5){
            // go to adminMenu page
        }
    }

    public void register(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Register.fxml"));
        ((Stage) errorLabel.getScene().getWindow()).setScene(new Scene(root));
    }
}

