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
                ${books[entry.key].name} (qty: ${entry.value})
                <form action="<c:url value='/book/shop' />" method="get">
                    <input type="hidden" name="bookId" value="${entry.key}" />
                    <input type="hidden" name="action" value="removeCart" />
                    <input type="number" name="quantity" min="1" value="1" />
                    <input type="submit" value="reduce Cart" />
                </form>
                    <br/>
            </c:forEach>
        </ul>
        <a href="<c:url value="/book/checkout" />">Checkout</a>
    </c:otherwise>
</c:choose>
<a href="<c:url value="/book" />">Return to books</a>
</body>
</html>