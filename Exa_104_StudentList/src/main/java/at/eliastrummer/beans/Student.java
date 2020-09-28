/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Student {
 
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-M-d");
 
    private String firstname;
    private String lastname;
    private int catNo;
    private String className;
    private Gender gender;
    private LocalDate birthdate;
 
    public Student(String firstname, String lastname, int catNo, String className, Gender gender, LocalDate birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.catNo = catNo;
        this.className = className;
        this.gender = gender;
        this.birthdate = birthdate;
    }
    
    public Student(String line, Map<String, Integer> values) {
        // 4DHIF;KIRSCHNER;Alexander;m;2001-11-04
        String[] tokens = line.split(";");
        this.className = tokens[0];
        this.lastname = tokens[1];
        this.firstname = tokens[2];
        this.gender = Gender.fromString(tokens[3].toUpperCase());
        this.birthdate = LocalDate.parse(tokens[4], DTF);
 
        Integer current = values.get(className);
        if (current != null) {
            current++;
            values.put(className, current);
        }
        else {
            values.put(className, 1);   
        }
 
        this.catNo = values.get(className);
    }
 
    public String getFirstname() {
        return firstname;
    }
 
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
 
    public String getLastname() {
        return lastname;
    }
 
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
 
 
 
    public int getCatNo() {
        return catNo;
    }
 
    public void setCatNo(int catNo) {
        this.catNo = catNo;
    }
 
    public String getClassName() {
        return className;
    }
 
    public void setClassName(String className) {
        this.className = className;
    }
 
    public Gender getGender() {
        return gender;
    }
 
    public void setGender(Gender gender) {
        this.gender = gender;
    }
 
    public LocalDate getBirthdate() {
        return birthdate;
    }
 
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
 
enum Gender {
    MALE,
    FEMALE;
 
    @Override
    public String toString() {
        switch (this) {
            case MALE:
                return "M";
            case FEMALE:
                return "W";
        }
        return null;
    }
 
    public static Gender fromString(String s) {
        switch(s) {
            case "M": 
                return Gender.MALE;
            case "W":
                return Gender.FEMALE;
            default: 
                return null;
        }
    }
}