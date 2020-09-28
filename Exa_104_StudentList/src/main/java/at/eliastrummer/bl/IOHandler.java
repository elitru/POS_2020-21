/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.bl;

import at.eliastrummer.beans.Student;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author root
 */
public class IOHandler {
    public static final String STUDENTS_FILE_NAME = "students.csv";
    
    public static List<Student> readStudents(String relativePath) throws FileNotFoundException {
        Map<String, Integer> catNo = new HashMap<>();
        
        return new BufferedReader(new FileReader(relativePath + STUDENTS_FILE_NAME))
                .lines()
                .skip(1)
                .map(line -> {
                    return new Student(line, catNo);
                })
                .collect(Collectors.toList());
    }
}
