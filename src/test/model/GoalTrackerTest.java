package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GoalTrackerTest {

    private GoalTracker testList;

    @BeforeEach
    void runBefore() {
        testList = new GoalTracker();
    }

    @Test
    void testAddGoalTracker(){
        Goal g1 = new Goal("study", 0);
        testList.addGoal(g1);
        assertEquals(1, testList.goalList.size());

    }

    @Test
    void testRemoveGoalTracker() {
        Goal g1 = new Goal("exercise", 3);
        testList.removeGoal(g1);
        assertEquals(0, testList.goalList.size());
    }


}
