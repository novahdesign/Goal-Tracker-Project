package ui;

import model.Goal;
import model.GoalTracker;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

//import model.Goal;

// Goal Tracker Application
public class GoalTrackerApp {
    private Goal study;
    private Goal sleep;
    private GoalTracker goalList;

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
        goalList = new GoalTracker();

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
        System.out.println("\tv -> view all goals");
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
        } else if (command.equals("v")) {
            doViewGoals();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void doViewGoals() {
        for (Goal goal : goalList.getGoalList()) {
            System.out.println(goal.getName() + ", " + goal.getProgress());
        }
    }
//        GoalTracker g = goalList;
//        for (GoalTracker g : ) {
//            System.out.println(selectGoal().getName());
//        }
//    }

    private void doAddTime() {
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

    private void doRemoveGoal() {
        System.out.println("Enter name of the goal");
        String name = input.next();
    }


    private void doAddGoal() {
        System.out.println("Enter name of the goal");
        String name = input.next();

        System.out.println("Enter progress so far:");
        double progress = input.nextDouble();

        Goal g = new Goal(name, progress);
        goalList.addGoal(g);


    }

    // EFFECTS: prompts user to select study or sleep goal and returns it
    private Goal selectGoal() {
        String selection = "";  // force entry into loop

        while (!(selection.equals("1") || selection.equals("2"))) {
            System.out.println("1 for study");
            System.out.println("2 for sleep");
            selection = input.next();
            selection = selection.toLowerCase();
        }

        if (selection.equals("1")) {
            return study;
        } else {
            return sleep;
        }
    }

    private void printProgress(Goal selected) {
        System.out.printf("Progress: %.2f hours\n", selected.getProgress());
    }
}




