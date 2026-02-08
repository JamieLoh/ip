package sophon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs within a specific time range.
 * <p>
 * An event task has a start time and an end time, both represented
 * using {@link LocalDateTime}.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructs an event task with the specified description and time range.
     *
     * @param description A short description of the event.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        assert startTime != null : "StartTime should not be null";
        assert endTime != null : "EndTime should not be null";
    }

    /**
     * Returns the start time of this event.
     *
     * @return The start time.
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of this event.
     *
     * @return The end time.
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Returns the formatted start time string of this event.
     *
     * @return A formatted string representing the start time.
     */
    public String getStartTimeString() {
        return startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss"));
    }

    /**
     * Returns the formatted end time string of this event.
     *
     * @return A formatted string representing the end time.
     */
    public String getEndTimeString() {
        return endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss"));
    }

    /**
     * Returns a formatted string representing the time range of this event.
     *
     * @return A string describing the event time range.
     */
    public String getTimeRange() {
        return "from " + getStartTimeString() + " to " + getEndTimeString();

    }

    /**
     * Returns a string representation of this event task.
     *
     * @return A formatted string representing this event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + getTimeRange() + ")";
    }
}
