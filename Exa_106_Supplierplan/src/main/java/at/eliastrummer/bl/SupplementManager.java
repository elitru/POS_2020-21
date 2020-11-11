package at.eliastrummer.bl;

import at.eliastrummer.beans.Lesson;
import at.eliastrummer.beans.LessonsRow;
import at.eliastrummer.beans.Teacher;
import java.util.List;

public class SupplementManager {
    public static boolean setSupplement(List<LessonsRow> rows, List<Teacher> teachers, int dayOfWeekId, int lesson, String subject) {
        LessonsRow row = rows.get(lesson - 1);
        Lesson ls = row.getLessons().get(dayOfWeekId - 1);
        
         if(ls.getSubject() == null && ls.getTeachers() == null) {
            return false;
        }
        
        ls.setTeachers(teachers);
        ls.setSubject(subject);
        ls.setSupplement(true);
        return true;
    }
}
