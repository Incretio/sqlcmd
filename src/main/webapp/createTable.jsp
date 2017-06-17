<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <title>SQLCmd</title>
    </head>
    <body>
        <%@include file="includes/commandList.jsp" %>

        <h3>Добавление таблицы</h3>
        <form action="createTable" method="post">
            <table>
                <tr>
                    <td>Имя таблицы</td>
                    <td><input type="text" name='tableName'/></td>
                </tr>
                <tr>
                    <td>Столбец 1</td>
                    <td><input type="text" name="columns"/></td>
                </tr>
                <tr>
                    <td>Столбец 2</td>
                    <td><input type="text" name="columns"/></td>
                </tr>
                <tr>
                    <td>Столбец 3</td>
                    <td><input type="text" name="columns"/></td>
                </tr>
                <tr>
                    <td>Столбец 4</td>
                    <td><input type="text" name="columns"/></td>
                </tr>
                <tr>
                    <td>Столбец 5</td>
                    <td><input type="text" name="columns"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="createTable"/></td>
                </tr>
            </table>
        </form>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>