
package sophon.command;

import java.io.IOException;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.TaskList;


/**
 * Represents an executable user command in the Sophon application.
 * <p>
 * Each concrete subclass defines a specific user action and implements
 * the {@code execute} method to perform that action.
 */
public abstract class Command {
    /**
     * Executes the command with access to the task list and storage.
     *
     * @param taskList The task list to operate on.
     * @param storage The storage component used for persistence.
     * @return A formatted response message.
     * @throws SophonException If a command-specific error occurs.
     * @throws IOException If an I/O error occurs during execution.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws SophonException, IOException;
}