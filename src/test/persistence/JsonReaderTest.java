package persistence;

import model.Goal;
import model.GoalTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileDoesNotExist.json");
        try {
            GoalTracker goalTracker = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyGoalTracker() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyGoalTracker.json");
        try {
            GoalTracker goalTracker = reader.read();
            assertEquals("Goal Tracker Test User", goalTracker.getUser());
            assertEquals(0, goalTracker.getLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderNormalGoalTracker() {
        JsonReader reader = new JsonReader("./data/testReaderNormalGoalTracker.json");

        try {
            GoalTracker goalTracker = reader.read();
            assertEquals("Goal Tracker Test User", goalTracker.getUser());

            List<Goal> goals = goalTracker.getGoalList();
            assertEquals(2, goalTracker.getLength());
            assertEquals("Goal Tracker Test User", goalTracker.getUser());
            checkGoal("study", 0, goals.get(0));
            checkGoal("sleep", 0, goals.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}