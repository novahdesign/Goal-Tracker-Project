package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GoalTrackerApp;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// Test Class for GoalTracker
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

    @Test
    void testGetGoalList() {
        Goal g1 = new Goal("sleep", 1);
        Goal g2 = new Goal( "study", 2);
        testList.addGoal(g1);
        testList.addGoal(g2);
        assertEquals(2, testList.getGoalList().size());
        assertTrue(testList.getGoalList().contains(g1));
        assertTrue(testList.getGoalList().contains(g2));

    }

    @Test
    void testGetLength() {
        assertEquals(0,testList.getLength());
        Goal g1 = new Goal("sleep", 1);
        testList.addGoal(g1);
        assertEquals(1,testList.getLength());


    }


}
