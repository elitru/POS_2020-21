/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.utils;

import at.eliastrummer.beans.Pizza;

/**
 *
 * @author root
 */
public class Mapper {
    public static Pizza toPizza(String param) {
        String[] parts = param.split(";");
        //Id;Name;Preis;Beschreibung;Image
        return new Pizza(Integer.parseInt(parts[0]), parts[1], parts[3], Float.parseFloat(parts[2]), parts[4]);
    }
}
