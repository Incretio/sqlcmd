<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <title>SQLCmd</title>
    </head>
    <body>
        <%@include file="includes/commandList.jsp" %>

        <h3>Очистить таблицу</h3>
        <form action="clear" method="post">
            <table>
                <tr>
                    <td>Имя таблицы</td>
                    <td><input type="text" name='tableName'/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="clear"/></td>
                </tr>
            </table>
        </form>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>