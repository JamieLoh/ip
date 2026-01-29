public class Parser {
    public Command parse(String userInput) throws SophonException {
        if (userInput.equals("list")) {
            return new ListCommand();
        } else if (userInput.startsWith("todo")) {
            return new TodoCommand(userInput);
        } else if (userInput.startsWith("deadline")) {
            return new DeadlineCommand(userInput);
        } else if (userInput.startsWith("event")) {
            return new EventCommand(userInput);
        } else if (userInput.startsWith("mark")) {
            return new MarkCommand(userInput);
        } else if (userInput.startsWith("unmark")) {
            return new UnmarkCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        } else {
            throw new SophonException.UnknownCommandException();
        }
    }
}
