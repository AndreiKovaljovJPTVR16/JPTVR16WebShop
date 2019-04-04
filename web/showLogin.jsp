
    <div class="col-md-6 offset-md-3">
        <h1 style="text-align: center" class="display-1">Войдите!</h1>
        <form action="login" method="POST">
            <div class="form-group">
                <label for="l">Логин</label>
                <input type="login" class="form-control" id="login" aria-describedby="loginHelp" name="login" placeholder="Login">
            </div>
            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" class="form-control" name="password" id="password" placeholder="Password" required>
            </div>
            <input type="submit" class="btn btn-info" value="Вход">
            <br>
            <a href="index"><button class="btn btn-default waves-effect waves-light">Главная страница</button></a><br>
        </form>
    </div>
    