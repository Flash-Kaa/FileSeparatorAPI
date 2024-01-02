package org.flasshka.fileseparator.client;

import org.flasshka.fileseparator.client.controller.ClientController;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientController controller = new ClientController();
        });
    }

}
