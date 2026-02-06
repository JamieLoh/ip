package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.Task;
import sophon.task.TaskList;
import sophon.task.Todo;


/**
 * Represents a command that adds a todo task to the task list.
 */
public class TodoCommand extends Command {
    private static final String TODOS_COMMAND_PATTERN = "^todo .+$";
    private final String description;

    /**
     * Constructs a {@code TodoCommand} by parsing user input.
     *
     * @param command The full user input string.
     * @throws SophonException.WrongFormatException If the command format is invalid.
     */
    public TodoCommand(String command) throws SophonException.WrongFormatException {
        // check format
        if (!command.matches(TODOS_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("todo [task]");
        }

        // get task information
        description = command.substring(5).trim();
    }

    /**
     * Adds the todo task to the task list and return a confirmation message.
     *
     * @param taskList The task list to add the task to.
     * @param storage The storage component (not used here).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        // instantiate and add the task to task list
        Task task = new Todo(description);
        taskList.add(task);
        String message = "Got it! I have added this task:\n"
                + "    " + task + "\n"
                + "Now you have " + taskList.size() + " tasks in your list. \n";
        return message;
    }
}
