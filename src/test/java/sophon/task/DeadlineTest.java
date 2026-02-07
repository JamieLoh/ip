package sophon.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;


public class DeadlineTest {
    @Test
    public void getDeadline_validDeadline_returnsCorrectDeadline() {
        LocalDateTime deadline = LocalDateTime.of(2026, 2, 28, 12, 30, 15);
        Deadline task = new Deadline("submit project", deadline);

        assertEquals(deadline, task.getDeadline());
    }

    @Test
    public void toString_validDeadlineTask_correctStringReturned() {
        LocalDateTime deadline = LocalDateTime.of(2026, 2, 28, 12, 30, 15);
        Deadline task = new Deadline("submit project", deadline);

        String expected = "[D][ ] submit project (by Feb 28 2026, 12:30:15)";
        assertEquals(expected, task.toString());
    }
}
