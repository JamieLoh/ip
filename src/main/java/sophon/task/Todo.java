package sophon.task;

/**
 * Represents a todo task with no associated date/time.
 * <p>
 * A todo task only contains a description and a completion status.
 */
public class Todo extends Task {
    /**
     * Constructs a todo task with the specified description.
     *
     * @param description A short description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this todo task.
     *
     * @return A formatted string representing this todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
