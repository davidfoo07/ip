package g.commands;

import java.util.ArrayList;

import g.storage.Storage;
import g.tasks.Task;
import g.tasks.TaskList;
import g.ui.Ui;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command to search for tasks with the specified keyword.
     *
     * @param tasks   The task list to search.
     * @param ui      The UI to display messages.
     * @param storage The storage to save data if needed.
     * @return The formatted message with search results.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTask(this.keyword);
        return ui.displayFindResults(matchingTasks, keyword);
    }
}
