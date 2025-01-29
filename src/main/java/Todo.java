public class Todo extends Task {

    public Todo(String description) {
        super(description);
    };

    public Todo(String description, Boolean isDone) {
        super(description);
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + description;
    }
};
