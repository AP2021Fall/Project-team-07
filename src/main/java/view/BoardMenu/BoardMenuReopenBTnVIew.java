package view.BoardMenu;

import controller.Controller;
import controller.LoggedController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import model.Board;
import model.Category;
import model.Task;
import model.User;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BoardMenuReopenBTnVIew implements Initializable {
    public BorderPane pane;
    public Label lblTaskPriority;
    public Button btnSelect;
    public Label lblTaskTitle;
    private Task selectTask;
    private User user;
    private Board board;
    private BoardMenuAddTaskPageView parent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnSelect.setStyle("-fx-background-color: #ee4f4f; ");
        btnSelect.setText("reopen");
        user = LoggedController.getInstance().getLoggedInUser();
        if (user.getRole().equals("Member"))pane.getChildren().remove(btnSelect);
        board = LoggedController.getInstance().getSelectedBoard();
        btnSelect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("ReOpen TASK");
                dialog.setHeaderText("Enter new deadline");
                dialog.setContentText("new deadline :");
                Optional<String> input = dialog.showAndWait();
                if (input.isPresent()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    int response = Controller.controller.updateDeadline(user,board.getTeam(),selectTask.getTitle(),
                            dialog.getEditor().getText(),board.getBoardName());
                    if (response == 2) {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("board construction is not done");
                    }
                    if (response == 5) {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("wrong date format");
                    }
                    if (response == 6) {
                        alert.setAlertType(Alert.AlertType.ERROR);
                        alert.setContentText("task successfully reopened");
                    }
//                    DialogPane dialogPane = alert.getDialogPane();
//                    dialogPane.getStylesheets().add(Objects.requireNonNull(parent.getClass().getResource("/CSS/DialogPane.css")).toExternalForm());
//                    dialogPane.getStyleClass().add("DialogPane");
                    alert.showAndWait();
                }

            }
        });
    }

    public void setTask(Task task) {
        lblTaskTitle.setText(task.getTitle());
        lblTaskPriority.setText(task.getPriority());
        selectTask = task;
    }

    public void setParent(BoardMenuAddTaskPageView parent) {
        this.parent = parent;
    }
}
