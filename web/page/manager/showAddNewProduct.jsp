<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Магазин</title>
    </head>
    <body>
        <h1>Добавить продукт</h1>
        ${info}<br>
        <a href="index.jsp">Главная страница</a><br>
        <form action="addNewProduct" method="POST">
            Название:<br>
            <input type="text" name="name"><br>
            Цена:<br>
            <input type="text" name="price"><br>
            Количество :<br>
            <input type="text" name="count"><br>
            <br>
            <input type="submit" value="Добавить продукт">
        </form>
    </body>
</html>