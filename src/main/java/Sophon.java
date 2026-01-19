import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sophon {
    private final String LOGO =
            "  ____              _                 \n" +
                    " / ___|  ___  _ __ | |__   ___  _ __  \n" +
                    " \\___ \\ / _ \\| '_ \\| '_ \\ / _ \\| '_ \\ \n" +
                    "  ___) | (_) | |_) | | | | (_) | | | |\n" +
                    " |____/ \\___/| .__/|_| |_|\\___/|_| |_|\n" +
                    "             |_|                      \n";

    private final String GREETING_MESSAGE = "Hello, here is Sophon! How can I help you? \n";
    private final String EXIT_MESSAGE = "Bye bye! Sophon hopes to see you again soon! :) \n";
    private final String LIST_MESSAGE = "Here are the tasks in your list: ";
    private List<Task> tasksList = new ArrayList<Task>();


    public void addTask(String command){
        System.out.println("Got it! I have added this task:");
        if (command.startsWith("todo")) {
            String description = command.substring(5).trim();
            addTodo(description);
        } else if (command.startsWith("deadline")) {
            int index = command.indexOf('/');
            String description = command.substring(9, index).trim();
            String deadline = command.substring(index + 1);
            addDeadline(description, deadline);
        } else {
            int index = command.indexOf('/');
            String description = command.substring(6, index).trim();
            String time = command.substring(index + 1).replaceAll("/", "");
            addEvent(description, time);
        }
        System.out.println("Now you have " + tasksList.size() + " tasks in your list. \n");
    }

    public void addEvent(String description, String time){
        Task task = new Event(description, time);
        tasksList.add(task);
        System.out.println("    " + task.toString());
    }

    public void addDeadline(String description, String by){
        Task task = new Deadlines(description, by);
        tasksList.add(task);
        System.out.println("    " + task.toString());
    }

    public void addTodo(String description){
        Task task = new Todo(description);
        tasksList.add(task);
        System.out.println("    " + task.toString());
    }

    public void listTasks(){
        Integer counter = 1;
        System.out.println(LIST_MESSAGE);
        for (Task task : tasksList) {
            System.out.println(counter + ". " + task.toString());
            counter++;
        }
        System.out.println("\n");
    }

    public void markTask(String markInformation){
        System.out.println("Great! I have marked this task as done: ");

        int taskIndex = Integer.parseInt(markInformation.substring(5)) - 1;
        Task task = tasksList.get(taskIndex);
        task.markAsDone();

        System.out.println("    " + task.toString() + "\n");
    }

    public void unmarkTask(String unmarkInformation){
        System.out.println("Sure! I have marked this task as not done yet: ");

        int taskIndex = Integer.parseInt(unmarkInformation.substring(7)) - 1;
        Task task = tasksList.get(taskIndex);
        task.markAsNotDone();

        System.out.println("    " + task.toString() + "\n");
    }

    public void interpretCommand(String command){
        if (command.equals("list")){
            listTasks();
        } else if (command.startsWith("mark ")){
            markTask(command);
        } else if (command.startsWith("unmark ")){
            unmarkTask(command);
        } else {
            addTask(command);
        }
    }

    public void run(){
        // greeting message
        System.out.println(LOGO);
        System.out.println(GREETING_MESSAGE);

        // interact with user
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().trim();
        while(!userInput.equals("bye")){
            interpretCommand(userInput);
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
