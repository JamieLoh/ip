package sophon.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.Event;
import sophon.task.Task;
import sophon.task.TaskList;

/**
 * Represents a command that adds an event task to the task list.
 * An event task contains a description, a start time, and an end time.
 */
public class EventCommand extends Command {
    private static final String EVENT_COMMAND_PATTERN = "^event .+ /from .+ /to .+$";
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs an {@code EventCommand} by parsing user input.
     *
     * @param command The full user input string.
     * @throws SophonException.WrongFormatException If the command format is invalid
     *                                             or if the date-time format is incorrect.
     */
    public EventCommand(String command) throws SophonException.WrongFormatException,
            SophonException.WrongDateSequenceException {
        // check format
        if (!command.matches(EVENT_COMMAND_PATTERN)) {
            throw new SophonException.WrongFormatException("event [task] /from [start time] /to [end time] \n"
                    + "Notice: Time should be in yyyy-MM--dd HH:mm:ss format");
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
            throw new SophonException.WrongFormatException("event [task] /from [start time] /to [end time] \n"
                    + "Notice: Time should be in yyyy-MM--dd HH:mm:ss format");
        }

        // check if end time is before start time
        if (endTime.isBefore(startTime)) {
            throw new SophonException.WrongDateSequenceException();
        }
    }

    /**
     * Adds the event task to the task list and returns a confirmation message.
     *
     * @param taskList The task list to add the task to.
     * @param storage The storage component (not used here).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        // instantiate task and add the task to task list
        Task task = new Event(description, startTime, endTime);
        taskList.add(task);

        String message = "Got it! I have added this task:\n"
                + "    " + task + "\n"
                + "Now you have " + taskList.size() + " tasks in your list. \n";
        return message;
    }
}
