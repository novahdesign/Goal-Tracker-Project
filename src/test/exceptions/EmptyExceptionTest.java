package exceptions;

import model.Goal;
import model.GoalTracker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmptyExceptionTest {
    GoalTracker testEmptyList = null;
    Goal goal = null;


    @BeforeEach
    void runBefore() {
        testEmptyList = new GoalTracker();
        goal = new Goal("goal", 0);
    }

    @Test
    void testEmptyException() {
        try {
            testEmptyList.removeGoal(goal);
        } catch (Exception exception) {
            assertTrue( exception instanceof EmptyException );
            System.out.println("Goal list is empty, add a goal!");
        }
    }
}
