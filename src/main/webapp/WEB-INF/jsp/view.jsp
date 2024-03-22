<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
</head>
<body>
<h2>Book:</h2>
[<a href="<c:url value="/book/delete/${book.id}" />">Delete</a>]<br/><br/>
Title - <c:out value="${book.name}"/><br/>
Author - <c:out value="${book.author}"/><br/>
Description - <c:out value="${book.description}"></c:out><br/>
Available - <c:out value="${book.availability}"></c:out><br/>
Price - <c:out value="${book.price}"></c:out><br/>
<%--<c:if test="${not empty book.photo}">--%>
<img src="<c:url value="/book/${book.id}/photo"/>" alt="Cover Photo" /><br/><br/>
<%--</c:if>--%>

<c:url var="addComment" value="/book/${book.id}/comment"/>
<form:form method="POST" modelAttribute="commentForm"
           action="${addComment}">
    <form:label path="content">New Comment</form:label><br/>
    <form:textarea path="content"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
<c:if test="${!empty book.comments}">
    Comments:
    <c:forEach items="${book.comments}" var="comment" varStatus="status">
        <div><c:out value="${comment.content}"/></div>
        [<a href="<c:url value="/book/${bookId}/delete/${comment.id}" />">Delete</a>]
    </c:forEach><br/><br/>
</c:if>
<a href="<c:url value="/book" />">Return to books</a>
</body>
</html>
