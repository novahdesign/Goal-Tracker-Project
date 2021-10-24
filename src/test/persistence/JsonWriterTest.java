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

//
//    // write down the data to a file, use reader to read it back in to check
//    @Test
//    void testWriterGoalTrackerNormal() {
//        try {
//            GoalTracker goalTracker = new GoalTracker("Goal Tracker Test User");
//            goalTracker.addGoal(new Goal("study", 0));
//            goalTracker.addGoal(new Goal("sleep",0));
//            JsonWriter writer = new JsonWriter(".data/testWriterGoalTrackerNormal.json");
//
//            writer.write(goalTracker);
//
//
//            JsonReader reader = new JsonReader(".data/testWriterGoalTrackerNormal.json");
//            goalTracker = reader.read();
//            assertEquals("Goal Tracker Test User", goalTracker.getUser());
//
//            List<Goal> goals = goalTracker.getGoalList();
//            assertEquals(2, goals.size());
//            checkGoal("study", 0, goals.get(0));
//            checkGoal("sleep", 0, goals.get(1));
//
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }

}