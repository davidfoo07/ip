package duke.commands;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private final int index;

    /**
     * 
     * @param index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmarkTask(index);
        storage.save(tasks.getTasks());
    }
}
