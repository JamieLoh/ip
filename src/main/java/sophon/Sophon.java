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
 * This class coordinates interactions between the UI, parser, command execution,
 * task list management, and persistent storage.
 */
public class Sophon {
    private static final String DATA_FILE = "./data/sophon.txt";
    private UI ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    /**
     * Runs the Sophon application.
     * <p>
     * This method initializes all components, loads stored tasks (if any),
     * and enters a loop to continuously read and execute user commands
     * until the user exits the application.
     */
    public void run() {
        ui = new UI();
        parser = new Parser();
        taskList = new TaskList();
        storage = new Storage(DATA_FILE);

        ui.showGreetingMessage();
        // load previous data stored locally first if any
        try {
            taskList = new TaskList(storage.load());
        } catch (SophonException.DataFileCorruptedException | IOException e) {
            ui.showErrorMessage(e.getMessage());
            taskList = new TaskList();
        }

        // interact with user
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().trim();
        while (!userInput.equals("bye")) {
            try {
                Command command = parser.parse(userInput);
                command.execute(taskList, ui, storage);
                storage.save(taskList);
            } catch (SophonException | IOException e) {
                System.out.println(e.getMessage() + "\n");
            }
            userInput = sc.nextLine();
        }
        sc.close();

        ui.showGoodbyeMessage();
    }

    /**
     * The main method that starts the Sophon application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Sophon().run();
    }
}
