<%--
  Created by IntelliJ IDEA.
  User: morji
  Date: 23.06.2024
  Time: 22:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.wd.entity.User" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h1>USERS LIST</h1>
<table border="1">
    <tr>
        <th>Login</th>
        <th>First name</th>
        <th>Last name</th>
        <th>Phone number</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.login}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.phone}</td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="main">
    <input type="submit" value="Back to Main">
</form>
</body>
</html>
