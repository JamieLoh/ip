package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.Task;
import sophon.task.TaskList;

/**
 * Represents a command that marks a task as not done.
 */
public class UnmarkCommand extends Command {
    private static final String UNMARK_TASK_COMMAND_PATTERN = "^unmark \\d+$";
    private final int taskIndex;

    /**
     * Constructs an {@code UnmarkCommand} by parsing user input.
     *
     * @param command The full user input string.
     * @throws SophonException.WrongFormatException If the command format is invalid.
     */
    public UnmarkCommand(String command) throws SophonException.WrongFormatException {
        // check format
        if (!command.matches(UNMARK_TASK_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("mark [task number]");
        }

        taskIndex = Integer.parseInt(command.substring(7)) - 1;
    }

    /**
     * Marks the specified task as not done and returns a confirmation message.
     *
     * @param taskList The task list containing the task.
     * @param storage The storage component (not used here).
     * @throws SophonException.TaskNotFoundException If the task index is invalid.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws SophonException.TaskNotFoundException {
        Task task = taskList.get(taskIndex);
        task.markAsNotDone();

        String message = "Sure! I have marked this task as not done yet: \n"
                + "    " + task + "\n";
        return message;
    }
}
