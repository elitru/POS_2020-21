package at.eliastrummer.contactapp.controller;

import at.eliastrummer.contactapp.pojo.Contact;
import at.eliastrummer.contactapp.utils.IOHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ContactController", urlPatterns = {"/ContactController"})
public class ContactController extends HttpServlet {
    private List<Contact> contacts;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        String path = config.getServletContext().getRealPath("/input.json");
        try {
            contacts = IOHandler.loadContactsFromFile(path);
        } catch (IOException ex) {
            Logger.getLogger(ContactController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        if(request.getSession().getAttribute("contacts") == null) {
            request.getSession().setAttribute("allContacts", new ArrayList<Contact>(){{ addAll(contacts); }});
            request.getSession().setAttribute("contacts", new ArrayList<Contact>(){{ addAll(contacts); }});
        }
        
        if(request.getAttribute("page") == null) {
            request.setAttribute("page", 1);
        }
        
        request.getRequestDispatcher("contactView.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int page;
        
        try {
            page = Integer.parseInt(request.getParameter("page"));
        }catch(NumberFormatException e) {
            page = 1;
        }
        
        request.setAttribute("page", page);
        
        List<Contact> con = (List<Contact>) request.getSession().getAttribute("allContacts");
        
        final String toBeDeleted = request.getParameter("delete");
        if(toBeDeleted != null && !toBeDeleted.equals("")) {
            con.removeIf(c -> Integer.toString(c.getId()).equals(toBeDeleted));
        }
        
        List<Contact> contactsCopy = new ArrayList<Contact>(){{ addAll(con); }};
        String name = request.getParameter("filterName");
        String gender = request.getParameter("filterGender");
        String company = request.getParameter("filterCompanyName");
        String sortBy = request.getParameter("sortBy");
        
        filter(contactsCopy, company, name, gender);
        sort(contactsCopy, sortBy);
        
        request.getSession().setAttribute("contacts", contactsCopy);
        request.setAttribute("filterName", name);
        request.setAttribute("filterGender", gender);
        request.setAttribute("filterCompanyName", company);
        request.setAttribute("sortBy", sortBy);
        
        request.getRequestDispatcher("contactView.jsp").forward(request, response);
    }
    
    private void filter(List<Contact> contacts, String companyName, String nameFilter, String genderFilter) {
        final String company = companyName == null ? "" : companyName;
        final String name = nameFilter == null ? "" : nameFilter;
        final String gender = genderFilter == null || genderFilter.equalsIgnoreCase("Both") ? "" : genderFilter;
        
        contacts.removeIf(c -> {
            return !((c.getLastname().toLowerCase().contains(name.toLowerCase())
                    || c.getFirstname().toLowerCase().contains(name.toLowerCase()))
                    && c.getGender().contains(gender)
                    && c.getCompany().getName().toLowerCase().contains(company.toLowerCase()));
        });
    }
    
    private void sort(List<Contact> contacts, String sortBy) {
        switch(sortBy) {
            case "sort_1":
                contacts.sort(Comparator.comparing(Contact::getLastname).thenComparing(Contact::getFirstname));
                break;
            case "sort_2":
                contacts.sort(Comparator.comparing(Contact::getCompany).thenComparing(Contact::getLastname).thenComparing(Contact::getFirstname));
                break;
            case "sort_3":
                contacts.sort(Comparator.comparing(Contact::getAge));
                break;
            default:
                contacts.sort(Comparator.comparing(Contact::getId));
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
