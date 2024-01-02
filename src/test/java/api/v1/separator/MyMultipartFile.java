package api.v1.separator;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MyMultipartFile implements MultipartFile {
    private final byte[] content;

    public MyMultipartFile(byte[] content) {
        this.content = content;
    }

    @Override
    public String getName() {
        return "name";
    }

    @Override
    public String getOriginalFilename() {
        return "originalFilename";
    }

    @Override
    public String getContentType() {
        return "none";
    }

    @Override
    public boolean isEmpty() {
        return content.length == 0;
    }

    @Override
    public long getSize() {
        return content.length;
    }

    @Override
    public byte[] getBytes() {
        return content;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(content);
    }

    @Override
    public void transferTo(File dest) throws IOException, IllegalStateException {
        Files.copy(new ByteArrayInputStream(content), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
