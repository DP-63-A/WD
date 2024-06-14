<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<h1>Welcome to the Application</h1>
<form action="controller" method="post">
    <div>
        <label for="login">Login:</label>
        <input type="text" id="login" name="login" required>
    </div>
    <div>
        <label for="pass">Password:</label>
        <input type="password" id="pass" name="pass" required>
    </div>
    <div>
        <input type="hidden" name="command" value="login">
        <button type="submit">Sign In</button>
    </div>
    <div>
        <p>Don't have an account? <a href="pages/register.jsp">Register Here</a></p>
    </div>
</form>
</body>
</html>