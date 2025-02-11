package duke.commands;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTaskList = tasks.findTask(this.keyword);
        if (matchingTaskList.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTaskList.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTaskList.get(i));
            }
        }
    }

}
