<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <title>SQLCmd</title>
    </head>
    <body>
        <%@include file="includes/commandList.jsp" %>

        <h3>Обновить запись</h3>
        <form action="update" method="post">
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
                    <td>Обновляемое поле</td>
                    <td><input type="text" name="setColumnName"/></td>
                    <td>Значение</td>
                    <td><input type="text" name="setColumnValue"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="update"/></td>
                </tr>
            </table>
        </form>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>