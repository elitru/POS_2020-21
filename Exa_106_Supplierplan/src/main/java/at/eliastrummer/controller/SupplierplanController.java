package at.eliastrummer.controller;

import at.eliastrummer.beans.Lesson;
import at.eliastrummer.beans.LessonsRow;
import at.eliastrummer.beans.Teacher;
import at.eliastrummer.utils.IOHandler;
import at.eliastrummer.utils.Mapper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        try {
            super.init(config);
            
            String path = config.getServletContext().getRealPath("/stundenplan.csv");
            List<Lesson> lessons = IOHandler.loadLessons(path);
            config.getServletContext().setAttribute("rowLessons", Mapper.rowifyLessons(lessons));
            config.getServletContext().setAttribute("class", IOHandler.loadClassName(path));
        } catch (Exception ex) {
            Logger.getLogger(SupplierplanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getRequestDispatcher("supplierplanView.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int dayOfWeekId = Integer.parseInt(request.getParameter("dayOfWeek"));
        int lesson = Integer.parseInt(request.getParameter("lesson"));
        String subject = request.getParameter("subject");
        String teachers = request.getParameter("teachers");
        
        List<Teacher> parsedTeachers = Mapper.toTeachers(teachers);
        
        List<LessonsRow> rows = (List<LessonsRow>) getServletContext().getAttribute("rowLessons");
        LessonsRow row = rows.get(lesson - 1);
        Lesson ls = row.getLessons().get(dayOfWeekId - 1);
        ls.setTeachers(parsedTeachers);
        ls.setSubject(subject);
        ls.setSupplement(true);
        request.getRequestDispatcher("supplierplanView.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
