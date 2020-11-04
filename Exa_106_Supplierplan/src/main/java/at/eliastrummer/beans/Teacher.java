package at.eliastrummer.beans;

public class Teacher {
    private String abbreviation;

    public Teacher(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    
    public Teacher() { }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
