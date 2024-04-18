<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Cart</title>
</head>
<body>
<h1>View Cart</h1>
<a href="<c:url value="/book/list" />">Product List</a><br/><br/>
<p>Checkouted</p>
 ${result}
<a href="<c:url value="/book" />">Return to list books</a>
</body>
</html>