<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta charset="utf-8">
        <script type="text/javascript" src="scripts/dynamicPage.js"></script>
        <title>SQLCmd</title>
    </head>
    <body>
        <%@include file="includes/commandList.jsp" %>

        <h3>Добавить запись</h3>
        <form action="insert" method="post">
             <p id="main">
                Имя таблицы: <input type="text" name='tableName'/>
             </p>

            <input id="clickMe" type="button" value="Добавить поле" onclick="addLine('main');" />
            <br><br>
            <input type="submit" name="insert"/>

        </form>
        <%@include file="includes/footer.jsp" %>
    </body>
</html>