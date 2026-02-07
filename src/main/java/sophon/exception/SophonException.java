package sophon.exception;

/**
 * Represents the base exception type for all Sophon-specific exceptions.
 * <p>
 * All custom exceptions in the Sophon application should extend this class.
 * It provides a unified way to handle application-level errors.
 */
public class SophonException extends Exception {
    /**
     * Constructs a {@code SophonException} with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public SophonException(String message) {
        super(message);
    }

    /**
     * Thrown when an operation is attempted on an empty task list.
     */
    public static class EmptyListException extends SophonException {
        /**
         * Constructs an {@code EmptyListException} with a predefined error message.
         */
        public EmptyListException() {
            super("Oops, your task list is currently empty. Please add some tasks into it first.");
        }
    }

    /**
     * Thrown when the user enters an unrecognized command.
     */
    public static class UnknownCommandException extends SophonException {
        /**
         * Constructs an {@code UnknownCommandException} with a predefined error message.
         */
        public UnknownCommandException() {
            super("Sorry, I don't know what you mean. Please give me a valid command.");
        }
    }

    /**
     * Thrown when a command is syntactically valid but does not follow the
     * required format.
     */
    public static class WrongFormatException extends SophonException {
        /**
         * Constructs a {@code WrongFormatException} with a message indicating
         * the expected command format.
         *
         * @param format The correct format of the command.
         */
        public WrongFormatException(String format) {
            super("Oops, there are some problems with your current command format."
                    + " Refer to this and try again: \n" + format);
        }
    }

    /**
     * Thrown when a task index provided by the user does not exist.
     */
    public static class TaskNotFoundException extends SophonException {
        /**
         * Constructs a {@code TaskNotFoundException} with a predefined error message.
         */
        public TaskNotFoundException() {
            super("There is no such task with your given task number. Please give me a valid task number.");
        }
    }

    /**
     * Thrown when the stored data file cannot be parsed due to corruption
     * or unexpected format.
     */
    public static class DataFileCorruptedException extends SophonException {
        /**
         * Constructs a {@code DataFileCorruptedException} with a predefined error message.
         */
        public DataFileCorruptedException() {
            super("Data file corrupted.");
        }
    }
}
