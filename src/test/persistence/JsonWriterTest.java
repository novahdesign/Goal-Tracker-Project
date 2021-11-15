package persistence;


import model.Goal;
import model.GoalTracker;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterIllegalFile() {
        try {
            GoalTracker goalTracker = new GoalTracker("Goal Tracker Test User");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyGoalTracker() {
        try {
            GoalTracker goalTracker = new GoalTracker("Goal Tracker Test User");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyGoalTracker.json");
            writer.open();
            writer.write(goalTracker);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyGoalTracker.json");
            goalTracker = reader.read();
            assertEquals("Goal Tracker Test User", goalTracker.getUser());
            assertEquals(0, goalTracker.getLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterNormalGoalTracker() {
        try {
            GoalTracker goalTracker = new GoalTracker("Goal Tracker Test User");
            JsonWriter writer = new JsonWriter("./data/testWriterNormalGoalTracker.json");
            goalTracker.addGoal(new Goal("study", 0, 100));
            goalTracker.addGoal(new Goal("sleep",0, 100));

            writer.open();
            writer.write(goalTracker);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNormalGoalTracker.json");
            goalTracker = reader.read();

            List<Goal> goals = goalTracker.getGoalList();
            assertEquals("Goal Tracker Test User", goalTracker.getUser());
            assertEquals(2, goalTracker.getLength());
            checkGoal("study", 0, goals.get(0));
            checkGoal("sleep", 0, goals.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

}