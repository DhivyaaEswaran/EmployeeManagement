<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

.container {
	position: fixed;
	top: 30%;
	left: 14%;
	font-size: 180%;
}

p {
	color: black;
}

input[type=submit] {
	background-color:#7DC2F0;
	border: none;
	color: black;
	padding: 5px 18px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-style: bold;
}
button{
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	position: fixed;
	top: 59.4%;
	left: 23%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
</style>
</head>
<body>
	<div class="container">
		<p>
			<b>RESTORE EMPLOYEE</b>
		</p>
		<form action="restore" method="get">
			<label>Id:</label><br> <input type="text" name="id"
				placeholder="Enter employee id"> <br>
			<br> <input type="submit" value="SUBMIT"><br>
		</form>
	</div>
	<a href = "getAllEmployees"><button>BACK</button></a>
	
</body>
</html>