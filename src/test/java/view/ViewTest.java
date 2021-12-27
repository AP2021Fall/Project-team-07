package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewTest {
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;
    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void register() throws ParseException {
        String command = "user create --username Amir --password1 2020 --password2 2020 --email Address Amir@gmail.com";
        String response = "user created successfully!";
        System.setOut(new PrintStream(outContent));
//        System.setIn(new ByteArrayInputStream(command.getBytes()));
        View.view.register(command);
        assertEquals(response, outContent.toString());
    }
}
