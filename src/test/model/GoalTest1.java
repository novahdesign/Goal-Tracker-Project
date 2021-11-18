package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test Class for Goal
class GoalTest {
    private Goal testGoal;

    @BeforeEach
    void runBefore() {
        testGoal = new Goal("study cpsc", 0, 100);
    }

    @Test
    void testConstructor() {
        assertEquals("study cpsc", testGoal.getName());
        assertEquals(0, testGoal.getCurrentHours());
        assertTrue(testGoal.getId() > 0);
    }

    @Test
    void testAddTime(){
        testGoal.addTime(20);
        assertEquals(20,testGoal.getCurrentHours());

    }

    @Test
    void testMultipleAddTime() {
        testGoal.addTime(2);
        testGoal.addTime(3);
        assertEquals(5,testGoal.getCurrentHours());
    }

    @Test
    void testGetCurrent() {
        testGoal.addTime(4);
        assertEquals(4, testGoal.getCurrentHours());
    }

    // Tests if initial progress is negative, return 0 if true
    @Test
    void testNegGetProgress() {
        Goal testNegGoal = new Goal("negative", -3, 100);
        assertEquals(0, testNegGoal.getCurrentHours());
    }

    @Test
    void testGetProgress() {
        testGoal.addTime(10);
        assertEquals(10, testGoal.getProgress());

    }

    @Test
    void testSetName() {
        testGoal.setName("NewName");
        assertTrue(testGoal.getName() == "NewName");
    }

    @Test
    void testSetCurrentHours() {
        testGoal.setCurrentHours(24);
        assertEquals(24, testGoal.getCurrentHours());
    }

    @Test
    void testSetTargetHours() {
        testGoal.setTargetHours(50);
        assertEquals(50, testGoal.getTargetHours());
    }

    @Test
    void testGetTargetHours() {
        testGoal.setTargetHours(5);
        assertEquals(5, testGoal.getTargetHours());
    }


}