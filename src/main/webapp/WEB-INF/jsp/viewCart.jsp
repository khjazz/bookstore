<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Cart</title>
</head>
<body>
<h1>View Cart</h1>
<a href="<c:url value="/book/list" />">Product List</a><br/><br/>
<c:choose>
    <c:when test="${empty cart}">
        Your cart is empty
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach var="entry" items="${cart.entrySet()}">
                <li>${books[entry.key].name} (qty: ${entry.value})</li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

</body>
</html>