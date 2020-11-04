package at.eliastrummer.beans;

import java.util.List;

public class Lesson {
    private String subject;
    private List<Teacher> teachers;
    private boolean supplement;

    public Lesson(String subject, List<Teacher> teachers, boolean supplement) {
        this.subject = subject;
        this.teachers = teachers;
        this.supplement = supplement;
    }
    
    public Lesson() { }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public boolean isSupplement() {
        return supplement;
    }

    public void setSupplement(boolean supplement) {
        this.supplement = supplement;
    }
}