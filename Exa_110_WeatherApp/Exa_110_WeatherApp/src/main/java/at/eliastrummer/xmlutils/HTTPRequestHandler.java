package at.eliastrummer.xmlutils;

import at.eliastrummer.pojo.Location;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;

public class HTTPRequestHandler {
    
    private static final String API_KEY = "0127526875de040abe225ee6dd1c0374";
    private static final String API_LINK = "http://api.openweathermap.org/data/2.5/weather?q=%s&lang=%s&appid=" + API_KEY + "&mode=xml&units=metric";
    
    public static Location requestLocationInfo(String locationName, String language) {
        Location loc = JAXB.unmarshal(String.format(API_LINK, encodeUrlParam(locationName), language), Location.class);
        return loc;
    }
    
    private static String encodeUrlParam(String param) {
        try {
            return URLEncoder.encode(param, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HTTPRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            return param;
        }
    }
}
