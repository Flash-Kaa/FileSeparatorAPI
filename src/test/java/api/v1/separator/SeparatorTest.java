package api.v1.separator;

import client.model.TestObjects;
import client.model.TestParams;
import org.flasshka.fileseparator.api.v1.FileSeparator;
import org.flasshka.fileseparator.contents.FileContent;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;

public class SeparatorTest {
    @Test
    void testSeparator() {
        TestParams[] params = new TestParams[]{
                TestObjects.TEST_OBJ_1,
                TestObjects.TEST_OBJ_2,
                TestObjects.TEST_OBJ_3,
                TestObjects.TEST_OBJ_4,
        };

        for (TestParams param : params) {
            FileContent fc;
            try {
                MyMultipartFile file = new MyMultipartFile(Files.readAllBytes(param.getFile().toPath()));
                fc = FileSeparator.getContent(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            assertThat(fc.toString())
                    .isEqualTo(param.getExpectedResult());
        }
    }
}
