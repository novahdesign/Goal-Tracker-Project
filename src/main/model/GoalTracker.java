package model;

import java.util.ArrayList;
import java.util.List;

public class GoalTracker {
    List<Goal> goalList;

    public GoalTracker() {
        goalList = new ArrayList<>();
    }

    public void addGoal(Goal g) {
        goalList.add(g);
    }

    public void removeGoal(Goal g) {
        goalList.remove(g);
    }
}
