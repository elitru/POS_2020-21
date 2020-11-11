package at.eliastrummer.utils;

import at.eliastrummer.beans.Lesson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class IOHandler {
    public static List<String> loadFile(String pathToFile) throws FileNotFoundException {
         return new BufferedReader(new FileReader(pathToFile))
                .lines()
                .collect(Collectors.toList());
    }
    
    public static List<Lesson> loadLessons(List<String> inputFileInLines) throws FileNotFoundException {
        return inputFileInLines
                .stream()
                .skip(1)
                .map(Mapper::toLesson)
                .collect(Collectors.toList());
    }
}
