<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

button{
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	text-align: center;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
</style>
<title>Employee Management</title>
</head>
<body>
	<center>
		<p>WELCOME TO EMPLOYEE MANAGEMENT</p>
		<a href="EmployeeController?action=employeeMainPage"><button>EMPLOYEE</button></a>&nbsp;&nbsp;&nbsp;
		<a href="ProjectController?action=projectMainPage"><button>PROJECT</button></a>
	</center>
</body>
</html>