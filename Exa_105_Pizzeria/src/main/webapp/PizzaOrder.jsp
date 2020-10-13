<%-- 
    Document   : PizzaOrder.js
    Created on : 07.10.2020, 11:29:10
    Author     : root
--%>

<%@page import="java.util.List"%>
<%@page import="at.eliastrummer.beans.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza Order Service</title>
        <link href="index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="title">
            <img src="pizza-slice.png" />
            <h1>Pizzeria de Italiano</h1>
        </div>
        <form onsubmit="return isValid();" method="POST">
            <h2>Pizza-Auswahl</h2>
            <div class="pizza-list">
                <%
                    List<Pizza> pizzas = (List<Pizza>) application.getAttribute("pizzas");

                    for(Pizza pizza : pizzas) {
                        out.println(pizza.toHTML());
                    }
                %>
            </div>
            <div class="delivery">
                <input type="text" id="deliver-to" value="" name="delivery" placeholder="Lieferadresse" />
                <button type="submit">Bestellen</button>
            </div>
        </form>
        <script src="index.js" type="text/javascript"></script>
    </body>
</html>
