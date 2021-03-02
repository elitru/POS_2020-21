<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Grocery Market</h1>
        <div>
            <form method="POST" action="./MarketController">
                <select name="product">
                    <c:forEach items="${market.products}" var="product">
                        <option value="${product.productName}">
                            ${product.productName} - ${product.formattedPrice}
                        </option>
                    </c:forEach>
                </select>
                <select name="quantity">
                    <c:forEach begin="1" end="5" var="i">
                        <option value="${i}">
                            ${i}
                        </option>
                    </c:forEach>
                </select>
                <button type="submit">
                    add
                </button>
            </form>
        </div>
        <hr />
        <h2>Shopping Cart</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>article</th>
                    <th>quantity</th>
                    <th>price</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <form method="POST" action="./MarketController" id="delete">
                    <input name="delete" type="text" id="delete-input" hidden />
                    <c:forEach items="${sessionScope.order.orderItems}" var="item">
                        <tr>
                            <td>${item.key.productName}</td>
                            <td>${item.key.price}</td>
                            <td>${item.value}</td>
                            <td><button type="button" onclick="onRemove('${item.key.productName}')">remove</button></td>
                        </tr>
                    </c:forEach>
                </form>
            </tbody>
        </table>
        <p>
            <strong>
                Total: ${sessionScope.order.price}
            </strong>
        </p>

        <script>
            const onRemove = (productName) => {
                document.getElementById('delete-input').value = productName;
                document.getElementById('delete').submit();
            };
        </script>
    </body>
</html>
