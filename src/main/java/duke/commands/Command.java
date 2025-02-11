package duke.commands;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public abstract class Command {
    /**
     * 
     * @param tasks
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * 
     * @return
     */
    public boolean isExit() {
        return false;
    }
}
