package sophon.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getStartTimeString(){
        return startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss"));
    }

    public String getEndTimeString(){
        return endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss"));
    }

    public String getTimeRange(){
        return "from " + getStartTimeString() + " to " + getEndTimeString();

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + getTimeRange() + ")";
    }
}
