package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markTask(index);
            storage.save(tasks.getTasks());
            return ui.displaySuccessfulMarkTask(task);
        } catch (IndexOutOfBoundsException e) {
            return ui.showError("Invalid task index for marking as done.");
        }
    }
}
