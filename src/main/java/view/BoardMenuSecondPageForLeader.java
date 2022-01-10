package view;

import controller.Controller;
import controller.LoggedController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Board;
import model.User;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class BoardMenuSecondPageForLeader {

    public AnchorPane pane;
    public Label response;
    private User user;
    private Board board;

    public void initialize() {
        user = LoggedController.getInstance().getLoggedInUser();
        board = LoggedController.getInstance().getSelectedBoard();
    }

    public void removeBoard(javafx.event.ActionEvent actionEvent) throws IOException {
        boolean confirmation = false;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation");
        alert.setContentText("DO YOU WANT TO CONTINUE ? (there would be no way to bring removed board back");
        ButtonType show = new ButtonType("Show in Explorer");
        alert.getButtonTypes().add(show);
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) confirmation = true;
        if (confirmation) {
            int controllerResponse = Controller.controller.removeBoard(user, board.getTeam(), board.getBoardName());
            response.setText("board removed successfully");
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/FirstPage.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }



}
