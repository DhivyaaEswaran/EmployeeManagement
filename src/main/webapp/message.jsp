<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	background-image: url('download1.jpg');
	background-repeat: no-repeat;
	background-size: 1370px 670px;
}

button{
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	position: fixed;
	top: 40%;
	left: 23%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
.container{
position: fixed;
	top: 30%;
	left: 23%;
	font-size: 200%;
	
}
</style>
</head>
<body>
<div class = "container">
<%= request.getAttribute("message") %>
</div>
<a href = "EmployeeController?action=employeeMainPage"><button>BACK</button></a>
</body>
</html>