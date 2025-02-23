package g.gui;

import g.G;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private G duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Dat.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        assert scrollPane != null : "scrollPane should not be null!";
        assert dialogContainer != null : "dialogContainer should not be null!";
        assert userInput != null : "userInput should not be null!";
        assert sendButton != null : "sendButton should not be null!";
        
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }


    /** Injects the Duke instance */
    public void setDuke(G d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        userInput.clear();

        // Check if the response is the "bye" message and exit the application
        if (response.equals("Bye. Hope to see you again soon!")) {
            Platform.exit();
        }
    }

}
