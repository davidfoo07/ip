package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert task != null : "Task should not be null before adding!";
        String message = tasks.addTask(task, ui);
        storage.save(tasks.getTasks());
        return message;
    }

}
