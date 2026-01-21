class EmptyListException extends Exception {
    public EmptyListException() {
        super("Oops, your task list is currently empty. Please add some tasks into it first.");
    }
}

class UnkownCommandException extends Exception {
    public UnkownCommandException() {
        super("Sorry, I don't know what you mean. Please give me a valid command.");
    }
}

class WrongFormatException extends Exception {
    public WrongFormatException(String format) {
        super("Oops, there are some problems with your current command format. Refer to this and try again: \n" + format);
    }
}

class TaskNotFoundException extends Exception {
    public TaskNotFoundException() {
        super("There is no such task with your given task number.");
    }
}