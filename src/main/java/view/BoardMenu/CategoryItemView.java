package view.BoardMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import model.Category;
import model.Task;

import java.util.ArrayList;

public class CategoryItemView {
    public VBox Vbox;
    public Label lblTaskTitle;
    private Category category;
    private BoardMenuSecondPageForLeaderView parentController;

    public void setCategory(Category category){
        this.category = category;
    }
    public void updateCategory(){
        lblTaskTitle.setText(category.getName());
        Vbox.getChildren().clear();
        ArrayList<Task> tasks = category.getCategoryTasks();
        Node[] nodes = new Node[tasks.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InCategoryItems.fxml"));
                nodes[i] = loader.load();
                Vbox.getChildren().add(nodes[i]);
                InCategoryItemsView inCategoryItemsView = loader.getController();
                inCategoryItemsView.setTask(tasks.get(i));
                inCategoryItemsView.setParentController(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public BoardMenuSecondPageForLeaderView getParentController() {
        return parentController;
    }

    public void setParentController(BoardMenuSecondPageForLeaderView parentController) {
        this.parentController = parentController;
    }
}
