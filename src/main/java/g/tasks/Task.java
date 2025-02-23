package g.tasks;
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * 
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * 
     * @param description
     * @param isDone
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * 
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * 
     * @param isDone
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * 
     * @return
     */
    public Boolean getStatus() {
        return this.isDone;
    }

    /**
     * 
     * @return
     */
    public String getDescription() {
        return this.description;
    }
    
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * 
     * @return
     */
    public abstract String toFileString();

}
