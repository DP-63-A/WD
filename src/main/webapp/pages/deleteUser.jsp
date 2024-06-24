<%--
  Created by IntelliJ IDEA.
  User: morji
  Date: 24.06.2024
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<h1>Delete User</h1>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="deleteUser">
    <label for="login">User Login:</label>
    <input type="text" id="login" name="login" required>
    <input type="submit" value="Delete User">
</form>

<form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="main">
    <input type="submit" value="Back to Main">
</form>

<c:if test="${not empty errorMsg}">
    <p style="color: red;">${errorMsg}</p>
</c:if>
<c:if test="${not empty successMsg}">
    <p style="color: green;">${successMsg}</p>
</c:if>
</body>
</html>