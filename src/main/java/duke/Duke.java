package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Default constructor with a predefined file path.
     */
    public Duke() {
        this(System.getProperty("user.home") + "/Documents/CS2103T/tasks.txt");
    }

    /**
     * Constructor with a specified file path.
     *
     * @param filePath Path to the storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generates a response based on user input.
     *
     * @param input User input string.
     * @return Response string from Duke.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String response = c.execute(tasks, ui, storage);
            
            // Automatically save after every command execution
            storage.save(tasks.getTasks());
            
            // Close the application if the "bye" command is detected
            if (c.isExit()) {
                Platform.exit();
            }
            
            return response;
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
}

}
