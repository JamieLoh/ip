package sophon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that must be completed by a specific deadline.
 * <p>
 * A deadline task stores a single date and time by which the task
 * should be completed.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a deadline task with the specified description and deadline.
     *
     * @param description A short description of the task.
     * @param deadline    The deadline by which the task should be completed.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        assert deadline != null : "Deadline should not be null";
    }

    /**
     * Returns the deadline of this task.
     *
     * @return The deadline.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns the formatted deadline string of this task.
     *
     * @return A formatted string representing the deadline.
     */
    public String getDeadlineString() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss"));
    }

    /**
     * Returns a string representation of this deadline task.
     *
     * @return A formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + getDeadlineString() + ")";
    }
}
