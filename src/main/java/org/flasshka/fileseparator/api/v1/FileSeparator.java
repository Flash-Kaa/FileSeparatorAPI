package org.flasshka.fileseparator.api.v1;

import org.flasshka.fileseparator.contents.Chapter;
import org.flasshka.fileseparator.contents.FileContent;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileSeparator {
    public static FileContent getContent(MultipartFile file) throws IOException {
        String[] lines = new String(file.getBytes(), StandardCharsets.UTF_8)
                .replaceAll("<br>", System.lineSeparator())
                .split(System.lineSeparator());

        FileContent content = new FileContent(new ArrayList<>());
        Chapter last = null;

        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            Pair<Integer, Integer> p = calcLevelAndSeparateIndex(lines[lineIndex]);
            int level = p.getLeft();
            int ind = p.getRight();

            if (level > 0) {
                Chapter parent = last;

                while (parent != null && parent.getLevel() >= level) {
                    parent = parent.getParent();
                }

                last = new Chapter(
                        lines[lineIndex].substring(ind - 1),
                        lineIndex + 1, level, parent, new ArrayList<>()
                );
                addChapter(last, content);
            }
        }

        return content;
    }

    private static void addChapter(Chapter newChapter, FileContent content) {
        Chapter parent = newChapter.getParent();

        if (parent != null) {
            parent.add(newChapter);
        } else {
            content.getChapters().add(newChapter);
        }
    }

    private static Pair<Integer, Integer> calcLevelAndSeparateIndex(String line) {
        int level = 0;
        int separateIndex = 0;

        while (separateIndex < line.length()) {
            char symbol = line.charAt(separateIndex++);

            if (symbol == '#') {
                level++;
            } else if (symbol != ' ' && symbol != '\t') {
                break;
            }
        }

        return new Pair<>(level, separateIndex);
    }

    private static class Pair<L, R> {
        private final L left;
        private final R right;

        public Pair(L left, R right) {
            this.right = right;
            this.left = left;
        }

        public L getLeft() {
            return left;
        }

        public R getRight() {
            return right;
        }
    }
}