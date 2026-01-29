package sophon.ui;

import sophon.task.Task;

import java.util.List;

public class UI {
    private static final String LOGO =
            "  ____              _                 \n" +
                    " / ___|  ___  _ __ | |__   ___  _ __  \n" +
                    " \\___ \\ / _ \\| '_ \\| '_ \\ / _ \\| '_ \\ \n" +
                    "  ___) | (_) | |_) | | | | (_) | | | |\n" +
                    " |____/ \\___/| .__/|_| |_|\\___/|_| |_|\n" +
                    "             |_|                      \n";


    public void showGreetingMessage(){
        System.out.println(LOGO);
        System.out.println("Hello, here is Sophon! How can I help you? \n");
    }

    public void showGoodbyeMessage(){
        System.out.println("Bye bye! Sophon hopes to see you again soon! :) \n");
    }

    public void showAddTaskMessage(Task task, int size) {
        System.out.println("Got it! I have added this task:");
        System.out.println("    " + task.toString());
        System.out.println("Now you have " + size + " tasks in your list. \n");
    }

    public void showTaskListMessage(List<Task> tasksList){
        System.out.println("Here are the tasks in your list: ");
        int counter = 1;
        for (Task task : tasksList) {
            System.out.println(counter + ". " + task.toString());
            counter++;
        }
        System.out.println("\n");
    }

    public void showMarkMessage(Task task){
        System.out.println("Great! I have marked this task as done: ");
        System.out.println("    " + task.toString() + "\n");
    }

    public void showUnMarkMessage(Task task){
        System.out.println("Sure! I have marked this task as not done yet: ");
        System.out.println("    " + task.toString() + "\n");
    }

    public void showDeleteMessage(Task task, int size){
        System.out.println("Understood. I have removed this task from your task list: ");
        System.out.println("    " + task.toString());
        System.out.println("Now you have " + size + " tasks in your list. \n");
    }

    public void showErrorMessage(String errorMessage){
        System.out.println(errorMessage + "\n");
    }
}
