<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>SQLCmd</title>
    </head>
    <body>
        <table border=1>
        <c:forEach var="row" items="${table}">
            <tr>
            <c:forEach var="value" items="${row}">
                <td>
                    ${value}
                </td>
            </c:forEach>
            </tr>
        </c:forEach>
        </table>

        <br>
        <%@include file="footer.jsp" %>
    </body>
</html>