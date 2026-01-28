public class SophonException extends Exception{
    public SophonException(String message){
        super(message);
    }

    public static class EmptyListException extends SophonException {
        public EmptyListException() {
            super("Oops, your task list is currently empty. Please add some tasks into it first.");
        }
    }

    public static class UnknownCommandException extends SophonException {
        public UnknownCommandException() {
            super("Sorry, I don't know what you mean. Please give me a valid command.");
        }
    }

    public static class WrongFormatException extends SophonException {
        public WrongFormatException(String format) {
            super("Oops, there are some problems with your current command format. Refer to this and try again: \n" + format);
        }
    }

    public static class TaskNotFoundException extends SophonException {
        public TaskNotFoundException() {
            super("There is no such task with your given task number. Please give me a valid task number.");
        }
    }

    public static class DataFileCorruptedException extends SophonException {
        public DataFileCorruptedException() {
            super("Data file corrupted.");
        }
    }
}
