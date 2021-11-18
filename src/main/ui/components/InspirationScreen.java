package ui.components;

import model.Goal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class InspirationScreen {

    public InspirationScreen() throws IOException  {

        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setBackground(new Color(93, 35, 255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(232, 168, 255));
        frame.add(panel);


        BufferedImage myPicture =
                ImageIO.read(new File("D:\\GIFS\\gaslight-gatekeep-girlboss.jpg"));

        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(50,50,150,150);
        panel.add(picLabel);


        frame.setVisible(true);


    }


    public static void main(String[] args) {
        try {
            new InspirationScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
