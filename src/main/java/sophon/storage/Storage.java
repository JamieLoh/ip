package sophon.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import sophon.exception.SophonException;
import sophon.task.Deadline;
import sophon.task.Event;
import sophon.task.Task;
import sophon.task.TaskList;
import sophon.task.Todo;



/**
 * Handles loading and saving of tasks to persistent storage.
 * <p>
 * Tasks are stored in a text file at a specified file path.
 * Each task is saved in a single line using a pipe-separated format:
 * <pre>
 * TYPE | STATUS | DESCRIPTION | [TIME INFORMATION]
 * </pre>
 * where TYPE indicates the task type and STATUS indicates whether the
 * task is completed.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a {@code Storage} object that reads from and writes to
     * the specified file path.
     *
     * @param filePath The file path used for task persistence.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     * <p>
     * If the file does not exist, it will be created and an empty task list will be returned.
     *
     * @return A list of tasks loaded from storage.
     * @throws SophonException.DataFileCorruptedException If the file format is invalid(corrupted).
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws SophonException.DataFileCorruptedException, IOException {
        List<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
            return loadedTasks;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String taskLine;

        while ((taskLine = br.readLine()) != null) {
            Task task = parseTask(taskLine);
            loadedTasks.add(task);
        }
        br.close();
        return loadedTasks;
    }

    /**
     * Parses a single line from the data file into a {@code Task}.
     *
     * @param taskLine One line from the storage file contains task content.
     * @return A reconstructed Task object.
     * @throws SophonException.DataFileCorruptedException
     *         If the line format is invalid.
     */
    private Task parseTask(String taskLine) throws SophonException.DataFileCorruptedException {
        String[] parts = taskLine.split(" \\| ");

        if (parts.length < 3) {
            throw new SophonException.DataFileCorruptedException();
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task = createTaskByType(type, parts, description);

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Creates a specific Task object based on the given type identifier.
     *
     * @param type The task type identifier ("T", "D", or "E").
     * @param parts The split components of the task line.
     * @param description The task description.
     * @return The constructed Task object.
     * @throws SophonException.DataFileCorruptedException
     *         If the task type is unknown or required fields are missing.
     */
    private Task createTaskByType(String type, String[] parts, String description)
            throws SophonException.DataFileCorruptedException {
        switch (type) {
        case "T":
            return new Todo(description);
        case "D":
            if (parts.length < 4) {
                throw new SophonException.DataFileCorruptedException();
            }
            return new Deadline(description, LocalDateTime.parse(parts[3]));
        case "E":
            if (parts.length < 5) {
                throw new SophonException.DataFileCorruptedException();
            }
            return new Event(description, LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
        default:
            throw new SophonException.DataFileCorruptedException();
        }
    }

    /**
     * Saves the given task list to the storage file.
     * <p>
     * Existing file contents will be overwritten.
     *
     * @param taskList The task list to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String content = buildSaveContent(taskList);
        fw.write(content);
        fw.close();
    }

    /**
     * Builds the complete file content string for all tasks.
     *
     * @param taskList The task list to be saved.
     * @return A formatted string representing all tasks.
     */
    private String buildSaveContent(TaskList taskList) {
        StringBuilder fileContentBuilder = new StringBuilder();
        for (Task task : taskList.getTasksList()) {
            fileContentBuilder.append(buildTaskContent(task));
            fileContentBuilder.append("\n");
        }
        return fileContentBuilder.toString();
    }

    /**
     * Converts a Task object into its file storage representation.
     *
     * <p>The format follows:</p>
     * <pre>
     * TYPE | STATUS | DESCRIPTION | [TIME INFORMATION]
     * </pre>
     *
     * @param task The task to be converted.
     * @return A formatted string suitable for file storage.
     */
    private String buildTaskContent(Task task) {
        String type;
        if (task instanceof Todo) {
            type = "T";
        } else if (task instanceof Deadline) {
            type = "D";
        } else {
            type = "E";
        }

        int status = task.isDone() ? 1 : 0;

        StringBuilder taskContentBuilder = new StringBuilder();
        // concatenate basic saveInformation: type | status | description
        taskContentBuilder.append(type)
                .append(" | ")
                .append(status)
                .append(" | ")
                .append(task.getDescription());

        // concatenate Time Information for deadline and event task
        if (task instanceof Deadline) {
            taskContentBuilder.append(" | ")
                    .append(((Deadline) task).getDeadline());
        } else if (task instanceof Event) {
            taskContentBuilder.append(" | ")
                    .append(((Event) task).getStartTime())
                    .append(" | ")
                    .append(((Event) task).getEndTime());
        }
        return taskContentBuilder.toString();
    }
}
