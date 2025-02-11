package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * 
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());
    }

    /**
     * 
     * @param description
     * @param from
     * @param to
     * @param isDone
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = LocalDate.parse(from.trim());
        this.to = LocalDate.parse(to.trim());
    }

   
    @Override
    public String toString() {
        return "[E][" + (isDone ? "X" : " ") + "] " + description + 
               " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + 
               " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
