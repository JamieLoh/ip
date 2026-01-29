package sophon.command;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.TaskList;
import sophon.ui.UI;
import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws SophonException, IOException;
}