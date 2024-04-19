<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
</head>
<body>
<h1>Order List</h1>
<c:choose>
    <c:when test="${empty orders}">
        Your Order is empty<br/>
    </c:when>
    <c:otherwise>
            <table>
                <tr>
                    <th>OrderID</th>
                    <th>Item</th>
                </tr>
            <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}</td>
                <td>${order.detail}</td>
            </tr>
            </c:forEach>
            </table>
    </c:otherwise>
</c:choose>
<a href="<c:url value="/book" />">Return to books</a>
</body>
</html>