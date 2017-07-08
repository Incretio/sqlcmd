<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <script type="text/javascript" src="view/scripts/dynamicPage.js"></script>
        <title>SQLCmd</title>
    </head>
    <body onload="addFieldName('main');">
        <%@include file="includes/commandList.jsp" %>

        <h3>Добавить таблицу</h3>
        <form action="createTable" method="post">
            <p id="main">
                Имя таблицы: <input type="text" name='tableName'/>
            </p>

            <input id="clickMe" type="button" value="Добавить поле" onclick="addFieldName('main');" />
            <br><br>
            <input type="submit" name="createTable"/>

        </form>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>