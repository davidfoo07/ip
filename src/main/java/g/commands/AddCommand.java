package g.commands;

import g.storage.Storage;
import g.tasks.Task;
import g.tasks.TaskList;
import g.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert task != null : "Task should not be null before adding!";
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return ui.displayAddTask(task, tasks.size());
    }

}
