package at.eliastrummer.beans;

import java.util.List;

public class LessonsRow {
    private List<Lesson> lessons;

    public LessonsRow(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
