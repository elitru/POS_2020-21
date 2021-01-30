package at.eliastrummer.utils;

import at.eliastrummer.beans.Window;
import org.w3c.dom.Element;

public class XMLMapper {
    
    public static Window toWindow(Element el) {
        int xPos = Integer.parseInt(el.getElementsByTagName("xpos").item(0).getTextContent());
        int yPos = Integer.parseInt(el.getElementsByTagName("ypos").item(0).getTextContent());
        int width = Integer.parseInt(el.getElementsByTagName("width").item(0).getTextContent());
        int height = Integer.parseInt(el.getElementsByTagName("height").item(0).getTextContent());
        String name = el.getAttribute("name");
        String type = el.getAttribute("type");
        
        return new Window(name, type, xPos, yPos, width, height);
    }
}
