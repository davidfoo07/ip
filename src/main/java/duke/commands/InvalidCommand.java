package duke.commands;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command {
    private String error;

    /**
     * 
     * @param error
     */
    public InvalidCommand(String error) {
        this.error = error;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError("Invalid command: " + this.error);
    }
}
