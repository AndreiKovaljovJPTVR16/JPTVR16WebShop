<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Магазин</title>
    </head>
    <body><div class="col-md-6 offset-md-3">
        <div style="font-family: serif;">
        <h1>Список продуктов</h1>
        ${info}<br><hr>
        <a href="index"><button class="btn btn-primary btn-outline">Главная страница</button></a><br><hr>
        <ul>
            <c:forEach var="product" items="${listProducts}">
                <li>${product.name}, ${product.price}, ${product.count}
            </c:forEach>
        </ul>
        </div>
        </div>
    </body>
</html>