package org.flasshka.fileseparator.client.model;

import org.flasshka.fileseparator.client.controller.ClientController;
import org.flasshka.fileseparator.contents.FileContent;
import org.flasshka.fileseparator.json.JsonUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class ClientModel {
    private final static String JSON_RESULT = "json";
    private final static String RESULT_CONTENT = "content";
    private final static String EXTENSION = ".txt";
    private final static String PATH_TO_SAVE_FILES =
            "src" + File.separator + "main" + File.separator + "resources" + File.separator;

    private ClientController controller;
    private File file;

    public void setController(ClientController controller) {
        this.controller = controller;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void sendFile() {
        String json;
        try {
            json = ClientHttpRequest.sendAndGetJson(file.toURI());
        } catch (IOException e) {
            Logger.getGlobal().warning(Arrays.toString(e.getStackTrace()));
            controller.printRequestResult(e.getMessage());
            return;
        }

        controller.printRequestResult(saveResult(json));
    }

    public String saveResult(String json) {
        int number = 0;
        File jsonSaveFile;
        File resSaveFile;

        do {
            number++;
            String endName = "_" + number + EXTENSION;
            jsonSaveFile = new File(PATH_TO_SAVE_FILES + JSON_RESULT + endName);
            resSaveFile = new File(PATH_TO_SAVE_FILES + RESULT_CONTENT + endName);
        } while (jsonSaveFile.exists() || resSaveFile.exists());

        String result;
        try {
            result = JsonUtils.jsonToObj(json, FileContent.class).toString();
        } catch (IOException e) {
            Logger.getGlobal().warning(Arrays.toString(e.getStackTrace()));
            return "Can't parse json";
        }

        writeInFile(resSaveFile, result);
        writeInFile(jsonSaveFile, json);
        return getFilesSavedMessage(jsonSaveFile, resSaveFile);
    }

    private String getFilesSavedMessage(File json, File result) {
        return "Files saves successful!"
                + System.lineSeparator() + json.getAbsolutePath()
                + System.lineSeparator() + result.getAbsolutePath();
    }

    private void writeInFile(File file, String content) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            Logger.getGlobal().warning(Arrays.toString(e.getStackTrace()));
        }
    }
}
