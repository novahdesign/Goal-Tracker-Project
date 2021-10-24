package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a GoalTracker comprised of an ArrayList of Goals
public class GoalTracker implements Writable {
    private List<Goal> goalList;
    private String user = null;

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    public List<Goal> getGoalList() {
        return goalList;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    // CONSTRUCTOR
    // EFFECTS: initialize the goalList as an empty ArrayList
    public GoalTracker() {
        goalList = new ArrayList<>();
    }

    // CONSTRUCTOR
    // EFFECTS: initialize the user, assigns GoalTracker user to the user
    public GoalTracker(String user) {
        this.user = user;
    }

    // REQUIRES: Goal g not already in list
    // MODIFIES: this
    // EFFECTS: adds Goal g to the goalList
    public void addGoal(Goal g) {
        goalList.add(g);
    }

    // REQUIRES: goalList is not an empty list
    // MODIFIES: this
    // EFFECTS: Goal g is removed from the goalList
    public void removeGoal(Goal g) {
        goalList.remove(g);
    }

    // EFFECTS: returns list size

    public int getLength() {
        return goalList.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("user", user);
        json.put("goals", goalsToJson());
        return json;
    }


    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray goalsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Goal goal : goalList) {
            jsonArray.put(goal.toJson());
        }

        return jsonArray;
    }

}

