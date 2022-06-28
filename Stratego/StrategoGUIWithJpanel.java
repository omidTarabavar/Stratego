package Stratego;

import javafx.scene.layout.Background;

import javax.swing.*;
import java.awt.*;

public class StrategoGUIWithJpanel {
    public static void main(String[] args) {

        JPanel redPanel = new JPanel();
        redPanel.setBackground(new Color(255,0,0));
        redPanel.setBounds(0,0,300,350);
        ImageIcon imageIcon = new ImageIcon("bomb.jpg");
        JLabel pic = new JLabel(imageIcon);
        redPanel.add(pic);


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(750,750);
        frame.setVisible(true);
        frame.add(redPanel);
    }
}
