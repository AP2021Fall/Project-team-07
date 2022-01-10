package view;

import controller.LoggedController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Board;
import model.Team;

import java.util.ArrayList;
import java.util.Objects;

public class BoardMenuFirstPageView {
    public TextField boardName;
    public AnchorPane pane;
    public Button makeBoardButton;
    private Team team;
    public void initialize() {
        team = LoggedController.getInstance().getLoggedTeam();
        if(LoggedController.getInstance().getLoggedInUser().getRole().equals("Member"))
            pane.getChildren().remove(makeBoardButton);
        makeBoardsTable();

    }

    private void makeBoardsTable() {
        ObservableList<Board> list = FXCollections.observableArrayList(getDoneBoards());
        TableView<Board> tableView = new TableView<>();
        tableView.setLayoutX(76.0);
        tableView.setLayoutY(58.0);
        TableColumn<Board, String> boardName = new TableColumn<>("BoardName");
        boardName.setPrefWidth(45);
        tableView.setPrefWidth(320);
        boardName.setCellValueFactory(new PropertyValueFactory<>("boardName"));
        tableView.getColumns().addAll(boardName);
        tableView.setItems(list);
        tableView.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/Scoreboard.css")).toExternalForm());
        pane.getChildren().add(tableView);
    }

    public ArrayList<Board> getDoneBoards(){
        ArrayList<Board> done = new ArrayList<>();
        for (Board board : team.getBoards()){
            if(!board.isCreated())
                done.add(board);
        }
        return done;
    }



}

