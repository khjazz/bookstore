<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
</head>
<body>
<c:choose>
    <c:when test="${fn:length(comments) == 0}">
        <i>No comment.</i>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Comment ID</th>
                <th>Content</th>
                <th>Book Id</th>
            </tr>
            <c:forEach items="${comments}" var="comment">
                <tr>
                    <td>${comment.id}</td>
                    <td>${comment.content}</td>
                    <td>${comment.book.id}</td>
                    <td>
                        <c:forEach items="${user.roles}" var="role" varStatus="status">
                            <c:if test="${!status.first}">, </c:if>
                            ${role.role}
                        </c:forEach>
                    </td>
                    <td>
                        [<a href="<c:url value="/user/deleteComment/${comment.id}" />">Delete</a>]
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<a href="<c:url value="/user" />">Return to users</a>
</body>
</html>