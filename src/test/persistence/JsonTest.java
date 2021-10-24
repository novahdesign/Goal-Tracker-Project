package persistence;

import model.Goal;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkGoal(String name, int progress, Goal goal) {
        assertEquals(name, goal.getName());
        assertEquals(progress, goal.getProgress());
    }
}
