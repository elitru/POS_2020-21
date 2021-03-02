package at.eliastrummer.controller;

import at.eliastrummer.pojo.Channel;
import at.eliastrummer.pojo.RSSFeed;
import at.eliastrummer.pojo.RSSRoot;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXB;
@WebServlet(name = "FeedController", urlPatterns = {"/FeedController"})
public class FeedController extends HttpServlet {
    private static File FILE;
    private List<Channel> channels = new ArrayList<>();
    private Map<UUID, String> urls = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String path = config.getServletContext().getRealPath("/res/data.txt");
        FILE = new File(path);
        this.readFile();
        config.getServletContext().setAttribute("channels", channels);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("feeds.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String add = request.getParameter("add");
        String remove = request.getParameter("remove");
        String read = request.getParameter("read");
        String channel = request.getParameter("channel");
        String hide = request.getParameter("hide");
        String show = request.getParameter("show");
        
        if(add != null) {
            Channel ch = loadFeed("http://51.178.220.142:5000/?url=" + add);
            this.channels.add(ch);
            this.urls.put(ch.getId(), "http://51.178.220.142:5000/?url=" + add);
            saveToFile();
        }
        
        if(remove != null) {
            UUID id = UUID.fromString(remove);
            this.channels.removeIf(c -> c.getId().equals(id));
            this.urls.remove(id);
            saveToFile();
        }
        
        if(read != null && channel != null) {
            UUID channelId = UUID.fromString(channel);
            Channel ch = this.channels.stream().filter(c -> c.getId().equals(channelId)).findFirst().get();
            UUID feedId = UUID.fromString(read);
            RSSFeed feed = ch.getItems().stream().filter(f -> f.getId().equals(feedId)).findFirst().get();
            feed.setRead(true);
        }
        
        if(hide != null && channel != null) {
            UUID channelId = UUID.fromString(channel);
            Channel ch = this.channels.stream().filter(c -> c.getId().equals(channelId)).findFirst().get();
            UUID feedId = UUID.fromString(hide);
            RSSFeed feed = ch.getItems().stream().filter(f -> f.getId().equals(feedId)).findFirst().get();
            feed.setHidden(true);
        }
        
        if(show != null && channel != null) {
            UUID channelId = UUID.fromString(channel);
            Channel ch = this.channels.stream().filter(c -> c.getId().equals(channelId)).findFirst().get();
            UUID feedId = UUID.fromString(show);
            RSSFeed feed = ch.getItems().stream().filter(f -> f.getId().equals(feedId)).findFirst().get();
            feed.setHidden(false);
        }
        
        request.getServletContext().setAttribute("channels", this.channels);
        request.getRequestDispatcher("feeds.jsp").forward(request, response);
    }
    
    private Channel loadFeed(String url) throws MalformedURLException, IOException {
        RSSRoot root = JAXB.unmarshal(new URL(url), RSSRoot.class);
        return root.getChannel();
    }
    
    private void readFile() {
        try {
            List<String> urls = new BufferedReader(new FileReader(FILE)).lines().filter(l -> l != null && !l.equals("")).collect(Collectors.toList());
            Collections.reverse(urls);
            
            for(String url : urls) {
                Channel channel = loadFeed(url);
                this.channels.add(channel);
                this.urls.put(channel.getId(), url);
            }
            
            System.out.println(">> " + FILE.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(FeedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE));
            
            for(String line : urls.values()) {
                bw.write(line + "\n");
            }
            
            bw.close();
            System.out.println(">> " + FILE.getAbsolutePath());
        } catch (IOException ex) {
            Logger.getLogger(FeedController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
