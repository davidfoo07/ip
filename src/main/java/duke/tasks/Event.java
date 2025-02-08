<<<<<<< Updated upstream:src/main/java/Event.java
public class Event extends Task{
    protected String from;
    protected String to;
=======
package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
>>>>>>> Stashed changes:src/main/java/duke/tasks/Event.java

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to, Boolean isDone) {
        super(description);
        this.isDone = isDone;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toFileString() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + description + " | " + this.from + " | " + this.to;
    }
    
}
