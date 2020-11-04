package at.eliastrummer.beans;

public enum DaysOfWeek {
    MONDAY(1, "Montag"),
    TUESDAY(2, "Dienstag"),
    WEDNESDAY(3, "Mittwoch"),
    THURSDAY(4, "Donnerstag"),
    FRIADY(5, "Freitag");
    
    private final int id;
    private String name;
    
    private DaysOfWeek(final int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
