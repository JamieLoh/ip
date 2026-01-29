import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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
                task = new Event(description, LocalDateTime.parse(parts[3]),  LocalDateTime.parse(parts[4]));
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

    public void save(TaskList tasksList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder saveInformation  = new StringBuilder();

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
