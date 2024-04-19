<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
</head>
<body>
<security:authorize access="hasAnyRole('USER', 'ADMIN')">
    <c:url var="logoutUrl" value="/logout"/>
    <form action="${logoutUrl}" method="post">
        <input type="submit" value="Log out"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</security:authorize>
<a href="<c:url value="/login" />">Login</a><br/>
<h2>Error page</h2>
<c:choose>
    <c:when test="${empty message}">
        <p>Something went wrong.</p>
        <ul>
            <li>Status Code: ${status}</li>
            <li>Exception: ${exception}</li>
        </ul>
    </c:when>
    <c:otherwise>
        <p>${message}</p>
    </c:otherwise>
</c:choose>
<a href="<c:url value="/book" />">Return to list books</a>
</body>
</html>