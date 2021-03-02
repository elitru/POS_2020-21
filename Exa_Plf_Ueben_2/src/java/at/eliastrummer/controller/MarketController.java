package at.eliastrummer.controller;

import at.eliastrummer.io.IOHandler;
import at.eliastrummer.pojo.Market;
import at.eliastrummer.pojo.Order;
import at.eliastrummer.pojo.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MarketController", urlPatterns = {"/MarketController"})
public class MarketController extends HttpServlet {
    private Market market;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.market = IOHandler.readMarket(config.getServletContext().getRealPath("/res/market.xml"));
        this.market.init();
        config.getServletContext().setAttribute("market", this.market);
    }
        

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if(session.getAttribute("order") == null) {
            session.setAttribute("order", new Order());
        }
        
        request.getRequestDispatcher("marketView.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Order order = (Order) request.getSession().getAttribute("order");
        String delete = request.getParameter("delete");
        
        if(delete != null) {
            Product p = this.market.getByName(delete);
            order.removeProduct(p);
        }
        
        String addProduct = (String) request.getParameter("product");
        String quantity = (String) request.getParameter("quantity");
        
        if(addProduct != null && quantity != null) {
            Product p = this.market.getByName(addProduct);
            int quan = Integer.parseInt(quantity);
            order.addOrder(p, quan);
        }
        
        request.getSession().setAttribute("order", order);
        request.getRequestDispatcher("marketView.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
