package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.TaskList;
import sophon.ui.UI;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasksList, UI ui, Storage storage) throws SophonException.EmptyListException {
        if (tasksList.isEmpty()) {
            throw new SophonException.EmptyListException();
        }
        ui.showTaskListMessage(tasksList.getTasksList());
    }
}
