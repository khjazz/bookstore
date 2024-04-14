<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
</head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<h2>Book:</h2>
<security:authorize access="hasRole('ADMIN') or
                                         principal.username=='${book.author}'">
    [<a href="<c:url value="/book/edit/${book.id}"/>">Edit</a>]
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
    [<a href="<c:url value="/book/delete/${book.id}"/>">Delete</a>]
</security:authorize>
<br/><br/>
[<a href="<c:url value='/book/shop?bookId=${book.id}&action=addToCart' />">Add to Cart</a>]<br />
[<a href="<c:url value="/book/delete/${book.id}" />">Delete</a>]<br/><br/>
Title - <c:out value="${book.name}"/><br/>
Author - <c:out value="${book.author}"/><br/>
Description - <c:out value="${book.description}"></c:out><br/>
Available - <c:out value="${book.availability}"></c:out><br/>
Price - <c:out value="${book.price}"></c:out><br/>
<%--<c:if test="${not empty book.photo}">--%>
<img src="<c:url value="/book/${book.id}/photo"/>" alt="Cover Photo" style="width: 250px; height: auto"/><br/><br/>
<%--</c:if>--%>

<c:url var="addComment" value="/book/${book.id}/comment"/>
<form:form method="POST" modelAttribute="commentForm"
           action="${addComment}">
    <form:label path="content">New Comment</form:label><br/>
    <form:textarea path="content"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
<c:if test="${!empty book.comments}">
    Comments:<br/><br/>
    <c:forEach items="${book.comments}" var="comment" varStatus="status">
        <div>
            <c:out value="${comment.user.username}"/> says:<br/>
            <c:out value="${comment.content}"/>
        </div>
        [<a href="<c:url value="/book/${bookId}/delete/${comment.id}"/>">Delete</a>]
    </c:forEach><br/><br/>
</c:if>
<a href="<c:url value="/book" />">Return to books</a>
</body>
</html>
