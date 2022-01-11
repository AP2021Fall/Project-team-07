package view;

import controller.LoggedController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Task;
import model.Team;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowTeamsForLeaderView implements Initializable {
    public AnchorPane pane;
    public ImageView exit;
    public Button showTeams;
    public Button sudoTask;
    public VBox vTeamItem;
    public Button createTeam;

    public void exit(MouseEvent mouseEvent) {
    }

    public void goToShowTeams(ActionEvent actionEvent) {
    }

    public void goToSUdoTask(ActionEvent actionEvent) {
    }

    public void goCreateTeam(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Team> teams = Team.getAllTeams();
        Node[] nodes = new Node[teams.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TeamItem.fxml"));
                TeamItemView controller = new TeamItemView();
                loader.setController(controller);
                nodes[i] = loader.load();
                vTeamItem.getChildren().add(nodes[i]);
                controller.setTeam(teams.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
