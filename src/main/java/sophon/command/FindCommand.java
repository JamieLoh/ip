package sophon.command;

import java.util.List;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.Task;
import sophon.task.TaskList;

/**
 * Represents a command that finds tasks containing a given keyword.
 * The search is performed on the task descriptions.
 */
public class FindCommand extends Command {
    private static final String FIND_COMMAND_PATTERN = "^find .+$";
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} by parsing user input.
     *
     * @param command The full user input string.
     * @throws SophonException.WrongFormatException If the command format is invalid.
     */
    public FindCommand(String command) throws SophonException.WrongFormatException {
        if (!command.matches(FIND_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("find [keyword of task]");
        }
        keyword = command.substring(5).trim();
    }

    /**
     * Executes the find command by searching the task list
     * and displaying all matching tasks to the user.
     *
     * @param taskList The task list to be searched.
     * @param storage The storage component (not used in this command).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        List<Task> foundTasks = taskList.findTasks(keyword);

        StringBuilder sb = new StringBuilder("Here are the matching tasks:\n");
        int counter = 1;
        for (Task task : foundTasks) {
            sb.append(counter).append(". ").append(task).append("\n");
            counter++;
        }
        return sb.toString();
    }
}
