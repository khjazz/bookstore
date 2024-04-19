<!DOCTYPE html>
<html>
<head><title>Bookstore</title>
    <style>
        .error {
            color: red;
            font-weight: bold;
            display: block;
        }
    </style>
</head>

<body>
<h2>Register</h2>
<form:form method="POST" modelAttribute="ticketUser">
    <form:label path="username">Username</form:label><br/>
    <form:errors path="username" cssClass="error" />
    <form:input type="text" path="username"/><br/><br/>
    <form:label path="password">Password</form:label><br/>
    <form:errors path="password" cssClass="error" />
    <form:input type="text" path="password"/><br/><br/>
    <form:label path="confirm_password">Confirm Password</form:label><br/>
    <form:errors path="confirm_password" cssClass="error" />
    <form:input type="text" path="confirm_password" /><br/><br/>
    <form:label path="email">email</form:label><br/>
    <form:errors path="email" cssClass="error" />
    <form:input type="text" path="email"/><br/><br/>
    <form:label path="delivery">delivery</form:label><br/>
    <form:errors path="delivery" cssClass="error" />
    <form:input type="text" path="delivery"/><br/><br/>
    <br/><br/>
    <input type="submit" value="register"/>
</form:form>
<a href="<c:url value="/login" />">Return login</a>
</body>
</html>