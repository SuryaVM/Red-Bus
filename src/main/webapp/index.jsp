<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
</head>
<body>
	<form action="login" method="post">
        <label>Username:</label><input type="text" name="username"  ></input><br><br>
        <label>Password:</label><input type="password" name="password"  ></input><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>