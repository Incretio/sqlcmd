<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
    <head>
        <title>SQLCmd</title>
    </head>
    <body>
        <%@include file="includes/commandList.jsp" %>

        <table border=1>
        <c:forEach var="userAction" items="${userActionsList}">
            <tr>
                <td>
                    ${userAction.id}
                </td>
                <td>
                    ${userAction.userName}
                </td>
                <td>
                    ${userAction.dbName}
                </td>
                <td>
                    ${userAction.action}
                </td>
            </tr>
        </c:forEach>
        </table>


        <%@include file="includes/footer.jsp" %>
    </body>
</html>