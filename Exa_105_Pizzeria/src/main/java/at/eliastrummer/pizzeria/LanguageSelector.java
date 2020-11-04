/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.pizzeria;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author root
 */
public class LanguageSelector {
    private static final int DEFAULT_LANG = 0; //Deutsch = 0, Englisch = 1
    private static final Map<String, String[]> TRANSLATIONS;
    
    static {
        TRANSLATIONS = new HashMap<String, String[]>() {
            {
                put("pizza-selection", new String[]{ "Pizza-Auswahl", "Pizza selection" });
                put("cheese", new String[]{ "Käse", "cheese" });
                put("ham", new String[]{ "Schinken", "ham" });
                put("sauce", new String[]{ "Tomatensauce", "tomato sauce" });
                put("salami", new String[]{ "Salami und Pepperoni", "salami" });
                put("pineapple", new String[]{ "Ananas", "pineapple" });
                put("order", new String[]{ "Bestellen", "Order" });
                put("deliver-to", new String[]{ "Lieferadresse", "Delivery address" });
                put("back", new String[]{ "Zurück", "Back" });
                put("price", new String[]{ "Preis", "Price" });
                put("amount", new String[]{ "Stück", "Amount" });
                put("overall", new String[]{ "Gesamt", "Overall" });
                put("overview", new String[]{ "Bestellungsübersicht", "Order overview" });
            }
        };
    }
    
    public static String getTranslation(HttpServletRequest req, HttpServletResponse res, String translationKey) {        
        int language;

        try {
            String lang = ((Cookie) Arrays.stream(req.getCookies()).filter(c -> c.getName().toLowerCase().equals("lang")).toArray()[0]).getValue();
            language = Integer.parseInt(lang);
        }catch(Exception e) {
            language = DEFAULT_LANG;
            res.addCookie(new Cookie("lang", DEFAULT_LANG + ""));
        }
        
        if(language < 0 || language > TRANSLATIONS.size()) {
            language = DEFAULT_LANG;
            res.addCookie(new Cookie("lang", DEFAULT_LANG + ""));
        }

        return TRANSLATIONS.get(translationKey)[language];
    }
    
    public static int getLanguage(HttpServletRequest req) {
        for(Cookie cookie : req.getCookies()) {
            if(cookie.getName().equals("lang")){
                return Integer.parseInt(cookie.getValue());
            }
        }
            
        return DEFAULT_LANG;
    }
}
