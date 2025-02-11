package duke.commands;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command{
    private final Task task;

    /**
     * 
     * @param task
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * 
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
    }
}
