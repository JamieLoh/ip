import java.io.*;
import java.util.Scanner;

public class Sophon {
    private UI ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private final String DATA_FILE = "./data/sophon.txt";

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
        while(!userInput.equals("bye")){
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

    public static void main(String[] args){
        // instantiate and run Sophon
        new Sophon().run();
    }
}
