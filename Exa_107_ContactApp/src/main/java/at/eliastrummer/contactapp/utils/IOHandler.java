package at.eliastrummer.contactapp.utils;

import at.eliastrummer.contactapp.pojo.Company;
import at.eliastrummer.contactapp.pojo.Contact;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class IOHandler {
    public static List<Contact> loadContactsFromFile(String path) throws FileNotFoundException, IOException {
        List<Company> companies = new ArrayList<>();
        List<Contact> contacts = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(new File(path), new TypeReference<List<Contact>>() { });
        
        for(int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            
            if(!companies.contains(c.getCompany())) {
                companies.add(c.getCompany());
                c.getCompany().setContacts(new ArrayList<Contact>(){{ add(c); }});
                continue;
            }
            
            Company comp = companies.get(companies.indexOf(c.getCompany()));
            c.setCompany(comp);
            comp.getContacts().add(c);
        }
        
        return contacts;
    }
}
