package view;

import controller.Controller;
import controller.LoggedController;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.Task;
import model.Team;
import model.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowTeamForLeaderView implements Initializable {
    public AnchorPane pane;
    public ImageView exit;
    public Button showTeams;
    public Button sudoTask;
    public TextField taskTitleField;
    public ChoiceBox members;
    public ListView membersList;
    public Button addMember;
    public Button addMember1;
    public Button deleteMember;
    public Label lblError;
    private final Team selectTeam = LoggedController.getInstance().getSelectedTeam();
    private int result;


    public void exit(MouseEvent mouseEvent) {
    }

    public void goToShowTeams(ActionEvent actionEvent) {
    }

    public void goToSUdoTask(ActionEvent actionEvent) {
    }

    public void addMember(ActionEvent actionEvent) {
        if (membersList.getItems().contains(members.getValue().toString()))
            lblError.setText("Old added to list!");
        else {
            membersList.getItems().add(members.getValue().toString());
            result = Controller.controller.addMember(LoginView.LoginUser, selectTeam, members.getValue().toString());
            lblError.setText("User successfully added!");
        }
    }

    public void deleteMember(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskTitleField.setText(selectTeam.getTeamName());

        for (User user : selectTeam.getTeamMembers()) {
            membersList.getItems().add(user.getUserName());
        }
        for (User member : User.getUsers()) {
            members.getItems().add(member.getUserName());
        }
        members.setValue(User.getUsers().get(0).getUserName());

    }
}
