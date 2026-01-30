package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.TaskList;
import sophon.ui.UI;

import java.io.IOException;

/**
 * Represents an executable user command in the Sophon application.
 * <p>
 * Each concrete subclass defines a specific user action and implements
 * the {@code execute} method to perform that action.
 */
public abstract class Command {
    /**
     * Executes the command with access to the task list, UI, and storage.
     *
     * @param taskList The task list to operate on.
     * @param ui The UI component used to show messages.
     * @param storage The storage component used for persistence.
     * @throws SophonException If a command-specific error occurs.
     * @throws IOException If an I/O error occurs during execution.
     */
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws SophonException, IOException;
}