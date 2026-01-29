public class UnmarkCommand extends Command {
    private static final String UNMARK_TASK_COMMAND_PATTERN = "^unmark \\d+$";
    private int taskIndex;

    public UnmarkCommand(String command) throws SophonException.WrongFormatException {
        // check format
        if (!command.matches(UNMARK_TASK_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("mark [task number]");
        }

        taskIndex = Integer.parseInt(command.substring(7)) - 1;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws SophonException.TaskNotFoundException {
        Task task = taskList.get(taskIndex);
        task.markAsNotDone();
        ui.showUnMarkMessage(task);
    }
}
