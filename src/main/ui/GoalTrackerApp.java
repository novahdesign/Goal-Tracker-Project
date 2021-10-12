package ui;

import model.Goal;

// Goal Tracker Application
public class GoalTrackerApp {
    private Goal study;

    public GoalTrackerApp() {
        runGoalTracker();
    }

    private void runGoalTracker() {

        init();
    }

    //initializes the Goals
    private void init() {
        study = new Goal();

    }
}


