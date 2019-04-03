<%@page contentType="text/html" pageEncoding="UTF-8"%>


    <div class="col-md-20 offset-md-3">
        <H3>Добро пожаловать в наш магазин </H3><br>
        ${info}<br>
        
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link active" href="showLogin"><button type="button" class="btn btn-outline-secondary">Войти</button></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="logout"><button type="button" class="btn btn-outline-secondary">Выйти</button></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="showRegistration"><button type="button" class="btn btn-outline-secondary">Зарегистрироваться</button></a>
            </li>
        </ul>
    </div>