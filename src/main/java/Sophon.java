import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sophon {
    private String logo =
            "  ____              _                 \n" +
                    " / ___|  ___  _ __ | |__   ___  _ __  \n" +
                    " \\___ \\ / _ \\| '_ \\| '_ \\ / _ \\| '_ \\ \n" +
                    "  ___) | (_) | |_) | | | | (_) | | | |\n" +
                    " |____/ \\___/| .__/|_| |_|\\___/|_| |_|\n" +
                    "             |_|                      \n";

    private final String GREETING_MESSAGE = "Hello, here is Sophon! How can I help you? \n";
    private final String EXIT_MESSAGE = "Bye bye! Sophon hopes to see you again soon! :) \n";
    private List<String> tasksList = new ArrayList<String>();

    public void addTask(String task){
        tasksList.add(task);
        System.out.println("Added task: " + task + "\n");
    }

    public void listTasks(){
        Integer counter = 1;
        for (String task : tasksList) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        System.out.println("\n");
    }


    public void run(){
        // greeting message
        System.out.println(logo);
        System.out.println(GREETING_MESSAGE);

        // interact with user
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while(!userInput.equals("bye")){
            // check whether to add or list task
            if (userInput.equals("list")){
                listTasks();
            } else {
                addTask(userInput);
            }
            userInput = sc.nextLine();
        }
        sc.close();

        // exit message
        System.out.println(EXIT_MESSAGE);
    }



    public static void main(String[] args) {
        // run the chatbot Sophon
        new Sophon().run();
    }
}
