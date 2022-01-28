package view;

import controller.Controller;
import controller.JsonController;
import controller.LoggedController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Task;
import model.Team;
import model.User;

import java.io.IOException;

public class CreateTeamForLeaderView {
    public ImageView exit;
    public Button showTeams;
    public TextField teamTitleField;
    public Button btnCreate;
    public ChoiceBox members;
    public ListView membersList;
    public Button addMember;
    public Button addMember1;
    public Button deleteMember;
    public AnchorPane pane;
    public Label error;
    public Button leave;
    public Button suspendMember;
    private Team selectedTeam;
    private int result;

    public void exit(MouseEvent mouseEvent) {
        LoggedController.getInstance().setSelectedTask(null);
        JsonController.getInstance().updateJson();
        System.exit(0);
    }

    public void goToShowTeams(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ShowTeamsForLeader.fxml"));
        ((Stage) pane.getScene().getWindow()).setScene(new Scene(root));
    }

    public void Create(ActionEvent actionEvent) {
        int status = Controller.controller.creatTeam(LoggedController.getInstance().getLoggedInUser(), teamTitleField.getText());
        if (status == 1)
            error.setText("There is another team with this name!");
        else if (status == 2)
            error.setText("Team name is invalid!");
        else if (status == 3) {
            error.setText("Team created successfully! Waiting For Admin’s confirmation…");
            selectedTeam = Team.getTeamByName(teamTitleField.getText(), Team.getAllTeams());
        }
    }

    public void addMember(ActionEvent actionEvent) {
        if (membersList.getItems().contains(members.getValue().toString()))
            error.setText("Old added to list!");
        else {
            membersList.getItems().add(members.getValue().toString());
            result = Controller.controller.addMember(LoginView.LoginUser,
                    selectedTeam,
                    members.getValue().toString());
            error.setText("User successfully added!");
        }
    }

    public void deleteMember(ActionEvent actionEvent) {
        String selectedItem = membersList.getSelectionModel().getSelectedItem().toString();
        result = Controller.controller.deleteMember(LoggedController.getInstance().getLoggedInUser(), selectedTeam, selectedItem);
        membersList = null;
        for (User user : selectedTeam.getTeamMembers()) {
            membersList.getItems().add(user.getUserName());
        }
    }

    public void leave(ActionEvent actionEvent) throws IOException {
        LoggedController.getInstance().setSelectedTeam(null);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LeaderMenu.fxml"));
        ((Stage) pane.getScene().getWindow()).setScene(new Scene(root));
    }

    public void suspendMember(ActionEvent actionEvent) {
        String selectedItem = membersList.getSelectionModel().getSelectedItem().toString();
        result = Controller.controller.suspendMember(LoggedController.getInstance().getLoggedInUser(), selectedTeam, selectedItem);
        membersList = null;
        for (User user : selectedTeam.getTeamMembers()) {
            membersList.getItems().add(user.getUserName());
        }
    }
}
