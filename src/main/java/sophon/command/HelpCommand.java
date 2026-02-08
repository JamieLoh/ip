package sophon.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import sophon.exception.SophonException;
import sophon.storage.Storage;
import sophon.task.TaskList;

/**
 * Represents a command that shows all available commands and their usage.
 */
public class HelpCommand extends Command {
    private final String topic;

    /**
     * Constructs a HelpCommand.
     *
     * @param topic The specific command to get help for.
     *              If null, show general help.
     */
    public HelpCommand(String topic) {
        this.topic = topic;
    }

    /**
     * Displays a list of available commands and their formats.
     *
     * @param taskList The task list (not used here).
     * @param storage The storage component (not used here).
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws SophonException, IOException {
        if (topic == null) {
            return getGeneralHelp();
        }
        return getSpecificHelp(topic);
    }

    /**
     * Returns general help listing all available commands.
     */
    private String getGeneralHelp() {
        String message = """
                Here are the available commands:
                (First line: format)
                (Second line: usage)

                1. todo [description]
                   Adds a todo task.

                2. deadline [description] /by yyyy-MM-dd HH:mm:ss
                   Adds a deadline task.

                3. event [description] /from yyyy-MM-dd HH:mm:ss /to yyyy-MM-dd HH:mm:ss
                   Adds an event task.

                4. list
                   Shows all tasks.

                5. mark [task number]
                   Marks a task as done.

                6. unmark [task number]
                   Marks a task as not done.

                7. delete [task number]
                   Deletes a task.

                8. find [keyword]
                   Finds tasks containing the keyword.

                9. help [command]
                   Get detailed help for a specific command.

                10. bye
                    Exits the application.
                """;
        return message;
    }

    /**
     * Returns detailed help for a specific command.
     */
    private String getSpecificHelp(String topic) {
        Map<String, String> helpMap = new HashMap<>();

        helpMap.put("todo", """
                todo [description]
                Adds a simple todo task.

                Example:
                todo read book
                """);

        helpMap.put("deadline", """
                deadline [description] /by yyyy-MM-dd HH:mm:ss
                Adds a deadline task.

                Example:
                deadline submit assignment /by 2026-03-01 23:59:00
                """);

        helpMap.put("event", """
                event [description] /from yyyy-MM-dd HH:mm:ss /to yyyy-MM-dd HH:mm:ss
                Adds an event task with start and end time.

                Example:
                event meeting /from 2026-03-01 10:00:00 /to 2026-03-01 12:00:00
                """);

        helpMap.put("list", """
                list
                Displays all tasks.
                """);

        helpMap.put("mark", """
                mark [task number]
                Marks a task as done.

                Example:
                mark 2
                """);

        helpMap.put("unmark", """
                unmark [task number]
                Marks a task as not done.

                Example:
                unmark 2
                """);

        helpMap.put("delete", """
                delete [task number]
                Deletes a task.

                Example:
                delete 3
                """);

        helpMap.put("find", """
                find [keyword]
                Finds tasks containing the keyword.

                Example:
                find book
                """);

        helpMap.put("bye", """
                bye
                Exits the application.
                """);

        return helpMap.getOrDefault(topic,
                "Sorry, there is no help available for '" + topic + "'.\n"
                        + "You can type \"help\" for a general help.");
    }
}
