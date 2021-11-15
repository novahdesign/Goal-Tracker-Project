package ui;

import model.Goal;
import model.GoalTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

// Goal Tracker Application
public class GoalTrackerApp {
    private static final String JSON_LOC = "./data/goaltracker.json";
    private GoalTracker goalTracker;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


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
                saveGoalTracker();

            } else {
                processCommand(command);

            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes the Goals
    private void initialize() {
        jsonReader = new JsonReader(JSON_LOC);
        loadGoalTracker();
        jsonWriter = new JsonWriter(JSON_LOC);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: loads the goals in the Goal Tracker
    private void loadGoalTracker() {
        try {
            goalTracker = jsonReader.read();
        } catch (IOException e) {
            goalTracker = new GoalTracker();
            System.out.println("This is the first time using Goal Tracker!");
            goalTracker.setUser("Default User"); // in future setUser(username) given in intialize
        }
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
//            try {
            doRemoveGoal();
//            } catch (EmptyException e) {
//                System.out.println("Goal list is empty, add a goal!");
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
        for (Goal goal : goalTracker.getGoalList()) {
            double p = goal.getProgress();

            System.out.println(goal.getName() + ", Current Hours:" + goal.getCurrentHours() + ", Progress:"
                    + goal.getProgress());
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts adding time to selected goal's progress
    private void doAddTime() {
        System.out.println("List of goals to choose from:");
        doViewGoals();
        Goal selected = selectGoal();

        System.out.print("Enter hours to add: ");
        int amount = input.nextInt();

        if (amount >= 0) {
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
        doViewGoals();
        Goal selected = selectGoal();
        goalTracker.removeGoal(selected);
        doViewGoals();
    }

    // MODIFIES: this
    // EFFECTS: adds a goal to the list of goals
    private void doAddGoal() {
        System.out.println("Enter name of the goal:");
        String name = input.next();

        System.out.println("Enter target hours:");
        int target = input.nextInt();

        System.out.println("Added a goal! ");
        System.out.println("Name: " + name + " " + "Target: " + target);

        Goal g = new Goal(name, 0, target);
        goalTracker.addGoal(g);


    }


    // REQUIRES: non-empty list of goals
    // EFFECTS: prompts user to select a goal and returns it
    private Goal selectGoal() {

        String selection = "";  // force entry into loop
        Goal ret = null; // return goal that is inputted
        System.out.println("Enter a goal name:");

        while ((ret == null)) {
            selection = input.next();
            for (Goal goal : goalTracker.getGoalList()) {
                if (goal.getName().equals(selection)) {
                    ret = goal;
                    break;
                }
            }
        }
        return ret;
    }

    // EFFECTS: prints the progress of a goal
    private void printProgress(Goal selected) {
        System.out.println("Goal: " + selected.getName());
        System.out.printf("Progress: %2f hours\n", selected.getProgress());
    }


    // EFFECTS: saves the workroom to file
    private void saveGoalTracker() {
        try {
            jsonWriter.write(goalTracker);
            System.out.println("Saved " + goalTracker.getUser() + " to " + JSON_LOC);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOC);
        }
    }
}


