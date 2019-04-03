<%-- 
    Document   : uploadFile
    Created on : Mar 5, 2019, 12:48:27 PM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Загрузка файла</title>
    </head>
    <body><div class="col-md-6 offset-md-3">
        <div style=" font-family: serif;">
        <h1>Загрузка файла!</h1>
        <p>${info}</p>
        <form action="uploadFile" method="POST" enctype="multipart/form-data">
            <input type="text" name="description"><br><hr>
            <input type="file" class="btn btn-primary btn-outline" name="file"><br><br>
            <input type="submit" class="btn btn-primary btn-outline" value="Загрузить">
            </div>
        </div>
        </form>
        
    </body>
</html>
