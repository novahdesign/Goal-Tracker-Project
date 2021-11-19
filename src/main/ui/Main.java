package ui;

import model.Goal;
import model.GoalTracker;
import ui.components.GoalDetailScreen;
import ui.components.GoalTrackerScreen;

import java.net.MalformedURLException;

// Main class that creates instance of GoalTrackerScreen class
public class Main {
    public static void main(String[] args) throws MalformedURLException {
        new GoalTrackerScreen();
    }
}
