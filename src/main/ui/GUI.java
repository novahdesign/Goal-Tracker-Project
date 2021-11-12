package ui;

import sun.reflect.generics.repository.FieldRepository;

import javax.swing.*;
import java.awt.*;

public class GUI {

    public GUI() {

        // construct the JFrame Object
        JFrame frame = new JFrame();

        // construct the JButton
        JButton button = new JButton("Welcome to GoalTracker. Click Here to Enter");

        JPanel panel = new JPanel();
        // set border of the panel
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setLayout(new GridLayout(1,1));

        Color background = new Color(255, 192, 203);
        panel.setBackground(background);

        panel.add(button);
        button.setBackground(new Color(234, 203, 255));

        // set the panel border layout
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("GUI");
        frame.pack();
        frame.setVisible(true);

        // gradient

//            Color color1 = Color.RED;
//            Color color2 = Color.GREEN;
//
//            int w = panel.getWidth();
//            int h = panel.getHeight();
//
//
//            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//
//
//            GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
//            g2d.setPaint(gp);
//            g2d.fillRect(0, 0, w, h);
//
//            GradientPaint gradient = new GradientPaint(0,0, background, 0, h, 0)
//
    }



}
