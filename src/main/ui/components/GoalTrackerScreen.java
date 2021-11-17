package ui.components;

import model.Goal;
import model.GoalTracker;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GoalTrackerScreen extends DefaultListModel implements ActionListener, Writable {

    Goal goal;

    private static final String JSON_LOC = "./data/goaltracker.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private List<Goal> goalList = new ArrayList<>();
    private String user = null;

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    public List<Goal> getGoalList() {
        return goalList;
    }

    JList testList;
    DefaultListModel listModel;
    JTextField goalName;

    GoalTrackerScreen goalTrackerScreen;
    JPanel panel;

    JLabel welcome = getWelcomeGif();
    JLabel moon = getMoonGif();
    JButton loadButton = getLoadButton();
    JButton inspireButton = getInspireButton();
    JButton addGoalButton = getAddGoalButton();
    JButton editGoalButton = getEditGoalButton();
    JButton saveButton = getSaveButton();

    JScrollPane listScrollPane = getScrollList();

    GoalTracker goalTracker = new GoalTracker();



    public GoalTrackerScreen() throws MalformedURLException {

        jsonReader = new JsonReader(JSON_LOC);
        // loadGoalTracker();
        jsonWriter = new JsonWriter(JSON_LOC);



        JFrame frame = new JFrame();
        frame.setSize(530, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        frame.add(panel);
        panel.setBackground(new Color(255, 211, 242));

        panel.add(welcome);
        panel.add(moon);
        panel.add(loadButton);
        panel.add(inspireButton);
        panel.add(addGoalButton);
        panel.add(editGoalButton);
        panel.add(saveButton);

        panel.add(listScrollPane);

        frame.setVisible(true);


    }
    
    private JScrollPane getScrollList() {

        Goal testGoal = new Goal("Finish Phase 3", 10, 100);

        listModel = new DefaultListModel();

        listModel.addElement(testGoal.getName());
        listModel.addElement(testGoal.getProgress());

        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

        testList = new JList(listModel);
        testList.setVisibleRowCount(15);

        JScrollPane scrollList = new JScrollPane(testList);
        scrollList.setBounds(215, 90,240,200);

        return scrollList;
    }


    private JButton getSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(40, 210, 165, 25);
        saveButton.setActionCommand("saveButton");
        saveButton.addActionListener(this);
        return saveButton;
    }

    private JButton getEditGoalButton() {
        JButton editGoalButton = new JButton("Edit Goal");
        editGoalButton.setBounds(40, 180, 165, 25);
        editGoalButton.setActionCommand("editGoalButton");
        editGoalButton.addActionListener(this);
        return editGoalButton;
    }

    private JButton getAddGoalButton() {
        JButton addGoal = new JButton("Add Goal");
        addGoal.setBounds(40, 150, 165, 25);
        addGoal.addActionListener(this);
        addGoal.setActionCommand("addGoal");
        return addGoal;
    }

    private JButton getInspireButton() {
        JButton inspireButton = new JButton("Inspiration");
        inspireButton.setBounds(40, 120, 165, 25);
        inspireButton.setActionCommand("inspireButton");
        inspireButton.addActionListener(this);

        return inspireButton;
    }

    private JButton getLoadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.setBounds(40, 90, 165, 25);
        return loadButton;
    }

    private JLabel getWelcomeGif() throws MalformedURLException {

        URL url = new URL("http://www.animatedgif.net/welcome/weltrain_e0.gif");
        Icon icon = new ImageIcon(url);
        JLabel welcomeGif = new JLabel(icon);
        welcomeGif.setBounds(5,5,490,80);
        return welcomeGif;
    }

    private JLabel getMoonGif() throws MalformedURLException {

        URL moonUrl = new URL("https://media1.giphy.com/media/9ukPOCS9EOEta/giphy.gif?cid=ecf05e47y8fyxri3mvpgrqhtcdgwa9pm4dhleghq3027ccje&rid=giphy.gif&ct=s");
        Icon moonIcon = new ImageIcon(moonUrl);
        JLabel moon = new JLabel(moonIcon);
        return moon;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        GoalTracker goalTracker = new GoalTracker();

        Goal goal = new Goal("test", 20, 100);

        if ("addGoal".equals(e.getActionCommand())) {
            doAddNewGoal();
        } else if ("inspireButton".equals(e.getActionCommand())) {
            try {
                new InspirationScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if ("editGoalButton".equals(e.getActionCommand())) {
            new GoalDetailScreen(goal, goalTracker);
        } else if ("saveButton".equals(e.getActionCommand())) {
            saveGoalTrackerScreen();
        }

    }

    private ActionListener saveGoalTrackerScreen() {

        try {
            jsonWriter.write(goalTracker);
            System.out.println("Saved " + goalTracker.getUser() + " to " + JSON_LOC);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOC);
        }

        return goalTrackerScreen;
    }

    private ActionListener doAddNewGoal() {

        Goal goal = new Goal("enter name",0,100);
   //     GoalTracker goalTracker = new GoalTracker();

        new GoalDetailScreen(goal, goalTracker);

        System.out.println("Enter name of the goal:");
        String name = goal.getName();

        System.out.println("Enter target hours:");
        int target = goal.getTargetHours();

        int current = goal.getCurrentHours();

        Goal g = new Goal(name, current, target);
        goalTracker.addGoal(g);
        listModel.addElement(g.getName());

        System.out.println("Added a goal! ");
        System.out.println("Name: " + name + " " + "Target: " + target);

        return new GoalDetailScreen(g, goalTracker);

//        new GoalDetailScreen(emptyGoal, goalTracker);
//
//        emptyGoal.setName(emptyGoal.getName());
//        emptyGoal.setCurrentHours(emptyGoal.getCurrentHours());
//        emptyGoal.setTargetHours(emptyGoal.getTargetHours());
//
//
////        String name = this.goal.getName();
////        int current = this.goal.getCurrentHours();
////        int target = this.goal.getTargetHours();
////
//        goalTracker.addGoal(emptyGoal);
//
//        return new GoalDetailScreen(emptyGoal, goalTracker);

    }

        //
//            Goal emptyGoal = new Goal("", 0, 100);
//
//            new GoalDetailScreen(emptyGoal, goalTracker);
//            goalTracker.getGoalList().add(goal);
//
//            return new GoalDetailScreen(goal, goalTracker);
    //
//        JButton loadButton = new JButton("Load");
//        loadButton.setBounds(20, 400, 165, 25);
//        panel.add(loadButton);
//
//        JButton inspireButton = new JButton("Inspiration");
//        inspireButton.setBounds(60, 90, 165, 25);
//        panel.add(inspireButton);
//
//        inspireButton.addActionListener(this);
//
//        JButton addGoal = new JButton("Add Goal");
//        addGoal.setBounds(40, 90, 165, 25);
//        panel.add(addGoal);
//
//        addGoal.addActionListener(this);
//
//        JButton addTimeButton = new JButton("Add Time");
//        addTimeButton.setBounds(80, 90, 165, 25);
//        panel.add(addTimeButton);
//
//        JButton saveButton = new JButton("Save");
//        saveButton.setBounds(100, 90, 165, 25);
//        panel.add(saveButton);
//
//        frame.setVisible(true);

    public static void main(String[] args) throws MalformedURLException {
        new GoalTrackerScreen();
    }

//    public void addGoalButton() {
//        JButton addGoal = new JButton("Add Goal");
//        addGoal.setBounds(40, 90, 165, 25);
//        panel.add(addGoal);
//
//        addGoal.addActionListener(this);
//    }




}