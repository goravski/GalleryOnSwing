package com.vizor.test;

import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {

    public void run() {
        JFrame frame = new JFrame("DT Developer Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(ConstantMy.WIDTH.getConstant(), ConstantMy.HEIGHT.getConstant()));

        JPanel startPanel = new JPanel();
        JButton startButton = new JButton("View gallery");
        startButton.addActionListener(e -> {
            frame.getContentPane().remove(0);
            GridLayoutConstructButtonPanel construct = new GridLayoutConstructButtonPanel();
            construct.textFieldPanelConstruct(frame);
            construct.buttonsPanelConstruct(frame);
            construct.galleryButtonConstruct(frame);
            frame.setVisible(true);
        });
        startPanel.add(startButton);
        frame.getContentPane().add(startPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Main()::run);
    }

}
