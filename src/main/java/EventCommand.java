import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private static final String EVENT_COMMAND_PATTERN = "^event .+ /from .+ /to .+$";
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventCommand(String command) throws SophonException.WrongFormatException {
        // check format
        if (!command.matches(EVENT_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("event [task] /from [start time] /to [end time] \n" + "Notice: Time should in yyyy-MM--dd HH:mm:ss format");
        }

        // get task information
        int index = command.indexOf('/');
        description = command.substring(6, index).trim();
        String timeRange = command.substring(index + 1);
        int timeIndex = timeRange.indexOf('/');
        String startTimeString = timeRange.substring(5, timeIndex).trim();
        String endTimeString = timeRange.substring(timeIndex + 4).trim();

        // convert startTime and endTime to LocalDateTime format
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            startTime = LocalDateTime.parse(startTimeString, df);
            endTime = LocalDateTime.parse(endTimeString, df);
        } catch (DateTimeParseException e) {
            throw new SophonException.WrongFormatException("event [task] /from [start time] /to [end time] \n" + "Notice: Time should in yyyy-MM--dd HH:mm:ss format");
        }

    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        // instantiate task and add the task to task list
        Task task = new Event(description, startTime, endTime);
        taskList.add(task);
        ui.showAddTaskMessage(task, taskList.size());
    }
}
