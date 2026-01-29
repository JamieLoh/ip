import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasksList;

    // initialise empty list
    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    // load list from Storage
    public TaskList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    public void add(Task task) {
        tasksList.add(task);
    }

    public Task get(int index) throws SophonException.TaskNotFoundException {
        if (index < 0 || index >= tasksList.size()) {
            throw new SophonException.TaskNotFoundException();
        }
        return tasksList.get(index);
    }

    public Task remove(int index) throws SophonException.TaskNotFoundException {
        if (index < 0 || index >= tasksList.size()) {
            throw new SophonException.TaskNotFoundException();
        }
        Task task = tasksList.get(index);
        tasksList.remove(index);
        return task;
    }

    public int size() {
        return tasksList.size();
    }

    public boolean isEmpty() {
        return tasksList.isEmpty();
    }

    public List<Task> getTasksList() {
        return tasksList;
    }
}
