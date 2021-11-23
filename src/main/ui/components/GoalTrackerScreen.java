package ui.components;

import model.EventLog;
import ui.components.GoalDetailScreen;
import model.Goal;
import model.GoalTracker;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
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

// Goal Tracker Screen that is the opening GUI window upon opening the application
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

    public GoalTracker getGoalTracker() {
        return goalTracker;
    }

    GoalTracker goalTracker = new GoalTracker("Default User");

    // EFFECTS: creates the Goal Tracker Screen window
    public GoalTrackerScreen() throws MalformedURLException {

        jsonReader = new JsonReader(JSON_LOC);
        jsonWriter = new JsonWriter(JSON_LOC);

        JFrame frame = new JFrame();
        frame.setSize(530, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);

        frame.add(panel);
        panel.setBackground(new Color(255, 198, 248));
        //  panel.setBackground(new Color(255, 211, 242));

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

    // EFFECTS: initializes the Scroll List pane to display list of goals
    private JScrollPane getScrollList() {

        listModel = new DefaultListModel();

        testList = new JList(listModel);
        testList.setVisibleRowCount(15);

        JScrollPane scrollList = new JScrollPane(testList);
        scrollList.setBounds(215, 90, 240, 200);

        ListSelectionModel listSelectionModel;
        listSelectionModel = testList.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                new SharedListSelectionHandler());

        return scrollList;
    }

    // MODIFIES: this
    // EFFECTS: gets the selected index value
    private class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            Goal selectedGoal;

            if (e.getValueIsAdjusting() == false) {

                if (testList.getSelectedIndex() == -1) {
                    System.out.println("select something!");
                } else {
                    //Selection, get the selected Index
                    int selectedIndex = testList.getSelectedIndex();
                    selectedGoal = goalTracker.getGoalList().get(selectedIndex);
                    new GoalDetailScreenEditView(selectedGoal);

                    testList.getSelectedValue();
                    System.out.println(testList.getSelectedValue());

                }
            }

        }
    }

    // EFFECTS: creates the save button
    private JButton getSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(40, 210, 165, 25);
        saveButton.setActionCommand("saveButton");
        saveButton.addActionListener(this);
        return saveButton;
    }

    // EFFECTS: creates the edit goal button
    private JButton getEditGoalButton() {
        JButton editGoalButton = new JButton("Edit/View Goal");
        editGoalButton.setBounds(40, 180, 165, 25);
        editGoalButton.setActionCommand("editGoalButton");
        editGoalButton.addActionListener(this);
        return editGoalButton;
    }

    // EFFECTS: creates the add goal button
    private JButton getAddGoalButton() {
        JButton addGoalButton = new JButton("Add Goal");
        addGoalButton.setBounds(40, 150, 165, 25);
        addGoalButton.addActionListener(this);
        addGoalButton.setActionCommand("addGoal");
        return addGoalButton;
    }

    // EFFECTS: creates the inspiration button
    private JButton getInspireButton() {
        JButton inspireButton = new JButton("Inspiration");
        inspireButton.setBounds(40, 120, 165, 25);
        inspireButton.setActionCommand("inspireButton");
        inspireButton.addActionListener(this);
        return inspireButton;
    }

    // EFFECTS: creates the load button
    private JButton getLoadButton() {
        JButton loadButton = new JButton("Load");
        loadButton.setBounds(40, 90, 165, 25);
        loadButton.setActionCommand("loadButton");
        loadButton.addActionListener(this);
        return loadButton;
    }

    // EFFECTS: gets the welcome gif
    private JLabel getWelcomeGif() {

        //   URL url = new URL("http://www.animatedgif.net/welcome/weltrain_e0.gif");
        Icon icon = new ImageIcon("data/weltrain_e0.gif");
        JLabel welcomeGif = new JLabel(icon);
        welcomeGif.setBounds(5, 5, 490, 80);
        return welcomeGif;
    }

    // EFFECTS: gets the flowers gif
    private JLabel getMoonGif() {

        //  URL moonUrl = new URL("http://www.animatedgif.net/plants/flowerline2_e0.gif");
        Icon moonIcon = new ImageIcon("data/flowerline2_e0.gif");
        JLabel moon = new JLabel(moonIcon);
        moon.setBounds(70, 70, 400, 400);
        return moon;
    }

    // EFFECTS: handles the different actions for each button when pressed
    @Override
    public void actionPerformed(ActionEvent e) {

        if ("addGoal".equals(e.getActionCommand())) {
            doAddGoal();
        } else if ("inspireButton".equals(e.getActionCommand())) {
            try {
                new InspirationScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if ("editGoalButton".equals(e.getActionCommand())) {
            doEditViewGoal();
        } else if ("saveButton".equals(e.getActionCommand())) {
            saveGoalTrackerScreen();
        } else if ("loadButton".equals(e.getActionCommand())) {
            loadGoalTracker();
        }
    }

    // EFFECTS: do edit and view goal upon selection
    private void doEditViewGoal() {

    }

    // EFFECTS: loads the goal tracker on file
    private void loadGoalTracker() {
        try {
            goalTracker = jsonReader.read();
            for (Goal goal : goalTracker.getGoalList()) {
                listModel.addElement(goal.getName());
            }

        } catch (IOException e) {
            goalTracker = new GoalTracker();
            System.out.println("This is the first time using Goal Tracker!");
            goalTracker.setUser("Default User");
        }
    }

    // EFFECTS: initializes the GoalDetailScreenAdd window with Goal g
    private void doAddGoal() {
        Goal g = new Goal("Enter", 0, 100);
        new GoalDetailScreenAdd(g);

    }

    // MODIFIES: this
    // EFFECTS: saves the Goal Tracker to the specified file location
    private ActionListener saveGoalTrackerScreen() {
        try {
            jsonWriter.write(goalTracker);
            System.out.println("Saved " + goalTracker.getUser() + " to " + JSON_LOC);
            doPrintEventLog();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOC);
        }
        return goalTrackerScreen;
    }

    private void doPrintEventLog() {
        LogPrinter lp = null;
        lp.printLog(EventLog.getInstance());
    }



    // Represents class for Goal Detail Screen to add a goal
    private class GoalDetailScreenAdd implements ActionListener {

        private Goal goal;
        private JFrame frame;

        private JTextField nameText;
        private JTextField targetText;
        private JTextField currentText;

        public JProgressBar getProgressBar() {
            return progressBar;
        }

        private JProgressBar progressBar;

        // REQUIRES: goal is not null
        // EFFECTS: creates the GoalDetailScreen panel
        public GoalDetailScreenAdd(Goal goal) {
            makeGoalDetailScreenPanel(goal);

        }

        // REQUIRES: goal is not null
        // EFFECTS: helper for creating the GoalDetailScreen panel
        private void makeGoalDetailScreenPanel(Goal goal) {
            JPanel panel = getPanel();

            panel.add(nameLabel());

            makeNameText(goal);
            panel.add(nameText);

            JLabel targetLabel = makeJLabel();
            panel.add(targetLabel);

            makeTargetText(goal);
            panel.add(targetText);

            JLabel currentLabel = makeCurrentLabel();
            panel.add(currentLabel);

            makeCurrentHoursText(goal, panel);

            makeProgressLabel(panel);

            progressBar = createProgressBar(goal);
            panel.add(progressBar);

            JButton saveButton = makeSaveButton(panel);

            saveButton.addActionListener(this);

            frame.setVisible(true);
        }

        // EFFECTS: creates JPanel
        private JPanel getPanel() {
            JPanel panel = new JPanel();
            frame = new JFrame();
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);

            panel.setLayout(null);
            Color background = new Color(255, 123, 211);
            panel.setBackground(background);
            return panel;
        }

        // EFFECTS: creates save button
        private JButton makeSaveButton(JPanel panel) {
            JButton saveButton = new JButton("Save and Back");
            saveButton.setBounds(50, 140, 165, 25);
            panel.add(saveButton);
            return saveButton;
        }

        // EFFECTS: creates progress label
        private void makeProgressLabel(JPanel panel) {
            JLabel progressLabel = new JLabel("Progress");
            progressLabel.setBounds(10, 110, 80, 25);
            panel.add(progressLabel);
        }

        // EFFECTS: creates current hours text field
        private void makeCurrentHoursText(Goal goal, JPanel panel) {
            currentText = new JTextField(20);
            currentText.setBounds(100, 80, 165, 25);
            currentText.setText(String.valueOf(goal.getCurrentHours()));
            panel.add(currentText);
        }

        // EFFECTS: creates current hours label
        private JLabel makeCurrentLabel() {
            JLabel currentLabel = new JLabel("Current Hours");
            currentLabel.setBounds(10, 80, 80, 25);
            return currentLabel;
        }

        // EFFECTS: creates target hours label
        private JLabel makeJLabel() {
            JLabel targetLabel = new JLabel("Target Hours");
            targetLabel.setBounds(10, 50, 80, 25);
            return targetLabel;
        }

        // EFFECTS: creates target hours text field
        private void makeTargetText(Goal goal) {
            targetText = new JTextField(20);
            targetText.setBounds(100, 50, 165, 25);
            targetText.setText(String.valueOf(goal.getTargetHours()));
        }

        // EFFECTS: creates name text field
        private void makeNameText(Goal goal) {
            nameText = new JTextField(20);
            nameText.setBounds(100, 20, 165, 25);
            nameText.setText(goal.getName());
        }

        public Goal getGoal() {
            return goal;
        }

        // EFFECTS: creates JProgress bar
        public JProgressBar createProgressBar(Goal goal) {
            JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
            progressBar.setBounds(100, 110, 165, 25);
            progressBar.setValue((int) goal.getProgress());
            progressBar.setStringPainted(true);

            return progressBar;
        }

        // EFFECTS: creates name label
        private JLabel nameLabel() {
            JLabel nameLabel = new JLabel("Goal Name");
            nameLabel.setBounds(10, 20, 80, 25);

            return nameLabel;
        }


        public void setGoal(Goal goal) {
            this.goal = goal;
        }

        public GoalTracker getGoalTracker() {
            return goalTracker;
        }

        // EFFECTS: creates a new goal from goal detail screen
        public Goal getNewGoal() {
            Goal newGoal = new Goal(nameText.getText(), Integer.parseInt(currentText.getText()),
                    Integer.parseInt(targetText.getText()));
            return newGoal;
        }

        // MODIFIES: this, goalTracker
        // EFFECTS: sets this goal to the new goal and adds new goal to the goal tracker
        @Override
        public void actionPerformed(ActionEvent e) {

            Goal newGoal = new Goal(nameText.getText(), Integer.parseInt(currentText.getText()),
                    Integer.parseInt(targetText.getText()));

            this.goal = newGoal;

            this.goal.setName(nameText.getText());
            this.goal.setTargetHours(Integer.parseInt(targetText.getText()));
            this.goal.setCurrentHours(Integer.parseInt(currentText.getText()));

            System.out.println(goal.getName());
            System.out.println(goal.getCurrentHours());
            System.out.println(goal.getTargetHours());
            System.out.println(goal.getProgress());

            goalTracker.addGoal(newGoal);
            listModel.addElement(newGoal.getName());
            //   listModel.addElement(getProgressBar());

            frame.dispose();

        }

    }


    // Represents the Goal Detail Screen for editing and viewing goal
    private class GoalDetailScreenEditView implements ActionListener {

        private Goal goal;
        private JFrame frame;

        private JTextField nameText;
        private JTextField targetText;
        private JTextField currentText;

        public JProgressBar getProgressBar() {
            return progressBar;
        }

        private JProgressBar progressBar;


        // EFFECTS: creates the GoalDetailScreen for editing and viewing the goal
        public GoalDetailScreenEditView(Goal goal) {

            JPanel panel = getPanel();

            panel.add(nameLabel());

            makeNameText(goal);
            panel.add(nameText);

            JLabel targetLabel = makeJLabel();
            panel.add(targetLabel);

            makeTargetText(goal);
            panel.add(targetText);

            JLabel currentLabel = makeCurrentLabel();
            panel.add(currentLabel);

            makeCurrentHoursText(goal, panel);

            makeProgressLabel(panel);

            progressBar = createProgressBar(goal);
            panel.add(progressBar);

            JButton saveButton = makeSaveButton(panel);

            saveButton.addActionListener(this);

            frame.setVisible(true);

        }


        // EFFECTS: creates the JPanel
        private JPanel getPanel() {
            JPanel panel = new JPanel();
            frame = new JFrame();
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);

            panel.setLayout(null);
            Color background = new Color(255, 123, 211);
            panel.setBackground(background);
            return panel;
        }

        // EFFECTS: creates save button
        private JButton makeSaveButton(JPanel panel) {
            JButton saveButton = new JButton("Save and Back");
            saveButton.setBounds(50, 140, 165, 25);
            panel.add(saveButton);
            return saveButton;
        }

        // EFFECTS: creates progress label
        private void makeProgressLabel(JPanel panel) {
            JLabel progressLabel = new JLabel("Progress");
            progressLabel.setBounds(10, 110, 80, 25);
            panel.add(progressLabel);
        }

        // EFFECTS: creates current hours text field
        private void makeCurrentHoursText(Goal goal, JPanel panel) {
            currentText = new JTextField(20);
            currentText.setBounds(100, 80, 165, 25);
            currentText.setText(String.valueOf(goal.getCurrentHours()));
            panel.add(currentText);
        }

        // EFFECTS: creates current hours label
        private JLabel makeCurrentLabel() {
            JLabel currentLabel = new JLabel("Current Hours");
            currentLabel.setBounds(10, 80, 80, 25);
            return currentLabel;
        }

        // EFFECTS: creates target hours label
        private JLabel makeJLabel() {
            JLabel targetLabel = new JLabel("Target Hours");
            targetLabel.setBounds(10, 50, 80, 25);
            return targetLabel;
        }

        // EFFECTS: creates target hours text field
        private void makeTargetText(Goal goal) {
            targetText = new JTextField(20);
            targetText.setBounds(100, 50, 165, 25);
            targetText.setText(String.valueOf(goal.getTargetHours()));
        }

        // EFFECTS: creates name text field
        private void makeNameText(Goal goal) {
            nameText = new JTextField(20);
            nameText.setBounds(100, 20, 165, 25);
            nameText.setText(goal.getName());
        }

        public Goal getGoal() {
            return goal;
        }

        // EFFECTS: creates progress bar
        public JProgressBar createProgressBar(Goal goal) {
            JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
            progressBar.setBounds(100, 110, 165, 25);
            progressBar.setForeground(new Color(183, 92, 255));
            progressBar.setValue((int) goal.getProgress());
            progressBar.setStringPainted(true);
            return progressBar;
        }

        // EFFECTS: creates name label
        private JLabel nameLabel() {
            JLabel nameLabel = new JLabel("Goal Name");
            nameLabel.setBounds(10, 20, 80, 25);

            return nameLabel;
        }

        public void setGoal(Goal goal) {
            this.goal = goal;
        }

        public GoalTracker getGoalTracker() {
            return goalTracker;
        }



        // MODIFIES: this
        // EFFECTS: gets index from selection and sets the new goal to details inputted by user
        @Override
        public void actionPerformed(ActionEvent e) {

            Goal newGoal = new Goal(nameText.getText(), Integer.parseInt(currentText.getText()),
                    Integer.parseInt(targetText.getText()));

            this.goal = newGoal;


            this.goal.setName(nameText.getText());
            this.goal.setTargetHours(Integer.parseInt(targetText.getText()));
            this.goal.setCurrentHours(Integer.parseInt(currentText.getText()));

            System.out.println(goal.getName());
            System.out.println(goal.getCurrentHours());
            System.out.println(goal.getTargetHours());
            System.out.println(goal.getProgress());

            goalTracker.getGoalList().set(testList.getSelectedIndex(), newGoal);
            listModel.set(testList.getSelectedIndex(), newGoal.getName());

            frame.dispose();

        }

    }


}



