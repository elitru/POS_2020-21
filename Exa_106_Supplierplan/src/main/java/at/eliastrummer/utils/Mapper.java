package at.eliastrummer.utils;

import at.eliastrummer.beans.DaysOfWeek;
import at.eliastrummer.beans.Lesson;
import at.eliastrummer.beans.LessonsRow;
import at.eliastrummer.beans.Teacher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Mapper {
    public static List<Teacher> toTeachers(String param) {        
        return Arrays.stream(param.split(","))
                .map(line -> new Teacher(line))
                .collect(Collectors.toList());
    }
    
    public static Lesson toLesson(String param) {
        if(param.equals("-;-")) {
            return new Lesson(null, null, false);
        }
        String[] splitted = param.split(";");
        return new Lesson(splitted[0], toTeachers(splitted[1]), false);
    }
    
    public static List<LessonsRow> rowifyLessons(List<Lesson> lessons) {
        List<LessonsRow> result = new ArrayList<>();
        
        for(int i = 0; i < lessons.size(); i += 5) {
            LessonsRow row = new LessonsRow(new ArrayList<>());
            
            for(int i2 = i; i2 < (i + 5); i2++) {
                row.getLessons().add(lessons.get(i2));
            }
            
            result.add(row);
        }
        
        return result;
    }
}
