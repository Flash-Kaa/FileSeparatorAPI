package json;

import org.flasshka.fileseparator.contents.Chapter;
import org.flasshka.fileseparator.contents.FileContent;
import org.flasshka.fileseparator.json.JsonUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {
    @Test
    void testJson() {
        FileContent[] testObjs = new FileContent[]{
                getTestObj1(),
                getTestObj2(),
                getTestObj3(),
                getTestObj4()
        };

        for (FileContent content : testObjs) {
            String json;
            FileContent copy;
            try {
                json = JsonUtils.objToJson(content);
                copy = JsonUtils.jsonToObj(json, FileContent.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            assertThat(content.toString())
                    .isEqualTo(copy.toString());
            assertThat(content)
                    .isEqualTo(copy);
        }
    }

    private FileContent getTestObj1() {
        FileContent content = new FileContent(new ArrayList<>());
        content.getChapters().add(new Chapter("name1", 1, 1, null, new ArrayList<>()));

        Chapter moreLevelChapter = new Chapter("name2", 2, 1, null, new ArrayList<>());
        content.getChapters().add(moreLevelChapter);
        moreLevelChapter.add(new Chapter("name3", 5, 2, moreLevelChapter, new ArrayList<>()));
        moreLevelChapter.add(new Chapter("name4", 7, 2, moreLevelChapter, new ArrayList<>()));
        moreLevelChapter.add(new Chapter("name5", 8, 2, moreLevelChapter, new ArrayList<>()));
        moreLevelChapter.add(new Chapter("name6", 15, 2, moreLevelChapter, new ArrayList<>()));
        moreLevelChapter.getSubchapters().get(0)
                .add(new Chapter("name6", 17, 3, moreLevelChapter.getSubchapters().get(0), new ArrayList<>()));

        content.getChapters().add(new Chapter("name8", 17, 1, null, new ArrayList<>()));
        content.getChapters().add(new Chapter("name9", 19, 1, null, new ArrayList<>()));
        content.getChapters().add(new Chapter("name10", 20, 1, null, new ArrayList<>()));
        return content;
    }

    private FileContent getTestObj2() {
        return new FileContent();
    }

    private FileContent getTestObj3() {
        FileContent content = new FileContent(new ArrayList<>());
        content.getChapters().add(null);
        content.getChapters().add(null);
        content.getChapters().add(null);
        content.getChapters().add(null);
        content.getChapters().add(null);
        return content;
    }

    private FileContent getTestObj4() {
        FileContent content = new FileContent(new ArrayList<>());
        content.getChapters().add(new Chapter("name1", 1, 1, null, new ArrayList<>()));
        content.getChapters().add(null);
        content.getChapters().add(new Chapter("name2", 5, 1, null, new ArrayList<>()));
        content.getChapters().add(null);
        content.getChapters().add(null);
        return content;
    }
}
