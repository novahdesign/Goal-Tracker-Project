package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Goal with an id, goal name, and progress measured in hours
public class Goal implements Writable {
    private static int nextGoalID = 1;
    private int id;
    private String name;
    private int currentHours;
    private int targetHours;

    // REQUIRES: goalName has nonzero length
    // EFFECTS: goal is set to goalName, goal id is integer not assigned to any other goal
    // if initialProgress >= 0 then progress on account is set to
    // initialProgress, otherwise progress is zero.
    public Goal(String goalName, int currentHours, int targetHours) {
        id = nextGoalID++;
        name = goalName;
        this.currentHours = currentHours;
        if (targetHours == 0) {
            targetHours = Constants.defaultTargetHours;
            System.out.println("Target hours set to default 100 hours.");
        } else {
            this.targetHours = targetHours;
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
    public int getCurrentHours() {
        if (currentHours < 0) {
            return 0;
        }
        return currentHours;
    }

    // EFFECTS: returns target hours
    public int getTargetHours() {
        return targetHours;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: amount is added to progress and progress is returned
    public double addTime(int amount) {
        currentHours = currentHours + amount;
        return currentHours;
    }

    // EFFECTS: returns progress = to amount
    public double getProgress() {
        double progress = ((double) currentHours / (double) targetHours) * 100;
        return progress;
    }

    // Code modelled from: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Override
    public JSONObject toJson() {
        JSONObject jsonGoal = new JSONObject();
        jsonGoal.put("name", name);
        jsonGoal.put("target hours", targetHours);
        jsonGoal.put("current hours", (String.valueOf(currentHours)));


        return jsonGoal;
    }
}