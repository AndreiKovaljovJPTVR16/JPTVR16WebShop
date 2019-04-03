<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div class="col-md-20 offset-md-3">
    <H1>Добро пожаловать в наш магазин</H1>
    ${info}<br>

    <ul class="nav">

        <li class="nav-item">
            <a href="logout"><button type="button" class="btn btn-outline-secondary">Выйти</button></a>
        </li>
        <li class="nav-item">
            <a href="showChangeRole"><button type="button" class="btn btn-outline-secondary">Изменить роль</button></a>
        </li>
        <li class="nav-item">
            <a href="showAddNewProduct"><button type="button" class="btn btn-outline-secondary">Добавить продукт</button></a>
        </li>
        <li class="nav-item">
            <a href="showListProducts"><button type="button" class="btn btn-outline-secondary">Список продуктов</button></a>
        </li>

    </ul>

</div>
