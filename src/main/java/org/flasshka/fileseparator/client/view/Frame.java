package org.flasshka.fileseparator.client.view;

import org.flasshka.fileseparator.client.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Frame {
    private final static Font GENERAL_FONT = new Font("Roboto", Font.BOLD, 36);

    private ClientController controller;
    private final JFrame frame = new JFrame();
    private final JLabel fileName = new JLabel("");
    private final JButton parseButton = new JButton("parse");
    private final JTextArea result = new JTextArea("");

    public void setFile(File file) {
        fileName.setText(file.getAbsolutePath());
        parseButton.setEnabled(true);
        frame.repaint();
    }

    public void printResult(String res) {
        result.setText(res);
    }

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public void draw() {
        frame.setVisible(true);
        frame.setBounds(0, 0, 1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // initialization button for begin http request
        parseButton.setEnabled(false);
        parseButton.addActionListener(e -> controller.sendFile());

        // initialization button for open fileChooser
        JButton showFile = new JButton("choose file");
        showFile.addActionListener(new ChooseFileActionListener(frame, controller));

        // installing fonts
        showFile.setFont(GENERAL_FONT);
        fileName.setFont(GENERAL_FONT);
        parseButton.setFont(GENERAL_FONT);
        result.setFont(GENERAL_FONT);

        // Creating layout
        SpringLayout sl = new SpringLayout();
        sl.putConstraint(SpringLayout.NORTH, showFile, 80, SpringLayout.NORTH, frame);
        sl.putConstraint(SpringLayout.WEST, showFile, 100, SpringLayout.WEST, frame);
        sl.putConstraint(SpringLayout.NORTH, fileName, 170, SpringLayout.NORTH, frame);
        sl.putConstraint(SpringLayout.WEST, fileName, 150, SpringLayout.WEST, frame);
        sl.putConstraint(SpringLayout.NORTH, parseButton, 280, SpringLayout.NORTH, frame);
        sl.putConstraint(SpringLayout.WEST, parseButton, 100, SpringLayout.WEST, frame);
        sl.putConstraint(SpringLayout.NORTH, result, 400, SpringLayout.NORTH, frame);
        sl.putConstraint(SpringLayout.WEST, result, 100, SpringLayout.WEST, frame);

        // adding components to form
        frame.setLayout(sl);
        frame.add(showFile);
        frame.add(fileName);
        frame.add(parseButton);
        frame.add(result);
    }
}
