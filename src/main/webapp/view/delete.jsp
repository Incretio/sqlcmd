<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <title>SQLCmd</title>
    </head>
    <body>
        <%@include file="includes/commandList.jsp" %>

        <h3>Удалить запись</h3>
        <form action="delete" method="post">
            <table>
                <tr>
                    <td>Имя таблицы</td>
                    <td><input type="text" name='tableName'/></td>
                </tr>
                <tr>
                    <td>Ключевое поле</td>
                    <td><input type="text" name="whereColumnName"/></td>
                    <td>Значение</td>
                    <td><input type="text" name="whereColumnValue"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="delete"/></td>
                </tr>
            </table>
        </form>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>