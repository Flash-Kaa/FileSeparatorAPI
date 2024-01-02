package org.flasshka.fileseparator.api.v1.controller;

import org.flasshka.fileseparator.contents.FileContent;
import org.flasshka.fileseparator.api.v1.FileSeparator;
import org.flasshka.fileseparator.json.JsonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {
    @PostMapping("/api/v1")
    public ResponseEntity<byte[]> separate(@RequestParam("file") MultipartFile file) {
        try {
            FileContent content = FileSeparator.getContent(file);

            return ResponseEntity.ok(JsonUtils.objToJson(content).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
