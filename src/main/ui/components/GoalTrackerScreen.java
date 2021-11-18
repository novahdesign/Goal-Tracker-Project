package ui.components;

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
//
//        listModel.addElement(testGoal.getName());
//        listModel.addElement(testGoal.getProgress());
//
//        listModel.addElement("John Smith");
//        listModel.addElement("Kathy Green");

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

    private class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            Goal selectedGoal;

            if (e.getValueIsAdjusting() == false) {

                if (testList.getSelectedIndex() == -1) {
                    System.out.println("select something!");
                } else {
                    //Selection, enable the fire button.
                    int selectedIndex = testList.getSelectedIndex();
                    selectedGoal = goalTracker.getGoalList().get(selectedIndex);
                    new GoalDetailScreen(selectedGoal);

                    testList.getSelectedValue();
                    System.out.println(testList.getSelectedValue());



                      //      fireButton.setEnabled(true);
                }
            }
//            //  JTextArea output;
//            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
//            int firstIndex = e.get();
//
//            System.out.println(listModel.get(firstIndex));

//            int lastIndex = e.getLastIndex();
//            boolean isAdjusting = e.getValueIsAdjusting();
//            System.out.println("Event for indexes "
//                    + firstIndex + " - " + lastIndex
//                    + "; isAdjusting is " + isAdjusting
//                    + "; selected indexes:");

//            if (lsm.isSelectionEmpty()) {
//                System.out.println("<none>");
//            } else {
//                // Find out which indexes are selected.
////                int minIndex = lsm.getMinSelectionIndex();
////                int maxIndex = lsm.getMaxSelectionIndex();
////                for (int i = minIndex; i <= maxIndex; i++) {
////                    if (lsm.isSelectedIndex(i)) {
//                System.out.println((" " + firstIndex));
        }
    }


    private JButton getSaveButton() {
        JButton saveButton = new JButton("Save");
        saveButton.setBounds(40, 210, 165, 25);
        saveButton.setActionCommand("saveButton");
        saveButton.addActionListener(this);
        return saveButton;
    }

    private JButton getEditGoalButton() {
        JButton editGoalButton = new JButton("Edit/View Goal");
        editGoalButton.setBounds(40, 180, 165, 25);
        editGoalButton.setActionCommand("editGoalButton");
        editGoalButton.addActionListener(this);
        return editGoalButton;
    }

    private JButton getAddGoalButton() {
        JButton addGoalButton = new JButton("Add Goal");
        addGoalButton.setBounds(40, 150, 165, 25);
        addGoalButton.addActionListener(this);
        addGoalButton.setActionCommand("addGoal");
        return addGoalButton;
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
        loadButton.setActionCommand("loadButton");
        loadButton.addActionListener(this);
        return loadButton;
    }

    private JLabel getWelcomeGif() throws MalformedURLException {

        URL url = new URL("http://www.animatedgif.net/welcome/weltrain_e0.gif");
        Icon icon = new ImageIcon(url);
        JLabel welcomeGif = new JLabel(icon);
        welcomeGif.setBounds(5, 5, 490, 80);
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

        //    GoalTracker goalTracker = new GoalTracker();

        //   Goal goal = new Goal("test", 20, 100);

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

    private void doEditViewGoal() {

    }

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

    private void doAddGoal() {
        Goal g = new Goal("Enter", 0, 100);
//        new GoalDetailScreen(g);
        new GoalDetailScreenAdd(g);
//        goalTracker.addGoal(g);
//        listModel.addElement(g.getName());
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

        public GoalDetailScreenAdd(Goal goal) {

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

        private JButton makeSaveButton(JPanel panel) {
            JButton saveButton = new JButton("Save and Back");
            saveButton.setBounds(50, 140, 165, 25);
            panel.add(saveButton);
            return saveButton;
        }

        private void makeProgressLabel(JPanel panel) {
            JLabel progressLabel = new JLabel("Progress");
            progressLabel.setBounds(10, 110, 80, 25);
            panel.add(progressLabel);
        }

        private void makeCurrentHoursText(Goal goal, JPanel panel) {
            currentText = new JTextField(20);
            currentText.setBounds(100, 80, 165, 25);
            currentText.setText(String.valueOf(goal.getCurrentHours()));
            panel.add(currentText);
        }

        private JLabel makeCurrentLabel() {
            JLabel currentLabel = new JLabel("Current Hours");
            currentLabel.setBounds(10, 80, 80, 25);
            return currentLabel;
        }

        private JLabel makeJLabel() {
            JLabel targetLabel = new JLabel("Target Hours");
            targetLabel.setBounds(10, 50, 80, 25);
            return targetLabel;
        }

        private void makeTargetText(Goal goal) {
            targetText = new JTextField(20);
            targetText.setBounds(100, 50, 165, 25);
            targetText.setText(String.valueOf(goal.getTargetHours()));
        }

        private void makeNameText(Goal goal) {
            nameText = new JTextField(20);
            nameText.setBounds(100, 20, 165, 25);
            nameText.setText(goal.getName());
        }

        public Goal getGoal() {
            return goal;
        }

        public JProgressBar createProgressBar(Goal goal) {
            JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL);
            progressBar.setBounds(100, 110, 165, 25);
            progressBar.setValue((int) goal.getProgress());
            progressBar.setStringPainted(true);
            return progressBar;
        }

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

        public Goal getNewGoal() {
            Goal newGoal = new Goal(nameText.getText(), Integer.parseInt(currentText.getText()),
                    Integer.parseInt(targetText.getText()));
            return newGoal;
        }

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
//
//    public static void main(String[] args) throws MalformedURLException {
//        new GoalTrackerScreen();
//    }

//    public void addGoalButton() {
//        JButton addGoal = new JButton("Add Goal");
//        addGoal.setBounds(40, 90, 165, 25);
//        panel.add(addGoal);
//
//        addGoal.addActionListener(this);
//    }




