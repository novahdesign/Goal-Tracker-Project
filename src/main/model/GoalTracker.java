package model;

import java.util.ArrayList;
import java.util.List;

// Represents a GoalTracker comprised of an ArrayList of Goals
public class GoalTracker {
    List<Goal> goalList;

    public GoalTracker() {
        goalList = new ArrayList<>();
    }

    // REQUIRES
    // MODIFIES
    // EFFECTS
    public void addGoal(Goal g) {
        goalList.add(g);
    }

    public void removeGoal(Goal g) {
        goalList.remove(g);
    }

    public List<Goal> getGoalList() {
        return goalList;
    }
}
