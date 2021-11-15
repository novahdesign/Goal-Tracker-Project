package ui;

import model.Goal;
import ui.components.GoalDetailScreen;

// Main class that creates instance of GoalTrackerApp class
public class Main {
    public static void main(String[] args) {
      //  new GoalTrackerApp();
        new GoalDetailScreen(new Goal("Testing Goal", 25,160));
       // new TextDemo();
    }
}
