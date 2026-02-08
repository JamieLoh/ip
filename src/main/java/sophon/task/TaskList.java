package sophon.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sophon.exception.SophonException;

/**
 * Represents a list of tasks in the Sophon application.
 * <p>
 * This class provides operations to add, retrieve(list), and remove(delete) tasks
 * while ensuring index validity.
 */
public class TaskList {
    private final List<Task> tasksList;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasksList = new ArrayList<>();
    }

    /**
     * Constructs a task list using an existing list of tasks.
     *
     * @param tasksList A list of tasks to initialise this task list with.
     */
    public TaskList(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasksList.add(task);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws SophonException.TaskNotFoundException If the index is invalid, for example, out of boundary.
     */
    public Task get(int index) throws SophonException.TaskNotFoundException {
        if (index < 0 || index >= tasksList.size()) {
            throw new SophonException.TaskNotFoundException();
        }
        return tasksList.get(index);
    }

    /**
     * Removes and returns the task at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     * @throws SophonException.TaskNotFoundException If the index is invalid.
     */
    public Task remove(int index) throws SophonException.TaskNotFoundException {
        if (index < 0 || index >= tasksList.size()) {
            throw new SophonException.TaskNotFoundException();
        }
        Task task = tasksList.get(index);
        tasksList.remove(index);
        return task;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasksList.size();
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return {@code true} if the list is empty; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return tasksList.isEmpty();
    }

    /**
     * Returns the list of tasks.
     *
     * @return A list containing all tasks.
     */
    public List<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Returns the list of the matching tasks.
     *
     * @param keyword Keyword used to search task descriptions.
     * @return A list containing all tasks.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> foundTasks = tasksList.stream()
                .filter(t -> t.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return foundTasks;
    }
}
