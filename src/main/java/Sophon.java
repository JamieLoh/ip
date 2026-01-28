import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    // print messages
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

    // data file
    private final String DATA_FILE = "./data/sophon.txt";

    public static LocalDateTime convertStringToLocalDateTime(String date) throws SophonException.WrongFormatException {
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(date, df);
            return localDateTime;
        } catch (java.time.format.DateTimeParseException e) {
            throw new SophonException.WrongFormatException("Invalid time format in your command! Please provide time in this format: yyyy-MM-dd HH:mm:ss");
        }
    }

    public void addEventTask(String command) throws SophonException.WrongFormatException{
        // check format
        if (!command.matches(EVENT_COMMAND_PATTERN)) throw new SophonException.WrongFormatException("event [task] /from [start time] /to [end time] \n" + "Notice: Time should in yyyy-MM--dd HH:mm:ss format");

        // get task information
        int index = command.indexOf('/');
        String description = command.substring(6, index).trim();
        String timeRange = command.substring(index + 1);
        int timeIndex = timeRange.indexOf('/');
        String startTimeString = timeRange.substring(5, timeIndex).trim();
        String endTimeString = timeRange.substring(timeIndex + 4).trim();

        // convert startTime and endTime to LocalDateTime format
        LocalDateTime startTime = convertStringToLocalDateTime(startTimeString);
        LocalDateTime endTime = convertStringToLocalDateTime(endTimeString);

        // instantiate task and add the task to task list
        Task task = new Event(description, startTime, endTime);
        tasksList.add(task);

        System.out.println(ADD_TASK_MESSAGE);
        System.out.println("    " + task.toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in your list. \n");
    }

    public void addDeadlineTask(String command) throws SophonException.WrongFormatException{
        // check format
        if (!command.matches(DEADLINE_COMMAND_PATTERN)) throw new SophonException.WrongFormatException("deadline [task] /by [deadline] \n" + "Notice: Time should in yyyy-MM--dd HH:mm:ss format");

        // get task information
        int index = command.indexOf('/');
        String description = command.substring(9, index).trim();
        String deadlineString = command.substring(index + 4).trim();

        // convert deadline String to LocalDateTime format
        LocalDateTime deadline = convertStringToLocalDateTime(deadlineString);

        // instantiate and add the task to task list
        Task task = new Deadlines(description, deadline);
        tasksList.add(task);

        System.out.println(ADD_TASK_MESSAGE);
        System.out.println("    " + task.toString());
        System.out.println("Now you have " + tasksList.size() + " tasks in your list. \n");
    }

    public void addToDosTask(String command) throws SophonException.WrongFormatException{
        // check format
        if (!command.matches(TODOS_COMMAND_PATTERN)) throw new SophonException.WrongFormatException("todo [task]");

        // get task information
        String description = command.substring(5).trim();

        // instantiate and add the task to task list
        Task task = new Todo(description);
        tasksList.add(task);

        System.out.println(ADD_TASK_MESSAGE);
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

    public void addTask(Task task) {
        tasksList.add(task);
    }

    public void saveData() throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE);
        StringBuilder saveInformation  = new StringBuilder();
        for (Task task : tasksList) {
            // get task type
            String type = "";
            if (task instanceof Todo) {
                type = "T";
            } else if (task instanceof Deadlines) {
                type = "D";
            } else {
                type = "E";
            }

            // get task status (isDone?)
            int status = task.isDone() ? 1 : 0;

            // concatenate basic saveInformation: type | status | description
            saveInformation.append(type).append(" | ").append(status).append(" | ").append(task.getDescription());

            // concatenate additional information like deadline or event time range
            if (task instanceof Deadlines) {
                saveInformation.append(" | ").append(
                        ((Deadlines) task).getDeadline()
                );
            } else if (task instanceof Event) {
                saveInformation.append(" | ").append(
                        ((Event) task).getStartTime()
                ).append(" | ").append(
                        ((Event) task).getEndTime()
                );
            }
            saveInformation.append("\n");
        }
        fw.write(saveInformation.toString());
        fw.close();
    }

    public void loadData() throws SophonException.DataFileCorruptedException, IOException {
        File file = new File(DATA_FILE);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
            return;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String content;

        while ((content = br.readLine()) != null) {
            String[] parts = content.split(" \\| ");

            if (parts.length < 3) {
                throw new SophonException.DataFileCorruptedException();
            }

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task;

            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadlines(description, LocalDateTime.parse(parts[3]));
                break;
            case "E":
                task = new Event(description, LocalDateTime.parse(parts[3]),  LocalDateTime.parse(parts[4]));
                break;
            default:
                throw new SophonException.DataFileCorruptedException();
            }

            if (isDone) {
                task.markAsDone();
            }
            addTask(task);
        }
        br.close();
    }

    public void interpretCommand(String userInput) throws SophonException {
        for (Command command : Command.values()){
            // find respected command pattern and execute respected command
            if (command.match(userInput)) {
                command.execute(userInput, this);
                return;
            }
        }
        throw new SophonException.UnknownCommandException();
    }

    public void run(){
        // print greeting message
        System.out.println(LOGO);
        System.out.println(GREETING_MESSAGE);

        // load previous data stored locally first if any
        try {
            loadData();
        } catch (SophonException.DataFileCorruptedException | IOException e) {
            System.out.println(e.getMessage() + "\n");
        }

        // interact with user
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine().trim();
        while(!userInput.equals("bye")){
            try {
                interpretCommand(userInput);
                saveData();
            } catch (SophonException | IOException e) {
                System.out.println(e.getMessage() + "\n");
            }
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
