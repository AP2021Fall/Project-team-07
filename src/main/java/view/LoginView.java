package view;

import controller.Controller;
import controller.JsonController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.ParseException;

public class LoginView extends Application {

    public TextField username;
    public PasswordField password;
    public Button login;
    public Label errorlabel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        primaryStage.setTitle("Snake");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        JsonController.getInstance().readFromJson();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void login(ActionEvent actionEvent) throws ParseException {

        int result = Controller.controller.logIn(username.getText(), password.getText());

        errorlabel.setText(Integer.toString(result));

    }
}

