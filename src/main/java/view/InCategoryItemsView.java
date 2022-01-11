package view;

import javafx.scene.control.Button;
import model.Task;
import model.Team;

public class InCategoryItemsView {
    public Button btnSelect;
    private Task task;
    public void setTask(Task task){
        this.task = task;
        btnSelect.setText(task.getTitle());
    }
}
