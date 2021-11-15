package ui;

import sun.reflect.generics.repository.FieldRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private int clicks = 0;
    private JLabel label = new JLabel("Progress hours: 0    ");


    public GUI() {

        // construct the JFrame Object
        JFrame frame = new JFrame();

        // construct the
        JButton inspiration = new JButton("Inspiration");
        JButton button = new JButton("Add Goal");
        JButton removeGoal = new JButton("Remove Goal");
        JButton viewGoals = new JButton("View Goals");
        JButton addTime = new JButton("Add Time");
        JButton saveAndQuit = new JButton("Save & Quit");

        button.addActionListener(this);

        JPanel panel = new JPanel();
        // set border of the panel
        panel.setBorder(BorderFactory.createEmptyBorder(100, 50, 100, 50));
        panel.setLayout(new GridLayout(1,1));
        panel.add(label);

        Color background = new Color(255, 123, 211);
        panel.setBackground(background);

        panel.add(button);
        button.setBackground(new Color(234, 203, 255));

        panel.add(inspiration);
        button.setBackground(new Color(234, 203, 255));

        panel.add(removeGoal);
        panel.add(viewGoals);
        panel.add(addTime);
        panel.add(saveAndQuit);

        // set the panel border layout
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        clicks++;
        label.setText("Progress hours: " + clicks);

    }
}
