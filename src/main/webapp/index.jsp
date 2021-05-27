<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
	background-image: url('download1.jpg');
	background-repeat: no-repeat;
	background-size: 1370px 670px;
}

p {
	color: #2E86C1;
	font-family: fantasy;
	font-size: 150%;
	padding-bottom: 25px;
}

a {
	color: black;
	padding-bottom: 10px;
	text-decoration: none;
	font-size: 135%;
}
</style>
<title>Employee Management</title>
</head>
<body>
	<center>
		<p>WELCOME TO EMPLOYEE MANAGEMENT</p>
		<a href="EmployeeController?action=employeeMainPage">EMPLOYEE</a>&nbsp;&nbsp;&nbsp;
		<a href="ProjectController?action=projectMainPage">PROJECT</a>
	</center>
</body>
</html>