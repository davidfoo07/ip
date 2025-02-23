package g.tasks;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Default constructor for an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for initializing with an existing list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     * @return The added task.
     */
    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to delete.
     * @return The deleted task if successful.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in deleteTask!";
        return tasks.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark.
     * @return The marked task.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Task markTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in markTask!";
        Task task = tasks.get(index);
        task.setStatus(true);
        return task;
    }

    /**
     * Unmarks a task as not done.
     *
     * @param index The index of the task to unmark.
     * @return The unmarked task.
     * @throws IndexOutOfBoundsException if the index is invalid.
     */
    public Task unmarkTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in unmarkTask!";
        Task task = tasks.get(index);
        task.setStatus(false);
        return task;
    }

    /**
     * Returns a formatted string of all tasks.
     *
     * @return A string listing all tasks.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "The list is empty.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString().trim();
        }
    }

    /**
     * Retrieves all tasks in the list.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds tasks containing a specific keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }
}
