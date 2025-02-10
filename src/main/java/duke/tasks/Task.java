package duke.tasks;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public Boolean getStatus() {
        return this.isDone;
    }

    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public abstract String toFileString();

}
