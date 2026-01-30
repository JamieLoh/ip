package sophon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class EventTest {
    @Test
    public void getStartTime_validEvent_returnsCorrectStartTime() {
        LocalDateTime startTime = LocalDateTime.of(2026, 2, 28, 12, 30, 15);
        LocalDateTime endTime = LocalDateTime.of(2026, 2, 28, 14, 30, 15);
        Event event = new Event("group meeting", startTime, endTime);

        assertEquals(startTime, event.getStartTime());
    }

    @Test
    public void getEndTime_validEvent_returnsCorrectEndTime() {
        LocalDateTime startTime = LocalDateTime.of(2026, 2, 28, 12, 30, 15);
        LocalDateTime endTime = LocalDateTime.of(2026, 2, 28, 14, 30, 15);
        Event event = new Event("group meeting", startTime, endTime);

        assertEquals(endTime, event.getEndTime());
    }

    @Test
    public void toString_validEvent_correctStringReturned() {
        LocalDateTime startTime = LocalDateTime.of(2026, 2, 28, 12, 30, 15);
        LocalDateTime endTime = LocalDateTime.of(2026, 2, 28, 14, 30, 15);
        Event event = new Event("group meeting", startTime, endTime);

        String expected = "[E][ ] group meeting (from Feb 28 2026, 12:30:15 to Feb 28 2026, 14:30:15)";
        assertEquals(expected, event.toString());
    }
}
