<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <title>SQLCmd</title>
    </head>
    <body>
        <%@include file="includes/commandList.jsp" %>

        <form action="connect" method="post">
            <table>
                <tr>
                    <td>Имя сервера</td>
                    <td><input type="text" name='serverName' value="localhost"/></td>
                </tr>
                <tr>
                    <td>Имя базы данных</td>
                    <td><input type="text" name="dbName" value="sqlcmd"/></td>
                </tr>
                <tr>
                    <td>Имя пользователя</td>
                    <td><input type="text" name="userName" value="postgres"/></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input type="password" name="password" value="123"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="connect"/></td>
                </tr>
            </table>
        </form>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>