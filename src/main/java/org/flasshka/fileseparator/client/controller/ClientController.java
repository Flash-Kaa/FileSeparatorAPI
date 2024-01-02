package org.flasshka.fileseparator.client.controller;

import org.flasshka.fileseparator.client.model.ClientModel;
import org.flasshka.fileseparator.client.view.Frame;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientController {
    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);
    private final Frame frame;
    private final ClientModel model;

    public ClientController() {
        model = new ClientModel();
        model.setController(this);

        frame = new Frame();
        frame.setController(this);
        frame.draw();
    }

    public void sendFile() {
        threadPool.execute(model::sendFile);
    }

    public void setFile(File file) {
        if (file != null) {
            model.setFile(file);
            frame.setFile(file);
        }
    }

    public void printRequestResult(String result) {
        frame.printResult(result);
    }
}
