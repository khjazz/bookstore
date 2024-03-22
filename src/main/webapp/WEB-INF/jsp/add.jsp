<!DOCTYPE html>
<html>
<head>
    <title>Bookstore</title>
</head>
<body>
<h2>Add a book</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="ticketForm">
    <form:label path="name">Name</form:label><br/>
    <form:input type="text" path="name"/><br/><br/>
    <form:label path="author">Author</form:label><br/>
    <form:input type="text" path="author"/><br/><br/>
    <form:label path="description">Description</form:label><br/>
    <form:input type="text" path="description"/><br/><br/>
    <form:label path="price">Price</form:label><br/>
    <form:input type="number" step="0.01" min="0" path="price"/><br/><br/>
    <form:label path="availability">Available</form:label><br/>
    <form:select path="availability">
        <form:option value="true">Available</form:option>
        <form:option value="false">Not Available</form:option>
    </form:select><br/><br/>
    <b>Cover Photo</b><br/>
    <input type="file" name="photo"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
