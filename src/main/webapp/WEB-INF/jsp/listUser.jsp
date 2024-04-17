<!DOCTYPE html>
<html>
<head><title>Customer Support User Management</title></head>
<body>
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
    <input type="submit" value="Log out"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<br/><br/>
<a href="<c:url value="/book" />">Return to book list</a>
<h2>Users</h2>
<a href="<c:url value="/user/create" />">Create a User</a><br/><br/>
<c:choose>
    <c:when test="${fn:length(ticketUsers) == 0}">
        <i>There are no users in the system.</i>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Username</th>
                <th>Password</th>
                <th>email</th>
                <th>delivery</th>
                <th>Roles</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${ticketUsers}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${fn:substringAfter(user.password, '{noop}')}</td>
                    <td>${user.email}</td>
                    <td>${user.delivery}</td>
                    <td>
                        <c:forEach items="${user.roles}" var="role" varStatus="status">
                            <c:if test="${!status.first}">, </c:if>
                            ${role.role}
                        </c:forEach>
                    </td>
                    <td>
                        <security:authorize access="hasRole('ADMIN') or
                                         principal.username=='${user.username}'">
                            [<a href="<c:url value="/user/edituser/${user.username}"/>">Edit</a>]
                        </security:authorize>
                        <security:authorize access="hasRole('ADMIN')">
                        [<a href="<c:url value="/user/delete/${user.username}" />">Delete</a>]
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
