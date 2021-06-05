<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.ideas2it.employeemanagement.model.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

h1 {
	text-align: center;
	color: black;
	font-style: oblique;
	font-size: 200%;
}

.container {
	position: fixed;
	top: 20%;
	left: 14%;
	color: black;
	font-size: 120%;
}

input[type=submit] {
	background-color: #7DC2F0;
	border: none;
	color: black;
	padding: 5px 18px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-style: bold;
}

button {
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	position: fixed;
	top: 77%;
	left: 23%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
</style>
</head>
<body>

	<%
	Employee employeeValues = (Employee)request.getAttribute("employee");
	if (employeeValues != null) {
	%>
	<form action="EmployeeController?action=update" method="post">
	<div class="container">
				<form action="EmployeeController?action=insert" method="post">
					<br> <label>Name: </label> <br> <input type="text"
						name="name" size="50" placeholder="Enter name"
						value=<%=employeeValues.getName() %> > <br> <br>
					<label>Salary: </label><br> <input type="text" name="salary"
						size="50" placeholder="Enter salary"
						value=<%=employeeValues.getSalary() %>> <br> <br>
					<label>Date of birth: </label><br> <input type="date"
						name="dateOfBirth" size="50" placeholder="Enter date of birth"
						value=<%=employeeValues.getDateOfBirth() %>> <br>
					<br> <label>Mobile number: </label><br> <input type="tel"
						name="mobileNumber" size="50" placeholder="Enter mobile number"
						value=<%=employeeValues.getMobileNumber() %> > <br>
					<br> <label>Email id: </label><br> <input type="email"
						name="emailId" size="50" placeholder="Enter email id"
						value=<%=employeeValues.getEmailId() %> > <br> <br>
						<input type="hidden" name="id" value="${employee.id}">
					<br> <input type="submit" value="SUBMIT">
				</form>
			</div>
		<%
		}
		%>
		<%
		if (employeeValues == null) {
		%>
		<form action="EmployeeController?action=insert" method="post">
		<div class="container">
				<form action="EmployeeController?action=insert" method="post">
					<br> <label>Name: </label> <br> <input type="text"
						name="name" size="50" placeholder="Enter name"> <br> <br>
					<label>Salary: </label><br> <input type="text" name="salary"
						size="50" placeholder="Enter salary"> <br> <br>
					<label>Date of birth: </label><br> <input type="date"
						name="dateOfBirth" size="50" placeholder="Enter date of birth"> <br>
					<br> <label>Mobile number: </label><br> <input type="tel"
						name="mobileNumber" size="50" placeholder="Enter mobile number"> <br>
					<br> <label>Email id: </label><br> <input type="email"
						name="emailId" size="50" placeholder="Enter email id"> <br> <br>
					<br> <input type="submit" value="SUBMIT">
				</form>
			</div>
			<%
			}
			%>
			<%
			if (employeeValues != null) {
			%>
			
			<%
			}
			%>
			<h1>Employee Management</h1>
			
			<a href="EmployeeController?action=employeeMainPage"><button>BACK</button></a>
</body>
</html>