<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>
body {
	font-family: 'Poppins', sans-serif;
	background-image: url('images/login_background.png');
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

form {
	background: #ffffff;
	padding: 40px;
	border-radius: 20px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	width: 320px;
	height: 380px;
	text-align: center;
	transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
	display: flex;
	flex-direction: column;
	justify-content: center;
	margin-top: -300px; /* Moves form upward */
}

form:hover {
	transform: scale(1.02);
	box-shadow: 0 12px 25px rgba(0, 0, 0, 0.15);
}

p {
	color: #D84E55;
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 20px;
}
a {
	color: #D84E55;
	font-size: 17px;
	font-weight: bold;
	margin-bottom: 20px;
}
h2 {
	color: #D84E55;
	font-size: 28px;
	font-weight: bold;
	margin-bottom: 20px;
}

label {
	font-size: 18px;
	font-weight: 600;
	color: #555;
	width: 100%; /* Ensures label takes full width */
	text-align: left; /* Keeps label text aligned to the left */
	display: flex;
	align-items: center;
	margin-bottom: 8px;
}

input[type="text"], input[type="password"] {
	width: 100%;
	padding: 12px;
	margin-bottom: 20px;
	border: 2px solid #ff758c;
	border-radius: 25px;
	font-size: 16px;
	outline: none;
	transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

input[type="text"]:focus, input[type="password"]:focus {
	border-color: #ff4757;
	box-shadow: 0 0 8px rgba(255, 117, 140, 0.5);
}

input[type="submit"] {
	border-radius: 50px;
	padding: 10px 15px;
	background-color: #D84E55;
	color: white;
	border: none;
	cursor: pointer;
	transition: background-color 0.3s ease;
	font-size: 18px;
	font-weight: bold;
}

input[type="submit"]:hover {
	background-color: #b83a45;
	transform: scale(1.05);
}
</style>
</head>
<body>
	<form action="login" method="post">
		<h2>Welcome to 🚍RedBus! Signin</h2>

		<label>👤 Username:</label> <input type="text" name="username" required>

		<label>🔒 Password:</label> <input type="password" name="password" required>

		<input type="submit" value="Login">
		<p >If New user!</p>
		<a href="signup.jsp">Register here</a>
	</form>

</body>
</html>
