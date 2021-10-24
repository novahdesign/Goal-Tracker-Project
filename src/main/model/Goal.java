package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Goal with an id, goal name, and progress measured in hours
public class Goal implements Writable {
    private static int nextGoalID = 1;
    private int id;
    private String name;
    private int progress;

    // REQUIRES: goalName has nonzero length
    // EFFECTS: goal is set to goalName, goal id is integer not assigned to any other goal
    // if initialProgress >= 0 then progress on account is set to
    // initialProgress, otherwise progress is zero.
    public Goal(String goalName, int initialProgress) {
        id = nextGoalID++;
        name = goalName;
        if (initialProgress >= 0) {
            progress = initialProgress;
        } else {
            progress = 0;
        }
    }

    // EFFECTS: returns goal id
    public int getId() {
        return id;
    }

    // EFFECTS: returns goal name
    public String getName() {
        return name;
    }

    // EFFECTS: returns progress
    public double getProgress() {
        return progress;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: amount is added to progress and progress is returned
    public double addTime(int amount) {
        progress = progress + amount;
        return progress;
    }

    // EFFECTS: returns progress = to amount
    public double trackProgress(int amount) {
        progress = amount;
        return progress;
    }

    public JSONObject toJson() {
        JSONObject jsonGoal = new JSONObject();
        jsonGoal.put("name",name);
        jsonGoal.put("progress", (String.valueOf(progress)));

        return jsonGoal;
    }
}