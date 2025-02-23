package g.commands;

import g.storage.Storage;
import g.tasks.Task;
import g.tasks.TaskList;
import g.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
