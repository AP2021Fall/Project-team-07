package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import model.Category;
import model.Task;

import java.util.ArrayList;

public class CategoryItemView {
    public BorderPane borderPane;
    public Label lblCategoryTitle;
    private Category category;

    public void setCategory(Category category){
        this.category = category;
    }
    public void updateCategory(){
        borderPane.getChildren().clear();
        borderPane.getChildren().add(lblCategoryTitle);
        lblCategoryTitle.setText(category.getName());
        ArrayList<Task> tasks = category.getCategoryTasks();
        Node[] nodes = new Node[tasks.size()];
        for (int i = 0; i < nodes.length; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/InCategoryItems.fxml"));
                nodes[i] = loader.load();
                borderPane.getChildren().add(nodes[i]);
                InCategoryItemsView inCategoryItemsView = loader.getController();
                inCategoryItemsView.setTask(tasks.get(i));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
