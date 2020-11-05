package at.eliastrummer.utils;

import at.eliastrummer.beans.Lesson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class IOHandler {
    public static List<Lesson> loadLessons(String pathToFile) throws FileNotFoundException {
        return new BufferedReader(new FileReader(pathToFile))
                .lines()
                .skip(1)
                .map(Mapper::toLesson)
                .collect(Collectors.toList());
    }
    
    public static String loadClassName(String pathToFile) throws FileNotFoundException {
        return (String) new BufferedReader(new FileReader(pathToFile))
                .lines()
                .toArray()[0];
    }
}
