package client.model;

import java.io.File;

public class TestParams {
    private File file;
    private String expectedResult;

    public TestParams(File file, String expectedResult) {
        this.file = file;
        this.expectedResult = expectedResult;
    }

    public File getFile() {
        return file;
    }

    public String getExpectedResult() {
        return expectedResult;
    }
}
