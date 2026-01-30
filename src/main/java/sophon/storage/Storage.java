package sophon.storage;

import sophon.exception.SophonException;
import sophon.task.Deadlines;
import sophon.task.Event;
import sophon.task.Task;
import sophon.task.TaskList;
import sophon.task.Todo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
     * If the file does not exist, it will be created and an empty task
     * list will be returned.
     *
     * @return A list of tasks loaded from storage.
     * @throws SophonException.DataFileCorruptedException If the file format is invalid(corrupted).
     * @throws IOException If an I/O error occurs while reading the file.
     */
    public List<Task> load() throws SophonException.DataFileCorruptedException, IOException {
        List<Task> loadedTasks = new ArrayList<>();

        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
            return loadedTasks;
        }

        BufferedReader br = new BufferedReader(new FileReader(file));
        String content;

        while ((content = br.readLine()) != null) {
            String[] parts = content.split(" \\| ");

            if (parts.length < 3) {
                throw new SophonException.DataFileCorruptedException();
            }

            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];
            Task task;

            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                task = new Deadlines(description, LocalDateTime.parse(parts[3]));
                break;
            case "E":
                task = new Event(description, LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
                break;
            default:
                throw new SophonException.DataFileCorruptedException();
            }

            if (isDone) {
                task.markAsDone();
            }
            loadedTasks.add(task);
        }
        br.close();
        return loadedTasks;
    }

    /**
     * Saves the given task list to the storage file.
     * <p>
     * Existing file contents will be overwritten.
     *
     * @param tasksList The task list to be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void save(TaskList tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder saveInformation = new StringBuilder();

        for (Task task : tasksList.getTasksList()) {
            // get task type
            String type = "";
            if (task instanceof Todo) {
                type = "T";
            } else if (task instanceof Deadlines) {
                type = "D";
            } else {
                type = "E";
            }

            // get task status (isDone?)
            int status = task.isDone() ? 1 : 0;

            // concatenate basic saveInformation: type | status | description
            saveInformation.append(type)
                    .append(" | ")
                    .append(status)
                    .append(" | ")
                    .append(task.getDescription());

            // concatenate additional information like deadline or event time range
            if (task instanceof Deadlines) {
                saveInformation.append(" | ")
                        .append(((Deadlines) task).getDeadline());
            } else if (task instanceof Event) {
                saveInformation.append(" | ")
                        .append(((Event) task).getStartTime())
                        .append(" | ")
                        .append(((Event) task).getEndTime());
            }
            saveInformation.append("\n");
        }
        fw.write(saveInformation.toString());
        fw.close();
    }
}
