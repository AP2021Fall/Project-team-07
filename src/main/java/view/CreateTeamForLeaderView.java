package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateTeamForLeaderView {
    public ImageView exit;
    public Button showTeams;
    public Button sudoTask;
    public TextField taskTitleField;
    public Button btnCreate;
    public ChoiceBox members;
    public ListView membersList;
    public Button addMember;
    public Button addMember1;
    public Button deleteMember;
    public AnchorPane pane;

    public void exit(MouseEvent mouseEvent) {
    }

    public void goToShowTeams(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ShowTeamsForLeader.fxml"));
        ((Stage) pane.getScene().getWindow()).setScene(new Scene(root));
    }

    public void goToSUdoTask(ActionEvent actionEvent) {
    }

    public void Create(ActionEvent actionEvent) {
    }

    public void addMember(ActionEvent actionEvent) {
    }

    public void deleteMember(ActionEvent actionEvent) {
    }
}
