package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GoalTrackerApp;

import java.util.ArrayList;
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
    void testAddGoalTracker() {
        Goal g1 = new Goal("study", 0, 100);
        testList.addGoal(g1);
        assertEquals(1, testList.getGoalList().size());

    }

    @Test
    void testRemoveGoalTracker() {
        Goal g1 = new Goal("exercise", 3, 100);
        testList.removeGoal(g1);
        assertEquals(0, testList.getGoalList().size());
    }

    @Test
    void testGetGoalList() {
        Goal g1 = new Goal("sleep", 1, 100);
        Goal g2 = new Goal("study", 2, 100);
        testList.addGoal(g1);
        testList.addGoal(g2);
        assertEquals(2, testList.getGoalList().size());
        assertTrue(testList.getGoalList().contains(g1));
        assertTrue(testList.getGoalList().contains(g2));

    }

    @Test
    void testGetLength() {
        assertEquals(0, testList.getLength());
        Goal g1 = new Goal("sleep", 1, 0);
        testList.addGoal(g1);
        assertEquals(1, testList.getLength());


    }

//    public void setGoalList(List<Goal> goalList) {
//        this.goalList = goalList;
//    }
//
    @Test
    void testSetGoalList() {
        List<Goal> goalList = new ArrayList<>();
        testList.setGoalList(goalList);
        assertTrue(this.testList.getGoalList() == goalList);
    }

    @Test
    void testSetUser() {
        testList.setUser("Test User");
        assertTrue(this.testList.getUser() == "Test User");
    }
}
