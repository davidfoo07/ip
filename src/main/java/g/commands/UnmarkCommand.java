package g.commands;

import g.storage.Storage;
import g.tasks.Task;
import g.tasks.TaskList;
import g.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.unmarkTask(index);
            storage.save(tasks.getTasks());
            return ui.displaySuccessfulUnmarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Invalid task index for unmarking.");
        }
    }
}
