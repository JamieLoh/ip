public class MarkCommand extends Command {
    private static final String MARK_TASK_COMMAND_PATTERN = "^mark \\d+$";
    private int taskIndex;

    public MarkCommand(String command) throws SophonException.WrongFormatException {
        // check format
        if (!command.matches(MARK_TASK_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("mark [task number]");
        }

        taskIndex = Integer.parseInt(command.substring(5)) - 1;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws SophonException.TaskNotFoundException {
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        ui.showMarkMessage(task);
    }
}
