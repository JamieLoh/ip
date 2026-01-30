package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.Task;
import sophon.task.TaskList;
import sophon.ui.UI;

/**
 * Represents a command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    private static final String DELETE_TASK_COMMAND_PATTERN = "^delete \\d+$";
    private final int taskIndex;

    /**
     * Constructs a {@code DeleteCommand} by parsing user input.
     *
     * @param command The full user input string.
     * @throws SophonException.WrongFormatException If the command format is invalid.
     */
    public DeleteCommand(String command) throws SophonException.WrongFormatException {
        if (!command.matches(DELETE_TASK_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("delete [task number]");
        }
        taskIndex = Integer.parseInt(command.substring(7)) - 1;
    }

    /**
     * Removes the specified task from the task list and shows a confirmation message.
     *
     * @param taskList The task list to remove the task from.
     * @param ui The UI component used to display messages.
     * @param storage The storage component (not used here).
     * @throws SophonException.TaskNotFoundException If the task index is invalid.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws SophonException.TaskNotFoundException {
        Task removedTask = taskList.remove(taskIndex);
        ui.showDeleteMessage(removedTask, taskList.size());
    }
}
