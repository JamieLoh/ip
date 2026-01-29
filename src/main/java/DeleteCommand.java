public class DeleteCommand extends Command {
    private static final String DELETE_TASK_COMMAND_PATTERN = "^delete \\d+$";
    private int taskIndex;

    public DeleteCommand(String command) throws SophonException.WrongFormatException {
        if (!command.matches(DELETE_TASK_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("delete [task number]");
        }
        taskIndex = Integer.parseInt(command.substring(7)) - 1;
    }

    @Override
    public void execute (TaskList taskList, UI ui, Storage storage) throws SophonException.TaskNotFoundException {
        Task removedTask = taskList.remove(taskIndex);
        ui.showDeleteMessage(removedTask, taskList.size());
    }
}
