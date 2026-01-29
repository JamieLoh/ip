import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private static final String DEADLINE_COMMAND_PATTERN = "^deadline .+ /by .+$";
    private String description;
    private LocalDateTime deadline;

    public DeadlineCommand(String command) throws SophonException.WrongFormatException {
        // check format
        if (!command.matches(DEADLINE_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("deadline [task] /by [deadline] \n" + "Notice: Time should in yyyy-MM--dd HH:mm:ss format");
        }

        // get task information
        int index = command.indexOf('/');
        description = command.substring(9, index).trim();
        String deadlineString = command.substring(index + 4).trim();

        // convert deadline String to LocalDateTime format
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            deadline = LocalDateTime.parse(deadlineString, df);
        } catch (DateTimeParseException e) {
            throw new SophonException.WrongFormatException("deadline [task] /by [deadline] \n" + "Notice: Time should in yyyy-MM--dd HH:mm:ss format");
        }
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        // instantiate and add the task to task list
        Task task = new Deadlines(description, deadline);
        taskList.add(task);
        ui.showAddTaskMessage(task, taskList.size());
    }
}
