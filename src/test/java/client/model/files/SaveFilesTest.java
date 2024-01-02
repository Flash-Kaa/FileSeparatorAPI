package client.model.files;

import client.model.TestObjects;
import client.model.TestParams;
import org.flasshka.fileseparator.client.model.ClientHttpRequest;
import org.flasshka.fileseparator.client.model.ClientModel;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class SaveFilesTest {
    @Test
    void testSave() {
        File file = new File("src/main/resources");
        for (File f : Objects.requireNonNull(file.listFiles())) {
            f.delete();
        }

        TestParams[] params = new TestParams[]{
                TestObjects.TEST_OBJ_1,
                TestObjects.TEST_OBJ_2,
                TestObjects.TEST_OBJ_3,
                TestObjects.TEST_OBJ_4,
        };

        ClientModel model = new ClientModel();

        for (int i = 1; i <= params.length; i++) {
            String json;
            try {
                json = ClientHttpRequest.sendAndGetJson(params[i - 1].getFile().toURI());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            model.saveResult(json);

            File resultFile = new File("src/main/resources/content_" + i + ".txt");
            File jsonFile = new File("src/main/resources/json_" + i + ".txt");

            assertThat(jsonFile.exists())
                    .isEqualTo(resultFile.exists())
                    .isTrue();

            String content;
            try {
                content = new String(Files.readAllBytes(resultFile.toPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            assertThat(content)
                    .isEqualTo(params[i - 1].getExpectedResult());
        }
    }
}