package ui.components;

import model.Goal;
import model.GoalTracker;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class GoalTrackerScreen extends DefaultListModel implements ActionListener  {

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



    public GoalTrackerScreen() throws MalformedURLException {

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


        listModel = new DefaultListModel();

        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");

        testList = new JList(listModel);
        testList.setVisibleRowCount(5);

        JScrollPane scrollList = new JScrollPane(testList);
        scrollList.setBounds(215, 90,240,200);

        return scrollList;
    }


    private JButton getSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(40, 210, 165, 25);
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
        Goal emptyGoal = new Goal("", 0, 100);
        GoalTracker goalTracker = new GoalTracker();

        Goal goal = new Goal("test", 20, 100);

        if ("addGoal".equals(e.getActionCommand())) {
            addNewGoal();
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

    private ActionListener addNewGoal() {
        return null;
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