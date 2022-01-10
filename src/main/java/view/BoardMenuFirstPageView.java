package view;

import controller.Controller;
import controller.LoggedController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Board;
import model.Team;
import model.User;
import java.util.ArrayList;
import java.util.Objects;

public class BoardMenuFirstPageView {
    public TextField boardName;
    public AnchorPane pane;
    public Button makeBoardButton;
    public Label response;
    private Team team;
    private User user;
    public void initialize() {
        team = LoggedController.getInstance().getLoggedTeam();
        user = LoggedController.getInstance().getLoggedInUser();
        if(user.getRole().equals("Member"))
            pane.getChildren().remove(makeBoardButton);
        makeBoardsTable();
    }

    private void makeBoardsTable() {
        ObservableList<Board> list = FXCollections.observableArrayList(team.getBoards());
        TableView<Board> tableView = new TableView<>();
        tableView.setLayoutX(76.0);
        tableView.setLayoutY(58.0);
        TableColumn<Board, String> boardName = new TableColumn<>("BoardName");
        boardName.setPrefWidth(45);
        tableView.setPrefWidth(320);
        boardName.setCellValueFactory(new PropertyValueFactory<>("boardName"));
        tableView.getColumns().addAll(boardName);
        tableView.setItems(list);
        tableView.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/CSS/Table.css")).toExternalForm());
        pane.getChildren().add(tableView);
    }

//    public ArrayList<Board> getDoneBoards(){
//        ArrayList<Board> done = new ArrayList<>();
//        for (Board board : team.getBoards()){
//            if(!board.isCreated())
//                done.add(board);
//        }
//        return done;
//    }
    public void loginBoard(ActionEvent actionEvent){
        String boardNameText = boardName.getText();
        Board board = Board.getBoardByName(team.getBoards(),boardNameText);
        if(board==null){
            response.setText("there is no board with this name");
            return;
        }
        if(!board.isCreated())
            if(user.getRole().equals("Member")){
                response.setText("board creation hasn't finished");
                return;
            }
        LoggedController.getInstance().setSelectedBoard(board);
        if (user.getRole().equals("Member")){}
            //go to MemberPage
        else{
            //go to LeaderPage
        }

    }
    public void makeBoard(ActionEvent actionEvent){
        String boardNameText = boardName.getText();
        int controllerResponse = Controller.controller.makeBoard(user,team,boardNameText);
        if (controllerResponse==1)
            response.setText("there is already a board with this name");
        if (controllerResponse==2)
            response.setText("board successfully created! now u can login to board");
    }




}

