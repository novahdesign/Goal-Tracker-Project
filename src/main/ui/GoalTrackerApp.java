package ui;

import exceptions.EmptyException;
import model.Goal;
import model.GoalTracker;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

// Goal Tracker Application
public class GoalTrackerApp {
//    private Goal study;
//    private Goal sleep;
//    private Goal water;

    private GoalTracker goalList;
    private Scanner input;

    // EFFECTS: runs the GoalTracker application
    public GoalTrackerApp() {
        runGoalTracker();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
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

    // MODIFIES: this
    // EFFECTS: initializes the Goals
    private void initialize() {
        goalList = new GoalTracker();
//        study = new Goal("study", 0);
//        sleep = new Goal("sleep", 0);
//        water = new Goal("drink water", 0);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to Goal Tracker!");
        System.out.println("\nSelect from:");
        System.out.println("\ti -> inspiration :)");
        System.out.println("\tag -> add goal");
        System.out.println("\trg -> remove goal");
        System.out.println("\tt -> add time");
        System.out.println("\tq -> quit");
        System.out.println("\tv -> view all goals");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("ag")) {
            doAddGoal();
        } else if (command.equals("i")) {
            doInspiration();
        } else if (command.equals("rg")) {
            doRemoveGoal();
        } else if (command.equals("t")) {
            doAddTime();
        } else if (command.equals("v")) {
            doViewGoals();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: prints two lines of inspirational quotes
    private void doInspiration() {
        System.out.println("You are doing great! <3");
        System.out.println("Our greatest glory is not in never falling, but in rising every time we fall. ");
    }

    // EFFECTS: prints goal name and goal progress from list of goals
    private void doViewGoals() {
        for (Goal goal : goalList.getGoalList()) {
            System.out.println(goal.getName() + ", " + goal.getProgress());
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts adding time to selected goal's progress
    private void doAddTime() {
        System.out.println("List of goals to choose from:");
        doViewGoals();
        Goal selected = selectGoal();

        System.out.print("Enter hours to add: ");
        double amount = input.nextDouble();

        if (amount >= 0.0) {
            selected.addTime(amount);
        } else {
            System.out.println("Cannot add negative amount...\n");
        }

        printProgress(selected);
    }

    // REQUIRES: list of goals is non-empty
    // MODIFIES: this
    // EFFECTS: removes a goal from the list of goals
    private void doRemoveGoal() {

        if (goalList.getLength() == 0) {
            System.out.println("Goal list is empty, add a goal!");


//        if (goalList.getLength() == 0) {
//            try {
//                throw new EmptyException();
//            } catch (EmptyException e) {
//                System.out.println("Goal list is empty! Add a goal!");
//                displayMenu();
//            }
        } else {
            Goal selected = selectGoal();

            goalList.removeGoal(selected);

            doViewGoals();

        }
    }

    // MODIFIES: this
    // EFFECTS: adds a goal to the list of goals
    private void doAddGoal() {
        System.out.println("Enter name of the goal:");
        String name = input.next();

        System.out.println("Enter progress so far:");
        double progress = input.nextDouble();

        System.out.println("Added a goal! ");
        System.out.println("Name: " + name + " " + "Progress: " + progress);

        Goal g = new Goal(name, progress);
        goalList.addGoal(g);


    }


    // REQUIRES: non-empty list of goals
    // EFFECTS: prompts user to select a goal and returns it
    private Goal selectGoal() {


        String selection = "";  // force entry into loop
        Goal ret = null; // return goal that is inputted
        System.out.println("Enter a goal name:");

        while ((ret == null)) {
            selection = input.next();
            for (Goal goal : goalList.getGoalList()) {
                if (goal.getName().equals(selection)) {
                    ret = goal;
                    break;
                }
            }
        }
        return ret;
    }


    // iterate over list
    // get goalList, run for each, match
    //    selection = selection.toLowerCase();


    // EFFECTS: prints the progress of a goal
    private void printProgress(Goal selected) {
        System.out.println("Goal: " + selected.getName());
        System.out.printf("Progress: %.2f hours\n", selected.getProgress());
    }
}




