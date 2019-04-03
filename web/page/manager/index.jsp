<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Менеджер</title>
    </head>
    <body><div class="col-md-6 offset-md-3">
        <div style="text-align: center; font-family: serif;">
        <H1>Добро пожаловать в наш магазин</H1>
        <div style="text-align: center; color:  #3F92D2; font-family: serif;">
            <h3>${info}<br><hr></h3>
        </div>
        <div style="text-align: left; font-family: serif;">
        <a href="logout"><button class="btn btn-primary btn-outline">Выйти</button></a><br><br>
        <a href="showChangePassword"><button class="btn btn-primary btn-outline">Изменить пароль</button></a><br>
        <br><hr>
       
        <a href="showRegistration">Добавить покупателя</a><br>
        <a href="showAddNewProduct">Добавить продукт</a><br>
        <a href="showListBuyers">Список покупателей</a><br>
        <a href="showListProducts">Список продуктов</a><br>
        <a href="showPageForGiveProduct">Выдать продукт</a><br>
        </div>
        </div>
        </div>
        
        
    </body>
</html>