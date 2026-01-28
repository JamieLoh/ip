public class Task {
    private String description;
    private boolean isDone;

    public Task() {
        this.isDone = false;
    }

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String getDescription(){
        return description;
    }

    public String getStatus(){
        // mark the task done with "X"
        return isDone ? "X" : " ";
    }

    public boolean isDone(){
        return isDone;
    }

    public void markAsDone(){
        isDone = true;
    }

    public void markAsNotDone(){
        isDone = false;
    }

    @Override
    public String toString(){
        return "[" + getStatus() + "] " + getDescription();
    }
}
