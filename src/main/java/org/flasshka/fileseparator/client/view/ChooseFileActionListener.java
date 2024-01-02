package org.flasshka.fileseparator.client.view;

import org.flasshka.fileseparator.client.controller.ClientController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ChooseFileActionListener implements ActionListener {
    private final static Font SELECT_FILE_FONT = new Font("Times New Roman", Font.BOLD, 22);

    private final JFrame frame;
    private final ClientController controller;

    public ChooseFileActionListener(JFrame frame, ClientController controller) {
        this.frame = frame;
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        setFont(fileChooser.getComponents());
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.showOpenDialog(frame);

        File file = fileChooser.getSelectedFile();
        controller.setFile(file);
    }

    private void setFont(Component[] components)
    {
        for (Component component : components) {
            component.setFont(SELECT_FILE_FONT);

            if (component instanceof Container) {
                setFont(((Container) component).getComponents());
            }
        }
    }
}
