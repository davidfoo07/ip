package duke.commands;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int index;

    /**
     * 
     * @param index
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTask(index);
        storage.save(tasks.getTasks());
    }
}
