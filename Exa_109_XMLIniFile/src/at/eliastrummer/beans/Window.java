package at.eliastrummer.beans;

import java.util.Objects;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Window {
    private String name;
    private String type;
    private int xPos;
    private int yPos;
    private int width;
    private int height;

    public Window(String name, String type, int xPos, int yPos, int width, int height) {
        this.name = name;
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    
    public static Window newDefault() {
        return new Window("Main", "window", 0, 0, 100, 100);
    }
    
    public static String getSubTag(Element node, String attribute) {
        return node.getElementsByTagName(attribute).item(0).getTextContent();
    }
    
    public void addToDocument(Document doc, Element pos) {
        Element node = doc.createElement("window");
        populate(doc, node);
        pos.appendChild(node);
    }
    
    public void populate(Document doc, Element window) {
        window.setAttribute("name", name);
        window.setAttribute("type", type);
        createSubTag(doc, "xpos", xPos, window);
        createSubTag(doc, "ypos", yPos, window);
        createSubTag(doc, "width", width, window);
        createSubTag(doc, "height", height, window);
    }
    
    private Element createSubTag(Document doc, String attributeName, int content, Element parent) {
        Element node = doc.createElement(attributeName);
        node.setTextContent(Integer.toString(content));
        parent.appendChild(node);
        return node;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Window other = (Window) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Window{" + "xPos=" + xPos + ", yPos=" + yPos + ", width=" + width + ", height=" + height + ", name=" + name + '}';
    }
}
