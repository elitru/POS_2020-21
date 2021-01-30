package at.eliastrummer.at.xml;

import at.eliastrummer.beans.Window;
import at.eliastrummer.utils.WindowAlreadyExistingException;
import at.eliastrummer.utils.WindowNotExistingException;
import at.eliastrummer.utils.XMLMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLWindowManager {
    private Document dom;
    private Map<String, Window> windows;
    
    private static XMLWindowManager INSTANCE;
    private static final File CONFIG_FILE = Paths.get(System.getProperty("user.dir"), "src", "res", "windows.xml").toFile();
    
    public static XMLWindowManager getInstance() throws ParserConfigurationException, TransformerException, TransformerException, SAXException, IOException {
        if(INSTANCE == null) {
            INSTANCE = new XMLWindowManager();
        }
        
        return INSTANCE;
    }
    
    private XMLWindowManager() throws ParserConfigurationException, TransformerException, TransformerException, SAXException, IOException {
        loadFile(CONFIG_FILE);
        updateWindows();
    }
    
    public void saveToFile() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        factory.setAttribute("indent-number", 2);
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        Result result = new StreamResult(CONFIG_FILE);
        transformer.transform(new DOMSource(dom), result);
    }
    
    private void loadFile(File file) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        if(!file.exists()) {
            generateNew(file);
            saveToFile();
        }else{
            parse(file);
        }
    }
    
    private void parse(File file) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        dom = builder.parse(file);
    }
    
    private void generateNew(File file) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        dom = builder.newDocument();
        dom.appendChild(dom.createElement("windows"));
    }
    
    private void replace(Window window) {
        NodeList nodes = dom.getElementsByTagName("window");
        
        for(int i = 0; i < nodes.getLength(); i++) {
            Element current = (Element) nodes.item(i);
            Window win = XMLMapper.toWindow(current);
            
            if(win.equals(window)) {
                Element el = dom.createElement("window");
                window.populate(dom, el);
                
                dom.getDocumentElement().removeChild(current);
                dom.getDocumentElement().appendChild(el);
            }
        }
        
        updateWindows();
    }
    
    private void updateWindows() {
        windows = new HashMap<>();
        
        Element root = dom.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("window");
        
        for(int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            
            if(n instanceof Element) {
                String name = ((Element) n).getAttribute("name");
                windows.put(name, XMLMapper.toWindow((Element) n));
            }
        }
    }
    
    private void insert(Window window) {
        window.addToDocument(dom, dom.getDocumentElement());
        updateWindows();
    }
    
    public void initWindow(Window window) {
        if(windows.containsKey(window.getName())) {
            throw new WindowAlreadyExistingException("A window with the given name '" + window.getName() + "' already exists!");
        }
        
        insert(window);
    }
    
    public void updateWindow(Window window) {
        if(!windows.containsKey(window.getName())) {
            throw new WindowNotExistingException("A window with the given name '" + window.getName() + "' does not exist!");
        }
        
        windows.replace(window.getName(), window);
        replace(window);
    }
    
    public List<Window> getWindows() {
        return new ArrayList<>(this.windows.values());
    }
    
    public Window getWindowByName(String windowName) {
        return windows.get(windowName);
    }
}