package org.flasshka.fileseparator.contents;

import java.util.List;

public class FileContent {
    private List<Chapter> chapters;

    public FileContent() {
    }

    public FileContent(List<Chapter> chapters) {
        if (chapters == null) {
            throw new IllegalArgumentException();
        }

        this.chapters = chapters;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    @Override
    public String toString() {
        if(chapters == null) {
            return "null";
        }

        StringBuilder sb = new StringBuilder();

        for (Chapter l : chapters) {
            sb.append(l);
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof FileContent)) {
            return false;
        }

        FileContent fc = (FileContent) obj;

        if(fc.getChapters() == null) {
            return chapters == null;
        }

        return fc.getChapters().equals(chapters);
    }
}