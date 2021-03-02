/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Elias
 */
public class DataAdapter extends XmlAdapter<String, LocalDateTime>{
    private static DateTimeFormatter DTF = DateTimeFormatter.RFC_1123_DATE_TIME;

    @Override
    public LocalDateTime unmarshal(String vt) throws Exception {
        return LocalDateTime.parse(vt, DTF);
    }

    @Override
    public String marshal(LocalDateTime bt) throws Exception {
        return DTF.format(bt);
    }

}
