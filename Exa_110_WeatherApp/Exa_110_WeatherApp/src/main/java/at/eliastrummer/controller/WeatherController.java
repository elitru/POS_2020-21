package at.eliastrummer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import at.eliastrummer.pojo.Location;
import at.eliastrummer.xmlutils.HTTPRequestHandler;
import javax.xml.bind.DataBindingException;

@WebServlet(name = "WeatherController", urlPatterns = {"/WeatherController"})
public class WeatherController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("lang", "en");
        request.getRequestDispatcher("weatherView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String language = request.getParameter("lang");
        String city = request.getParameter("city");
        
        if(city != null && !city.equalsIgnoreCase("")) {
            try {
                Location loc = HTTPRequestHandler.requestLocationInfo(city, language);
                request.setAttribute("loc", loc);
            }catch(DataBindingException e) {
                request.setAttribute("notFound", language.equalsIgnoreCase("de") ? "Dieser Ort wurde nicht gefunden" : "This location could not be found");
            }
        }
        
        request.setAttribute("city", city);
        request.setAttribute("lang", language);
        request.getRequestDispatcher("weatherView.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
