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

    // messages for print
    private final String GREETING_MESSAGE = "Hello, here is Sophon! How can I help you? \n";
    private final String EXIT_MESSAGE = "Bye bye! Sophon hopes to see you again soon! :) \n";
    private final String LIST_MESSAGE = "Here are the tasks in your list: ";
    private final String ADD_TASK_MESSAGE = "Got it! I have added this task:";

    // user input command patterns
    private final String EVENT_COMMAND_PATTERN = "^event .+ /from .+ /to .+$";
    private final String DEADLINE_COMMAND_PATTERN = "^deadline .+ /by .+$";
    private final String TODOS_COMMAND_PATTERN = "^todo .+$";
    private final String MARK_TASK_COMMAND_PATTERN = "^mark \\d+$";
    private final String UNMARK_TASK_COMMAND_PATTERN = "^unmark \\d+$";
    private final String DELETE_TASK_COMMAND_PATTERN = "^delete \\d+$";

    // task list
    private List<Task> tasksList = new ArrayList<Task>();

    public void addEventTask(String command) throws SophonException.WrongFormatException{
        // check format
        if (!command.matches(EVENT_COMMAND_PATTERN)) throw new SophonException.WrongFormatException("event [task] /from [start time] /to [end time]");

        System.out.println(ADD_TASK_MESSAGE);

        // get task information
        int index = command.indexOf('/');
        String description = command.substring(6, index).trim();
        String time = command.substring(index + 1).replaceAll("/", "");

        // instantiate task and add the task to task list
        Task task = new Event(description, time);
        tasksList.add(task);

        System.out.println("    " + task.toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in your list. \n");
    }

    public void addDeadlineTask(String command) throws SophonException.WrongFormatException{
        // check format
        if (!command.matches(DEADLINE_COMMAND_PATTERN)) throw new SophonException.WrongFormatException("deadline [task] / by [deadline]");

        System.out.println(ADD_TASK_MESSAGE);

        // get task information
        int index = command.indexOf('/');
        String description = command.substring(9, index).trim();
        String deadline = command.substring(index + 1);

        // instantiate and add the task to task list
        Task task = new Deadlines(description, deadline);
        tasksList.add(task);

        System.out.println("    " + task.toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in your list. \n");
    }

    public void addToDosTask(String command) throws SophonException.WrongFormatException{
        // check format
        if (!command.matches(TODOS_COMMAND_PATTERN)) throw new SophonException.WrongFormatException("todo [task]");

        System.out.println(ADD_TASK_MESSAGE);

        // get task information
        String description = command.substring(5).trim();

        // instantiate and add the task to task list
        Task task = new Todo(description);
        tasksList.add(task);

        System.out.println("    " + task.toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in your list. \n");
    }

    public void listTasks() throws SophonException.EmptyListException{
        // check whether the task list is empty
        if (tasksList.isEmpty()) throw new SophonException.EmptyListException();

        int counter = 1;
        System.out.println(LIST_MESSAGE);
        for (Task task : tasksList) {
            System.out.println(counter + ". " + task.toString());
            counter++;
        }
        System.out.println("\n");
    }

    // mark task as done
    public void markTask(String command) throws SophonException.WrongFormatException, SophonException.TaskNotFoundException{
        // check format
        if (!command.matches(MARK_TASK_COMMAND_PATTERN)) throw new SophonException.WrongFormatException("mark [task number]");

        // check whether task number is valid
        int taskIndex = Integer.parseInt(command.substring(5)) - 1;
        if (taskIndex < 0 || taskIndex >= tasksList.size()) throw new SophonException.TaskNotFoundException();

        // mark task as done
        System.out.println("Great! I have marked this task as done: ");
        Task task = tasksList.get(taskIndex);
        task.markAsDone();
        System.out.println("    " + task.toString() + "\n");
    }

    // mark task as not done yet
    public void unmarkTask(String command) throws SophonException.WrongFormatException, SophonException.TaskNotFoundException{
        // check format
        if (!command.matches(UNMARK_TASK_COMMAND_PATTERN)) throw new SophonException.WrongFormatException("mark [task number]");

        // check whether task number is valid
        int taskIndex = Integer.parseInt(command.substring(7)) - 1;
        if (taskIndex < 0 || taskIndex >= tasksList.size()) throw new SophonException.TaskNotFoundException();

        // mark the task as not done
        System.out.println("Sure! I have marked this task as not done yet: ");
        Task task = tasksList.get(taskIndex);
        task.markAsNotDone();
        System.out.println("    " + task.toString() + "\n");
    }

    public void deleteTask(String command) throws SophonException.WrongFormatException, SophonException.TaskNotFoundException{
        // check format
        if (!command.matches(DELETE_TASK_COMMAND_PATTERN)) throw new SophonException.WrongFormatException("delete [task number]");

        // check whether task number is valid
        int taskIndex = Integer.parseInt(command.substring(7)) - 1;
        if (taskIndex < 0 || taskIndex >= tasksList.size()) throw new SophonException.TaskNotFoundException();

        // delete task
        System.out.println("Understood. I have removed this task from your task list: ");
        Task task = tasksList.get(taskIndex);
        System.out.println("    " + task.toString());
        tasksList.remove(taskIndex);
        System.out.println("Now you have " + tasksList.size() + " tasks in your list. \n");
    }

    public void interpretCommand(String command){
        try {
            if (command.equals("list")){
                listTasks();
            } else if (command.startsWith("mark")){
                markTask(command);
            } else if (command.startsWith("unmark")){
                unmarkTask(command);
            } else if (command.startsWith("todo")) {
                addToDosTask(command);
            } else if (command.startsWith("deadline")) {
                addDeadlineTask(command);
            } else if (command.startsWith("event")) {
                addEventTask(command);
            } else if (command.startsWith("delete")) {
                deleteTask(command);
            } else {
                throw new SophonException.UnkownCommandException();
            }
        } catch (SophonException.UnkownCommandException | SophonException.WrongFormatException | SophonException.TaskNotFoundException | SophonException.EmptyListException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void run(){
        // print greeting message
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

        // print exit message
        System.out.println(EXIT_MESSAGE);
    }

    public static void main(String[] args){
        // instantiate and run Sophon
        new Sophon().run();
    }
}
