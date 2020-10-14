<%-- 
    Document   : PizzaOrderSummary
    Created on : 07.10.2020, 11:43:13
    Author     : root
--%>

<%@page import="at.eliastrummer.pizzeria.LanguageSelector"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="at.eliastrummer.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><% out.println(LanguageSelector.getTranslation(request, response, "overview")); %></title>
        <link href="index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="title">
            <div class="title">
                <img src="pizza-slice.png" />
                <h1>Pizzeria de Italiano</h1>
            </div>
        </div>
        <div class="overview">
            <div class="table">
                <div class="row table-title-row">
                    <section class="pizza-name table-title">Pizza</section>
                    <section class="pizza-price table-title"><% out.println(LanguageSelector.getTranslation(request, response, "price")); %></section>
                    <section class="pizza-amount table-title"><% out.println(LanguageSelector.getTranslation(request, response, "amount")); %></section>
                    <section class="pizza-price-overall table-title"><% out.println(LanguageSelector.getTranslation(request, response, "overall")); %></section>
                </div>
                <%
                    Map<Pizza, Integer> selectedPizzas = (Map<Pizza, Integer>) request.getAttribute("selected");
                    float sum = 0;
                    
                    if(selectedPizzas != null) {
                        for(Entry<Pizza, Integer> entry : selectedPizzas.entrySet()) {
                            Pizza pizza = entry.getKey();
                            sum += pizza.getPrice();
                            String row = "<div class='row'>";
                            row += "<section class='pizza-name'>" + pizza.getName() + "</section>";
                            row += "<section class='pizza-price'>" + String.format("€ %.2f", pizza.getPrice()) + "</section>";
                            row += "<section class='pizza-amount'>" + entry.getValue() + "</section>";
                            row += "<section class='pizza-price-overall'>" + String.format("€ %.2f", (entry.getValue() * pizza.getPrice())) + "</section>";
                            row += "</div>";
                            out.println(row);
                        }
                    }
                    
                    String overall = "<div class='row'><section class='pizza-name'></section>" + 
                                    "<section class='pizza-price'></section>" +
                                    "<section class='pizza-amount' style='text-align: right;'>Gesamt:</section>" +
                                    "<section class='pizza-price-overall'><b>€ %.2f</b></div>";
                    
                    out.println(String.format(overall, sum));
                %>
                <div class="delivery-to">
                    <% out.println(LanguageSelector.getTranslation(request, response, "deliver-to")); %>: 
                    <span>
                        <% 
                            String delivery = (String) request.getAttribute("delivery");
                            
                            if(delivery != null) {
                                out.println(delivery);
                            }
                        %>
                    </span>
                </div>
                <div class="back">
                    <a href="./PizzaOrderServlet"><% out.println(LanguageSelector.getTranslation(request, response, "back")); %></a>
                </div>
            </div>
        </div>
    </body>
</html>
