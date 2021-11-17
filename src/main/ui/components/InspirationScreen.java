package ui.components;

import model.Goal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class InspirationScreen {

    public InspirationScreen() throws IOException  {

        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        BufferedImage myPicture =
                ImageIO.read(new File("D:\\GIFS\\gaslight-gatekeep-girlboss.jpg"));



        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        frame.add(picLabel);


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
