<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Пополнить баланс</title>
    </head>
    <body>
        <h1>Добавте деньги на свою карточку!</h1>
        <a href="index">Главная страница</a><br>
        <p>${info}</p>
        Вы вошли как ${username}<br>
        <form action="addMoney" method="POST">
              Внести деньги <input type="text" class="btn btn-primary btn-outline" name="money">
            <br>
            <input type="submit" class="btn btn-primary btn-outline" value="Добавить деньги на карту"> 
        </form>
    </body>
</html>
