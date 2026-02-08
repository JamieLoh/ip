package sophon.task;

/**
 * Represents a task in Sophon
 * <p>
 * A task consists of a textual description and a completion status.
 * This class provides common behavior shared by all task types and
 * serves as the superclass for specific task implementations such as
 * {@link Todo}, {@link Deadline}, and {@link Event}.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a task with no description and marks it as not completed.
     */
    public Task() {
        this.isDone = false;
    }

    /**
     * Constructs a task with the specified description and marks it
     * as not completed.
     *
     * @param description A short description of the task.
     */
    public Task(String description) {
        assert description != null : "Description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the completion status marker of this task.
     * <p>
     * The status is represented as {@code "X"} if the task is completed,
     * or a blank space {@code " "} otherwise.
     *
     * @return A string representing the completion status.
     */
    public String getStatus() {
        // mark the task done with "X"
        return isDone ? "X" : " ";
    }

    /**
     * Returns whether this task has been marked as completed.
     *
     * @return {@code true} if the task is completed; {@code false} otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of this task.
     * <p>
     * The returned string includes the completion status and the task
     * description.
     *
     * @return A formatted string representing this task.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + getDescription();
    }
}
