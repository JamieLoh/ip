package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.Task;
import sophon.task.TaskList;
import sophon.task.Todo;
import sophon.ui.UI;


public class TodoCommand extends Command {
    private static final String TODOS_COMMAND_PATTERN = "^todo .+$";
    private String description;

    public TodoCommand(String command) throws SophonException.WrongFormatException {
        // check format
        if (!command.matches(TODOS_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("todo [task]");
        }

        // get task information
        description = command.substring(5).trim();
    }

    @Override
    public void execute (TaskList taskList, UI ui, Storage storage) {
        // instantiate and add the task to task list
        Task task = new Todo(description);
        taskList.add(task);
        ui.showAddTaskMessage(task, taskList.size());
    }
}
