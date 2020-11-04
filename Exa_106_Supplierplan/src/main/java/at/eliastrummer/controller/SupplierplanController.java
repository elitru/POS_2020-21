package at.eliastrummer.controller;

import at.eliastrummer.beans.Lesson;
import at.eliastrummer.utils.IOHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SupplierplanController", urlPatterns = {"/SupplierplanController"})
public class SupplierplanController extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); 
        
        List<Lesson> lessons = IOHandler.loadLessons(config.getServletContext().getRealPath("/res/lessons.csv"));
        config.getServletContext().setAttribute("lessons", lessons);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("supplierplanView.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
