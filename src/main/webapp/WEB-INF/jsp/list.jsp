<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
</head>
<body>
<security:authorize access="isAnonymous()">
    <a href="<c:url value="/login" />">Login</a><br/>
</security:authorize>
<security:authorize access="hasAnyRole('USER', 'ADMIN')">
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
    <a href="<c:url value="/user/selfEdit" />">Edit Account</a><br/>
    <a href="<c:url value="/book/order" />">View Order</a><br/>
    <a href="<c:url value="/book/viewCart" />">Cart</a><br/>
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
    <a href="<c:url value="/user" />">Manage User Accounts</a><br/>
    <a href="<c:url value="/book/create" />">Add a Book</a><br/><br/>
</security:authorize>
<h2>Books</h2>
<c:choose>
    <c:when test="${fn:length(books) == 0}">
        <i>There are no books in the system.</i>
    </c:when>
    <c:otherwise>
        <c:forEach items="${books}" var="entry">
            Book ${entry.id}:
            <a href="<c:url value="/book/view/${entry.id}" />">
                <c:out value="${entry.name}"/></a>
            (Author: <c:out value="${entry.author}"/>)
            <security:authorize access="hasRole('ADMIN')">
                [<a href="<c:url value="/book/edit/${entry.id}"/>">Edit</a>]
                [<a href="<c:url value="/book/delete/${entry.id}"/>">Delete</a>]
            </security:authorize>
            <br/>
            <form action="<c:url value='/book/shop' />" method="get">
                <input type="hidden" name="bookId" value="${entry.id}" />
                <input type="hidden" name="action" value="addToCart" />
                <input type="number" name="quantity" min="1" value="1" />
                <input type="submit" value="Add to Cart" />
            </form><br />
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
