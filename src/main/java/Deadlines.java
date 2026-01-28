import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    private LocalDateTime deadline;

    public Deadlines(String description, LocalDateTime deadline) {
        super(description);

        this.deadline = deadline;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getDeadlineString() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss"));
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by " + getDeadlineString() + ")";
    }
}
