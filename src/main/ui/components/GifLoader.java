package ui.components;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class GifLoader {

    public GifLoader() throws MalformedURLException {


        URL url = new URL("http://www.animatedgif.net/welcome/weltrain_e0.gif");
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);

        JFrame f = new JFrame("Animation");
        f.getContentPane().add(label);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);


    }

    public static void main(String[] args) throws MalformedURLException {

        new GifLoader();
    }

}