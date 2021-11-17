package ui;

import model.Goal;
import model.GoalTracker;
import ui.components.GoalDetailScreen;
import ui.components.GoalTrackerScreen;

import java.net.MalformedURLException;

// Main class that creates instance of GoalTrackerApp class
public class Main {
    public static void main(String[] args) throws MalformedURLException {
      //  new GoalTrackerApp();
        Goal initGoal = new Goal("Testing Goal", 25,160);
//        GoalTracker goalTracker = new GoalTracker("Default User");
        new GoalTrackerScreen();
    //    new GoalDetailScreen(initGoal, goalTracker);

        System.out.println(initGoal.getName());
       // new TextDemo();
    }
}
