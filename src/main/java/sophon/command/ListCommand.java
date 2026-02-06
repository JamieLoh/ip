package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.Task;
import sophon.task.TaskList;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Shows all tasks in the task list.
     *
     * @param taskList The task list to be displayed.
     * @param storage The storage component (not used here).
     * @throws SophonException.EmptyListException If the task list is empty.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws SophonException.EmptyListException {
        if (taskList.isEmpty()) {
            throw new SophonException.EmptyListException();
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int counter = 1;
        for (Task task : taskList.getTasksList()) {
            sb.append(counter).append(". ").append(task).append("\n");
            counter++;
        }
        return sb.toString();
    }
}
