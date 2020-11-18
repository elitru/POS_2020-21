package at.eliastrummer.contactapp.utils;

import at.eliastrummer.contactapp.pojo.Contact;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class IOHandler {
    public static List<Contact> loadContactsFromFile(String path) throws FileNotFoundException, IOException {        
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(new File(path), new TypeReference<List<Contact>>() { });
    }
}
