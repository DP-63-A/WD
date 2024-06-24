<%--
  Created by IntelliJ IDEA.
  User: morji
  Date: 29.05.2024
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
Hello, ${user}
<p> </p>
<form action="controller" method="get">
    <input type="hidden" name="command" value="logout">
    <input type="submit" value="Logout">
</form>
<p> </p>
<form action="controller" method="get">
    <input type="hidden" name="command" value="deleteUserPage">
    <input type="submit" value="Delete User">
</form>
<p> </p>
<form action="controller" method="get">
    <input type="hidden" name="command" value="viewUsers">
    <input type="submit" value="View Users">
</form>
</body>
</html>