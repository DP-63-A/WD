<%--
  Created by IntelliJ IDEA.
  User: morji
  Date: 03.06.2024
  Time: 22:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="controller" method="post">
    <input type="hidden" name="command" value="add_user"/>
    First Name: <input type="text" name="firstName" required/>
    <br/>
    Last Name: <input type="text" name="lastName" required/>
    <br/>
    Login: <input type="text" name="login" required/>
    <br/>
    Password: <input type="password" name="password" required/>
    <br/>
    Email: <input type="email" name="email" required/>
    <br/>
    Phone: <input type="text" name="phone" required/>
    <br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>
