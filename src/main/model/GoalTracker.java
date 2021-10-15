package model;

import java.util.ArrayList;
import java.util.List;

// Represents a GoalTracker comprised of an ArrayList of Goals
public class GoalTracker {
    List<Goal> goalList;

    // CONSTRUCTOR
    // EFFECTS: initialize the goalList as an empty ArrayList
    public GoalTracker() {
        goalList = new ArrayList<>();
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

    // EFFECTS: returns goalList
    public List<Goal> getGoalList() {
        return goalList;
    }
}
