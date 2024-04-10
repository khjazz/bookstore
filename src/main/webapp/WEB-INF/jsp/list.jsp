<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
</head>
<body>
<h2>Books</h2>
<a href="<c:url value="/book/create" />">Add a Book</a><br/><br/>
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
            [<a href="<c:url value="/book/delete/${entry.id}" />">Delete</a>]
            [<a href="<c:url value='/book/shop?bookId=${entry.id}&action=addToCart' />">Add to Cart</a>]<br />        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
