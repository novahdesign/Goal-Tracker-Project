package ui.components;

import model.Goal;
import model.GoalTracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoalDetailScreen implements ActionListener {

    private Goal goal;
    private GoalTracker goalTracker;
    private JFrame frame;

    public JTextField getNameText() {
        return nameText;
    }

    public void setNameText(JTextField nameText) {
        this.nameText = nameText;
    }

    public JTextField getTargetText() {
        return targetText;
    }

    public void setTargetText(JTextField targetText) {
        this.targetText = targetText;
    }

    public JTextField getCurrentText() {
        return currentText;
    }

    public void setCurrentText(JTextField currentText) {
        this.currentText = currentText;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    private JTextField nameText;
    private JTextField targetText;
    private JTextField currentText;

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    private JProgressBar progressBar;

    private Goal newGoal;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public GoalDetailScreen(Goal goal) {

        this.goal = goal;

        JPanel panel = new JPanel();
        frame = new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);
        Color background = new Color(255, 123, 211);
        panel.setBackground(background);

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

    public Goal getNewGoal() {
        Goal newGoal = new Goal(nameText.getName(), Integer.parseInt(currentText.getText()),
                Integer.parseInt(targetText.getText()));
        return newGoal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.goal = getNewGoal();

        this.goal.setName(nameText.getText());
        this.goal.setTargetHours(Integer.parseInt(targetText.getText()));
        this.goal.setCurrentHours(Integer.parseInt(currentText.getText()));

        System.out.println(goal.getName());
        System.out.println(goal.getCurrentHours());
        System.out.println(goal.getTargetHours());
        System.out.println(goal.getProgress());

        // System.out.println(goalTracker.getGoalList());

        frame.dispose();

    }
}
