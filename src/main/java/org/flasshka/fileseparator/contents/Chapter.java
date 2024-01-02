package org.flasshka.fileseparator.contents;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;

public class Chapter {
    private String name;
    private int lineNumber;
    private int level;
    @JsonIgnore
    private Chapter parent;
    private List<Chapter> subchapters;

    public Chapter() {
    }

    public Chapter(String name, int lineNumber, int level, Chapter parent, List<Chapter> subchapters) {
        if (lineNumber <= 0 || level < 1) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.level = level;
        this.lineNumber = lineNumber;
        this.parent = parent;
        this.subchapters = subchapters;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public List<Chapter> getSubchapters() {
        return subchapters;
    }

    public Chapter getParent() {
        return parent;
    }

    public void add(Chapter subchapter) {
        if (level >= subchapter.level || lineNumber >= subchapter.lineNumber) {
            throw new IllegalArgumentException();
        }

        subchapters.add(subchapter);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("\t".repeat(level - 1))
                .append(name)
                .append(" (")
                .append(lineNumber)
                .append(")")
                .append(System.lineSeparator());


        if(subchapters != null) {
            for (Chapter sub : subchapters) {
                sb.append(sub.toString());
            }
        }


        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Chapter)) {
            return false;
        }

        Chapter chapter = (Chapter) obj;

        return chapter.name.equals(name) && chapter.level == level && chapter.lineNumber == lineNumber
                && (subchapters == null && chapter.subchapters == subchapters
                    || subchapters != null && chapter.subchapters != null
                        && allSubEquals(subchapters, chapter.subchapters));
    }

    private boolean allSubEquals(List<Chapter> subs1, List<Chapter> subs2) {
        if(subs1.size() != subs2.size()) {
            return false;
        }

        for(int i = 0; i < subs1.size(); i++) {
            if(!subs1.get(i).equals(subs2.get(i))) {
                return false;
            }
        }

        return true;
    }
}
