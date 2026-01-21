public class SophonException extends Exception{
    public SophonException(String message){
        super(message);
    }

    public static class EmptyListException extends SophonException {
        public EmptyListException() {
            super("Oops, your task list is currently empty. Please add some tasks into it first.");
        }
    }

    public static class UnkownCommandException extends SophonException {
        public UnkownCommandException() {
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
            super("There is no such task with your given task number.");
        }
    }
}
