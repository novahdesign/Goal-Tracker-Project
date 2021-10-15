package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test Class for Goal
class GoalTest {
    private Goal testGoal;

    @BeforeEach
    void runBefore() {
        testGoal = new Goal("study cpsc", 0);
    }

    @Test
    void testConstructor() {
        assertEquals("study cpsc", testGoal.getName());
        assertEquals(0, testGoal.getProgress());
        assertTrue(testGoal.getId() > 0);
    }

    @Test
    void testAddTime(){
        testGoal.addTime(20);
        assertEquals(20,testGoal.getProgress());

    }

    @Test
    void testMultipleAddTime() {
        testGoal.addTime(2);
        testGoal.addTime(3);
        assertEquals(5,testGoal.getProgress());
    }

    @Test
    void testGetProgress() {
        testGoal.addTime(4);
        assertEquals(4, testGoal.getProgress());
    }

    // Tests if initial progress is negative, return 0 if true
    @Test
    void testNegGetProgress() {
        Goal testNegGoal = new Goal("negative", -3);
        assertEquals(0, testNegGoal.getProgress());
    }

    @Test
    void testTrackProgress() {
        testGoal.addTime(0);
        assertEquals(0, testGoal.trackProgress(0));

    }


}