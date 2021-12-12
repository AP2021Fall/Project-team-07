import model.Date;
import view.View;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        //View.view.run();
        Date date1 = new Date("1400/03/03|18:50");
        Date date2 = new Date("1400/03/03|18:55");
        System.out.println(Date.getTimeBetween(date1,date2));
        System.out.println(Date.getNow().toString());

    }
}
