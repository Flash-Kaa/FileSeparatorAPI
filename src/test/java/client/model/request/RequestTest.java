package client.model.request;

import client.model.TestObjects;
import client.model.TestParams;
import org.flasshka.fileseparator.client.model.ClientHttpRequest;
import org.flasshka.fileseparator.contents.FileContent;
import org.flasshka.fileseparator.json.JsonUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestTest {
    @Test
    void testRequest() {
        TestParams[] params = new TestParams[]{
                TestObjects.TEST_OBJ_1,
                TestObjects.TEST_OBJ_2,
                TestObjects.TEST_OBJ_3,
                TestObjects.TEST_OBJ_4,
        };

        for (TestParams param : params) {
            try {
                String json = ClientHttpRequest.sendAndGetJson(param.getFile().toURI());
                assertThat(JsonUtils.jsonToObj(json, FileContent.class).toString())
                        .isEqualTo(param.getExpectedResult());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
