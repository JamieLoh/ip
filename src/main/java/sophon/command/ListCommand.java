package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.TaskList;
import sophon.ui.UI;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Shows all tasks in the task list.
     *
     * @param tasksList The task list to be displayed.
     * @param ui The UI component used to show messages.
     * @param storage The storage component (not used here).
     * @throws SophonException.EmptyListException If the task list is empty.
     */
    @Override
    public void execute(TaskList tasksList, UI ui, Storage storage) throws SophonException.EmptyListException {
        if (tasksList.isEmpty()) {
            throw new SophonException.EmptyListException();
        }
        ui.showTaskListMessage(tasksList.getTasksList());
    }
}
