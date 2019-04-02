<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Магазин</title>
    </head>
    <body>
        <h1>Выдать товар покупателю</h1>
        ${info}<br>
        <a href="index.jsp">Главная страница</a><br>
        <form action="giveProduct" method="POST">
            <select name="productId">
                <c:forEach var="productItem" items="${listProducts}">
                    <option value="${productItem.id}">${productItem.name}</option>
                </c:forEach>
            </select>
            <select name="buyerId">
                <c:forEach var="buyerItem" items="${listBuyers}">
                    <option value="${buyerItem.id}">${buyerItem.name} ${buyerItem.surname} ${buyerItem.money}</option>
                </c:forEach>
            </select>
            <br>
            Коллическтво:
            <input type="text" name="count">
            <br>
            <input type="submit" value="Выдать товар">
        </form>
        
    </body>
</html>