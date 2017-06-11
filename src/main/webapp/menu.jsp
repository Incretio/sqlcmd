<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SQLCmd</title>
    </head>
    <body>
        <c:forEach items="${menuItems}" var="item">
            <a href="/sqlcmd/projects?category=${item.id}"><c:out value="${item.name}"/>
        </c:forEach>
    </body>
</html>