package g.commands;

import g.storage.Storage;
import g.tasks.Task;
import g.tasks.TaskList;
import g.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command to remove a task from the task list.
     *
     * @param tasks   The task list to delete from.
     * @param ui      The UI to display messages.
     * @param storage The storage to update data after deletion.
     * @return The formatted message indicating the delete operation result.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.deleteTask(index);
            storage.save(tasks.getTasks());
            return ui.displayDeleteTask(task, tasks.size());
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Invalid task index for deletion.");
        }
    }
}
