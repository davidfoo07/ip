package duke.parser;

import duke.commands.*;
import duke.tasks.Event;
import duke.tasks.Todo;
import duke.tasks.Deadline;

public class Parser {
    /**
     * 
     * @param userInput
     * @return
     */
    public static Command parse(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];

        switch (command) {
            case "list":
                return new ListCommand();
            
            case "todo":
                return new AddCommand(new Todo(parts[1]));
            
            case "deadline":
                String[] deadlineParts = parts[1].split("/by", 2);
                return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            
            case "event":
                String[] eventParts = parts[1].split("/from", 2);
                String[] timeParts = eventParts[1].split("/to", 2);
                return new AddCommand(new Event(eventParts[0].trim(), timeParts[0], timeParts[1]));
            
            case "mark":
                return new MarkCommand(Integer.parseInt(parts[1]) - 1);
            
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(parts[1]) - 1);
            
            case "delete":
                return new DeleteCommand(Integer.parseInt(parts[1]) - 1);
            
            case "find":
                return new FindCommand(parts[1]);

            case "bye":
                return new ExitCommand();

            default:
                return new InvalidCommand(command);
    }

        

    }
}
