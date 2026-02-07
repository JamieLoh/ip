package sophon;

import java.io.IOException;
import java.util.Scanner;

import sophon.command.Command;
import sophon.exception.SophonException;
import sophon.parser.Parser;
import sophon.storage.Storage;
import sophon.task.TaskList;
import sophon.ui.UI;



/**
 * The main entry point of the Sophon task management application.
 * <p>
 * This class coordinates interactions between the parser, command execution,
 * task list management, and persistent storage.
 */
public class Sophon {
    private static final String DATA_FILE = "./data/sophon.txt";
    private UI ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for GUI usage.
     * Initializes core components once.
     */
    public Sophon() {
        ui = new UI();
        parser = new Parser();
        storage = new Storage(DATA_FILE);

        // load previous data stored locally first if any
        try {
            taskList = new TaskList(storage.load());
        } catch (SophonException.DataFileCorruptedException | IOException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Runs the Sophon application in CLI mode.
     * <p>
     * Continuously reads user input until the user exits the application with "bye".
     */
    public void run() {
        ui.showGreetingMessage();

        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().trim();
        while (!userInput.equals("bye")) {
            String response = getResponse(userInput);
            System.out.println(response);
        }
        sc.close();

        System.out.println("Bye bye! Sophon hopes to see you again soon! :) \n");
    }

    /**
     * Processes a single user input and returns the response.
     * <p>
     * This method is used by the GUI.
     *
     * @param input User input command string
     * @return Response message
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            return "Bye bye! Sophon hopes to see you again soon! :)";
        }

        try {
            Command command = parser.parseCommand(input);
            String result = command.execute(taskList, storage);
            storage.save(taskList);
            return result;
        } catch (SophonException | IOException e) {
            return e.getMessage();
        }
    }

    /**
     * The main method for CLI execution
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Sophon().run();
    }
}
