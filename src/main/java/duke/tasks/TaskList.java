package duke.tasks;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Added task: " + task);
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("Deleted task: " + removedTask);
        } else {
            System.out.println("Task number " + (index + 1) + " does not exist.");
        }
    }

    public void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setStatus(true);
            System.out.println("Marked as done: Task " + tasks.get(index));
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setStatus(false);
            System.out.println("Unmarked: Task " + tasks.get(index));
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("The list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
