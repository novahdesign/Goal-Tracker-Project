package ui;

import model.Goal;

import java.util.Scanner;

//import model.Goal;

// Goal Tracker Application
public class GoalTrackerApp {
    private Goal study;
    private Goal sleep;
    private Scanner input;

    public GoalTrackerApp() {
        runGoalTracker();
    }

    private void runGoalTracker() {
        boolean keepGoing = true;
        String command = null;

        initialize();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    //initializes the Goals
    private void initialize() {
        study = new Goal("study", 10);
        sleep = new Goal("sleep", 0);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tag -> add goal");
        System.out.println("\trg -> remove goal");
        System.out.println("\tt -> add time");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("ag")) {
            doAddGoal();
        } else if (command.equals("rg")) {
            doRemoveGoal();
        } else if (command.equals("t")) {
            doAddTime();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doAddTime() {
    }

    private void doRemoveGoal() {
    }

    private void doAddGoal() {
    }

}


