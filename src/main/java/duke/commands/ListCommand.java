package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks in the task list.
     *
     * @param tasks   The task list to display.
     * @param ui      The UI to format the message.
     * @param storage The storage to save data if needed.
     * @return The formatted message displaying all tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
