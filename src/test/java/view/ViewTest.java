package view;

import controller.JsonController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.*;
import org.testng.annotations.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        View.view.register(command);
        assertEquals(response, outContent.toString().trim());
    }
    @Test
    public void login() throws ParseException {
        JsonController.getInstance().readFromJson();
        String command = "user login --username mamad --password 138014";
        String response = "There is not any user with username: mamad!";
        System.setOut(new PrintStream(outContent));
        View.view.logIn(command);
        assertEquals(response, outContent.toString().trim());
    }
//    @Test
//        public void mainTest() throws IOException, CsvValidationException, ParseException {
//        final File initialFile = new File("src/main/resources/input.txt");
//        final InputStream targetStream =
//                new DataInputStream(new FileInputStream(initialFile));
//            String responses = new String(Files.readAllBytes(Paths.get("src/main/resources/output.txt")));
//            System.setOut(new PrintStream(outContent));
//            System.setIn(targetStream);
//            View.view.run();
//            assertEquals(responses,outContent.toString().trim());
//        }


    }
