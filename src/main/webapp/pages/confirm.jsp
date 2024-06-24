<%--
  Created by IntelliJ IDEA.
  User: morji
  Date: 18.06.2024
  Time: 20:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm Registration</title>
</head>
<body>
<h1>CONFIRM REGISTRATION</h1>
<p>Please enter the code below to complete your registration.</p>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="confirm"/>
    <label for="confirmationCode">Confirmation code:</label>
    <input type="text" id="confirmationCode" name="confirmationCode" required/>
    <button type="submit">Confirm</button>
</form>

<p>Here is your code: ${generatedCode}</p>

<c:if test="${not empty error_msg}">
    <p style="color: red;">${error_msg}</p>
</c:if>
</body>
</html>