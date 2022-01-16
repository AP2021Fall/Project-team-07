package view.BoardMenu;

import controller.Controller;
import controller.LoggedController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Board;
import model.Task;
import model.User;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class BoardMenuFailedTasksPageView {

    public AnchorPane pane;
    public VBox vTaskItem;
    public Label response;
    public Label pFailed;
    private User user;
    private Board board;

    public void initialize() throws ParseException {
        user = LoggedController.getInstance().getLoggedInUser();
        board = LoggedController.getInstance().getSelectedBoard();
        makeTasksVbox();

    }
    private void makeTasksVbox() throws ParseException {
        vTaskItem.getChildren().clear();
        Controller.controller.updateFailed(board);
        ArrayList<Task> tasks = board.getFailed();
        Node[] nodes = new Node[tasks.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskItem.fxml"));
                BoardMenuReopenBTnVIew controller = new BoardMenuReopenBTnVIew();
                loader.setController(controller);
                nodes[i] = loader.load();
                vTaskItem.getChildren().add(nodes[i]);
                controller.setTask(tasks.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        pFailed.setText("board failed tasks percentage : "+
                Controller.controller.getBoardFailedPercentage(board));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/BoardMenuSecondPageForLeader.fxml"));
        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).setScene(new Scene(root));
    }

}
