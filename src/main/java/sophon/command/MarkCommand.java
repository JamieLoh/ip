package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.Task;
import sophon.task.TaskList;
import sophon.ui.UI;

/**
 * Represents a command that marks a task as done.
 */
public class MarkCommand extends Command {
    private static final String MARK_TASK_COMMAND_PATTERN = "^mark \\d+$";
    private final int taskIndex;

    /**
     * Constructs a {@code MarkCommand} by parsing user input.
     *
     * @param command The full user input string.
     * @throws SophonException.WrongFormatException If the command format is invalid.
     */
    public MarkCommand(String command) throws SophonException.WrongFormatException {
        // check format
        if (!command.matches(MARK_TASK_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("mark [task number]");
        }

        taskIndex = Integer.parseInt(command.substring(5)) - 1;
    }

    /**
     * Marks the specified task as done and shows a confirmation message.
     *
     * @param taskList The task list containing the task.
     * @param ui The UI component used to display messages.
     * @param storage The storage component (not used here).
     * @throws SophonException.TaskNotFoundException If the task index is invalid.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws SophonException.TaskNotFoundException {
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        ui.showMarkMessage(task);
    }
}
