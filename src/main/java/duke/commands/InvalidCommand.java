package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command {
    private final String error;

    public InvalidCommand(String error) {
        this.error = error;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showError("Invalid command: " + this.error);
    }
}
