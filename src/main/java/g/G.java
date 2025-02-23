package g;

import g.commands.Command;
import g.parser.Parser;
import g.storage.Storage;
import g.tasks.TaskList;
import g.ui.Ui;
import javafx.application.Platform;

public class G {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Default constructor with a predefined file path.
     */
    public G() {
        this(System.getProperty("user.home") + "/Documents/CS2103T/tasks.txt");
    }

    /**
     * Constructor with a specified file path.
     *
     * @param filePath Path to the storage file.
     */
    public G(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }

        // Assertions to ensure all essential components are initialized
        assert ui != null : "UI should not be null!";
        assert storage != null : "Storage should not be null!";
        assert tasks != null : "TaskList should not be null!";
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
            
            assert response != null : "Response should not be null!";
            return response;
        } catch (Exception e) {
            return ui.showError(e.getMessage());
        }
}

}
