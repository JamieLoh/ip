package sophon.parser;

import sophon.command.Command;
import sophon.command.DeadlineCommand;
import sophon.command.DeleteCommand;
import sophon.command.EventCommand;
import sophon.command.FindCommand;
import sophon.command.ListCommand;
import sophon.command.MarkCommand;
import sophon.command.TodoCommand;
import sophon.command.UnmarkCommand;
import sophon.exception.SophonException;

/**
 * Parses user input strings and converts them into executable {@link Command}
 * objects.
 * <p>
 * This class is responsible for determining the type of command entered by the
 * user and instantiating the corresponding {@code Command} subclass.
 */
public class Parser {
    /**
     * Parses the given user input and returns the corresponding command.
     *
     * @param userInput The raw input string entered by the user.
     * @return A {@link Command} representing the user instruction.
     * @throws SophonException If the command is unknown or invalid.
     */
    public Command parseCommand(String userInput) throws SophonException {
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
        } else if (userInput.startsWith("find")) {
            return new FindCommand(userInput);
        } else {
            throw new SophonException.UnknownCommandException();
        }
    }
}
