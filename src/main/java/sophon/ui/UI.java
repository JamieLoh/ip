package sophon.ui;

import java.util.List;

import sophon.task.Task;



/**
 * Handles all user-facing output for the Sophon application.
 * <p>
 * This class is responsible for displaying messages, task information,
 * and error messages to the user via the command line interface.
 * It does not contain any application logic.
 */
public class UI {
    private static final String LOGO =
            "  ____              _                 \n" +
                    " / ___|  ___  _ __ | |__   ___  _ __  \n" +
                    " \\___ \\ / _ \\| '_ \\| '_ \\ / _ \\| '_ \\ \n" +
                    "  ___) | (_) | |_) | | | | (_) | | | |\n" +
                    " |____/ \\___/| .__/|_| |_|\\___/|_| |_|\n" +
                    "             |_|                      \n";


    /**
     * Displays the greeting message and application logo when Sophon starts.
     */
    public void showGreetingMessage() {
        System.out.println(LOGO);
        System.out.println("Hello, here is Sophon! How can I help you? \n");
    }

    /**
     * Displays the goodbye message when the user exits the application.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye bye! Sophon hopes to see you again soon! :) \n");
    }

    /**
     * Displays a confirmation message after a task is added.
     *
     * @param task The task that was added.
     * @param size The updated number of tasks in the task list.
     */
    public void showAddTaskMessage(Task task, int size) {
        System.out.println("Got it! I have added this task:");
        System.out.println("    " + task.toString());
        System.out.println("Now you have " + size + " tasks in your list. \n");
    }

    /**
     * Displays all tasks currently stored in the task list.
     *
     * @param tasksList The list of tasks to be displayed.
     */
    public void showTaskListMessage(List<Task> tasksList) {
        System.out.println("Here are the tasks in your list: ");
        int counter = 1;
        for (Task task : tasksList) {
            System.out.println(counter + ". " + task.toString());
            counter++;
        }
        System.out.println("\n");
    }

    /**
     * Displays a message confirming that a task has been marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkMessage(Task task) {
        System.out.println("Great! I have marked this task as done: ");
        System.out.println("    " + task.toString() + "\n");
    }

    /**
     * Displays a message confirming that a task has been marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showUnMarkMessage(Task task) {
        System.out.println("Sure! I have marked this task as not done yet: ");
        System.out.println("    " + task.toString() + "\n");
    }

    /**
     * Displays a confirmation message after a task is deleted.
     *
     * @param task The task that was removed.
     * @param size The updated number of tasks in the task list.
     */
    public void showDeleteMessage(Task task, int size) {
        System.out.println("Understood. I have removed this task from your task list: ");
        System.out.println("    " + task.toString());
        System.out.println("Now you have " + size + " tasks in your list. \n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showErrorMessage(String errorMessage) {
        System.out.println(errorMessage + "\n");
    }

    /**
     * Displays all tasks that match the search keyword.
     *
     * @param foundTasks List of matching tasks.
     */
    public void showFoundTasks(List<Task> foundTasks) {
        System.out.println("Here are the matching tasks in your list: ");
        int counter = 1;
        for (Task task : foundTasks) {
            System.out.println(counter + ". " + task.toString());
            counter++;
        }
        System.out.println("\n");
    }
}
