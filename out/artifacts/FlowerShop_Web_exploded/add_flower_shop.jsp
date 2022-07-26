<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="controller?action=add_flower_to_shop">
    <p><input type="text" name="name" size="10"/></p>
    <p><input type="text" name="price" size="10" /></p>
    <p><input type="text" name="steak" size="10" /></p>
    <p><input type="text" name="ice" size="10" /></p>
    <p><input type="submit" value="Add" /></p>
    <p></p>
</form>
</body>
</html>
