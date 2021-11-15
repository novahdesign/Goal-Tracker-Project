package ui;

import model.Goal;
import ui.components.GoalDetailScreen;

// Main class that creates instance of GoalTrackerApp class
public class Main {
    public static void main(String[] args) {
      //  new GoalTrackerApp();
        Goal initGoal = new Goal("Testing Goal", 25,160);
        new GoalDetailScreen(initGoal);
        System.out.println(initGoal.getName());
       // new TextDemo();
    }
}
