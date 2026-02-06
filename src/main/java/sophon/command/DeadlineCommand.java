package sophon.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.Deadlines;
import sophon.task.Task;
import sophon.task.TaskList;



/**
 * Represents a command that adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    private static final String DEADLINE_COMMAND_PATTERN = "^deadline .+ /by .+$";

    private final String description;
    private final LocalDateTime deadline;

    /**
     * Constructs a {@code DeadlineCommand} by parsing user input.
     *
     * @param command The full user input string.
     * @throws SophonException.WrongFormatException If the command format is invalid.
     */
    public DeadlineCommand(String command) throws SophonException.WrongFormatException {
        // check format
        if (!command.matches(DEADLINE_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("deadline [task] /by [deadline] \n" + "Notice: Time should in yyyy-MM--dd HH:mm:ss format");
        }

        // get task information
        int index = command.indexOf('/');
        description = command.substring(9, index).trim();
        String deadlineString = command.substring(index + 4).trim();

        // convert deadline String to LocalDateTime format
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            deadline = LocalDateTime.parse(deadlineString, df);
        } catch (DateTimeParseException e) {
            throw new SophonException.WrongFormatException("deadline [task] /by [deadline] \n" + "Notice: Time should in yyyy-MM--dd HH:mm:ss format");
        }
    }

    /**
     * Adds the deadline task to the task list and returns a confirmation message.
     *
     * @param taskList The task list to add the task to.
     * @param storage The storage component (not used here).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        // instantiate and add the task to task list
        Task task = new Deadlines(description, deadline);
        taskList.add(task);

        String message = "Got it! I have added this task:\n"
                + "    " + task + "\n"
                + "Now you have " + taskList.size() + " tasks in your list. \n";
        return message;
    }
}
