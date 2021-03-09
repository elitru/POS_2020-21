package at.eliastrummer.utils;

import java.util.HashMap;
import java.util.Map;

public class TranslationService {
    public static final Map<String, String> EN = new HashMap<>();
    public static final Map<String, String> DE = new HashMap<>();
    
    static {
        EN.put("lang", "Language");
        EN.put("getdata", "Get weather data");
        EN.put("city", "City");
        EN.put("country", "Country");
        EN.put("timezone", "Timezone");
        EN.put("sunrise", "Sunrise");        
        EN.put("sunset", "Sunset");
        EN.put("temp", "Temperature (Cur, Min, Max)");
        EN.put("hum", "Humidity");
        EN.put("pre", "Pressure");
        EN.put("windspe", "Wind speed");
        EN.put("winddir", "Wind Direction");
        EN.put("clouds", "Clouds");
        EN.put("vis", "Visibility");
        EN.put("lastup", "Last update");
        EN.put("cord", "Coordinates (Lo, La)");
        EN.put("info", "Weather info");
        
        DE.put("lang", "Sprache");
        DE.put("getdata", "Wetterdaten abrufen");
        DE.put("city", "Stadt");
        DE.put("country", "Land");
        DE.put("timezone", "Zeitzone");
        DE.put("sunrise", "Sonnenaufgang");        
        DE.put("sunset", "Sonnenuntergang");
        DE.put("temp", "Temperatur (Akt, Min, Max)");
        DE.put("hum", "Luftfeuchtigkeit");
        DE.put("pre", "Luftdruck");
        DE.put("windspe", "Windgeschwindigkeit");
        DE.put("winddir", "Windrichtung");
        DE.put("clouds", "Wolken");
        DE.put("vis", "Sichtbarkeit");
        DE.put("lastup", "Letzte Aktualisierung");
        DE.put("cord", "Koordinaten (L, B)");
        DE.put("info", "Wetterdaten");
    }
    
    public static String translate(String lang, String key) {
        return lang.equalsIgnoreCase("de") ? DE.get(key) : EN.get(key);
    }
}
