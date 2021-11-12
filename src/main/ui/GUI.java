package ui;

import sun.reflect.generics.repository.FieldRepository;

import javax.swing.*;
import java.awt.*;

public class GUI {

    public GUI() {

        // construct the JFrame Object
        JFrame frame = new JFrame();

        // construct the JButton
        JButton button = new JButton("Click");

        JPanel panel = new JPanel();
        // set border of the panel
        panel.setBorder(BorderFactory.createEmptyBorder(500, 500, 500, 500));
        panel.setLayout(new GridLayout(1,1));

        Color background = new Color(255, 192, 203);
        panel.setBackground(background);

        panel.add(button);
        button.setBackground(new Color(147, 0, 255));

        // set the panel border layout
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);

    }


}
