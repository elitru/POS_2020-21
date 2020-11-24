package at.eliastrummer.contactapp.controller;

import at.eliastrummer.contactapp.pojo.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DownloadController", urlPatterns = {"/DownloadController"})
public class DownloadController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Contact> favs = (List<Contact>) request.getSession().getAttribute("favs");
        String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(favs);
        
        response.setContentType("application/json");
        response.setHeader("Content-disposition","attachment; filename=favourites.json");
        
        OutputStream out = response.getOutputStream();
        out.write(json.getBytes());
        out.flush();
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
