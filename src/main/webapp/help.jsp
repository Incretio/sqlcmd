<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <title>SQLCmd</title>
    </head>
    <body>
        <%@include file="includes/commandList.jsp" %>


        <h3>Помощь по работе с программой</h3>
        <pre>${helpText}</pre>

        <%@include file="includes/footer.jsp" %>
    </body>
</html>