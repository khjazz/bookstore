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
</security:authorize>
<h2>Book:</h2>
<security:authorize access="hasRole('ADMIN')">
    [<a href="<c:url value="/book/edit/${book.id}"/>">Edit</a>]<br/>
    [<a href="<c:url value="/book/delete/${book.id}"/>">Delete</a>]
    <br/><br/>
</security:authorize>
Title - <c:out value="${book.name}"/><br/>
Author - <c:out value="${book.author}"/><br/>
Description - <c:out value="${book.description}"></c:out><br/>
Available - <c:out value="${book.availability}"></c:out><br/>
Price - <c:out value="${book.price}"></c:out><br/>
<%--<c:if test="${not empty book.photo}">--%>
<img src="<c:url value="/book/${book.id}/photo"/>" alt="No Cover Photo" style="width: 250px; height: auto"/><br/><br/>
<%--</c:if>--%>

<c:url var="addComment" value="/book/${book.id}/comment"/>
<security:authorize access="hasAnyRole('USER', 'ADMIN')">
    <form:form method="POST" modelAttribute="commentForm"
               action="${addComment}">
        <form:label path="content">New Comment</form:label><br/>
        <form:textarea path="content"/><br/><br/>
        <input type="submit" value="Submit"/>
    </form:form>
</security:authorize>
<c:if test="${!empty book.comments}">
    <h2>Comments:</h2>
    <c:forEach items="${book.comments}" var="comment" varStatus="status">
        <div>
            <div>
                <c:out value="${comment.user.username}"/> says:
            </div>
            <div>
                <c:out value="${comment.content}"/>
            </div>
            <security:authorize access="hasRole('ADMIN')">
                [<a href="<c:url value="/book/${bookId}/delete/${comment.id}"/>">Delete</a>]
            </security:authorize>
        </div>
    </c:forEach><br/><br/>
</c:if>
<a href="<c:url value="/book" />">Return to books</a>
</body>
</html>
