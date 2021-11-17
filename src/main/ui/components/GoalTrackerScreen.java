package ui.components;

import model.Goal;
import model.GoalTracker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GoalTrackerScreen implements ActionListener {

    GoalTrackerScreen goalTrackerScreen;

    JPanel panel;

    public GoalTrackerScreen() {

        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.add(panel);

        JButton loadButton = new JButton("Load");
        loadButton.setBounds(20, 400, 165, 25);
        panel.add(loadButton);

        JButton inspireButton = new JButton("Inspiration");
        inspireButton.setBounds(60, 90, 165, 25);
        panel.add(inspireButton);
        inspireButton.setActionCommand("inspireButton");

        inspireButton.addActionListener(this);

        JButton addGoal = new JButton("Add Goal");
        addGoal.setBounds(40, 90, 165, 25);
        panel.add(addGoal);
        addGoal.setActionCommand("addGoal");

        addGoal.addActionListener(this);

        JButton editGoalButton = new JButton("Edit Goal");
        editGoalButton.setBounds(80, 90, 165, 25);
        panel.add(editGoalButton);
        editGoalButton.setActionCommand("editGoalButton");

        editGoalButton.addActionListener(this);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(100, 90, 165, 25);
        panel.add(saveButton);

        frame.setVisible(true);


    }
//
//    public void loadButton() {
//        JButton loadButton = new JButton("Load");
//        loadButton.setBounds(20, 400, 165, 25);
//        panel.add(loadButton);

    @Override
    public void actionPerformed(ActionEvent e) {
        Goal emptyGoal = new Goal("", 0, 100);
        GoalTracker goalTracker = new GoalTracker();

        Goal goal = new Goal("test", 20, 100);

        if ("addGoal".equals(e.getActionCommand())) {
            new GoalDetailScreen(emptyGoal, goalTracker);
        } else if ("inspireButton".equals(e.getActionCommand())) {
            try {
                new InspirationScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if ("editGoalButton".equals(e.getActionCommand())) {
            new GoalDetailScreen(goal, goalTracker);
        }

    }

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

    public static void main(String[] args) {
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