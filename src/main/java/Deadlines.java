public class Deadlines extends Task {
    String deadline;

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (" + this.deadline + ")";
    }
}
