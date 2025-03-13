<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RedBus Signup</title>
<style>
body {
	font-family: 'Poppins', sans-serif;
	background-image: url('images/signup_background.png');
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	display: flex;
	justify-content: flex-start; /* Move form to the left */
	align-items: center;
	height: 100vh;
	margin: 0;
	padding-left: 180px; /* Optional: add some space from the left edge */
}

form {
	background: #ffffff;
	padding: 40px;
	border-radius: 20px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	width: 320px;
	text-align: center;
	transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
	display: flex;
	flex-direction: column;
	justify-content: left;
}

form:hover {
	transform: scale(1.02);
	box-shadow: 0 12px 25px rgba(0, 0, 0, 0.15);
}

h2 {
	color: #D84E55;
	font-size: 28px;
	font-weight: bold;
	margin-bottom: 20px;
}

label {
	width: 100%;
	font-size: 18px;
	font-weight: 600;
	color: #555;
	display: flex;
	justify-content: flex-start;
	align-items: center;
	margin-bottom: 8px;
}

input[type="text"], input[type="email"], input[type="password"] {
	width: 100%;
	padding: 12px;
	margin-bottom: 20px;
	border: 2px solid #ff758c;
	border-radius: 25px;
	font-size: 16px;
	outline: none;
	transition: border-color 0.3s ease, box-shadow 0.3s ease;
}

input[type="text"]:focus, input[type="email"]:focus, input[type="password"]:focus
	{
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
	background-color: #e06c75;
	transform: scale(1.05);
}
</style>
</head>
<body>
	<form action="signup" method="post">
		<h2>🚍 RedBus Signup</h2>

		<label>👤 Full Name:</label> <input type="text" name="full_name"
			placeholder="Enter your full name" required> <label>📧
			Email:</label> <input type="email" name="email"
			placeholder="Enter your email" required> <label>👥
			Username:</label> <input type="text" name="username"
			placeholder="Choose a username" required> <label>🔒
			Password:</label> <input type="password" name="password"
			placeholder="Create a password" required> <input
			type="submit" value="Signup">
			<p  style=" font-size:15px; color: red;"><s:property value="error"/></p>
	</form>
</body>
</html>
