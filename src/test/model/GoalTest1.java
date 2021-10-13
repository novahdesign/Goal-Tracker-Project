package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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

}