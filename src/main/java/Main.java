import controller.JsonController;
import model.Date;
import view.View;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {

        JsonController.getInstance().readFromJson();
        View.view.run();
    }
}
