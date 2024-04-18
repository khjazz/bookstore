<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Order List</title>
</head>
<body>
<h1>Order List</h1>
<a href="<c:url value="/book/list" />">Order List</a><br/><br/>
<c:choose>
    <c:when test="${empty orders}">
        Your Order is empty
    </c:when>
    <c:otherwise>
        <ul>
            <table>
                <tr>
                    <th>OrderID</th>
                    <th>Item</th>
                </tr>
            <c:forEach var="order" items="${orders}">
            <tr>
                <td>${order.id}<td/>
                <td>${order.detail}<td/>
            </tr>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
<a href="<c:url value="/book" />">Return to books</a>
</body>
</html>