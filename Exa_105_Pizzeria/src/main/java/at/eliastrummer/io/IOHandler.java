/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.io;

import at.eliastrummer.beans.Pizza;
import at.eliastrummer.utils.Mapper;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author root
 */
public class IOHandler {
    public static List<Pizza> load(String path) throws FileNotFoundException {
        return new BufferedReader(new FileReader(path))
                .lines()
                .skip(1)
                .map(Mapper::toPizza)
                .collect(Collectors.toList());
    }
}
