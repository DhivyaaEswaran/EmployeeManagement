<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	background-color: #9FD4F7;
}

.style {
	position: fixed;
	top: 12%;
	left: 28%;
	font-size: 120%;
}

.container {
	position: fixed;
	top: 40%;
	left: 28%;
	font-size: 120%;
}

p {
	color: black;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 200%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: center;
	padding: 8px;
	color: black;
}

p {
	position: fixed;
	left: 40%;
	top: 28%;
	color: black;
	font-family: sans-serif;
}

a {
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-style: bold;
}

input[type=submit] {
	background-color: white;
	border: none;
	color: black;
	padding: 5px 18px;
	position: fixed;
	top: 70%;
	left: 40%;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-style: bold;
}

button {
	background-color: white;
	border: none;
	color: black;
	padding: 5px 18px;
	position: fixed;
	top: 70%;
	left: 50%;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-style: bold;
}
</style>
</head>
<body>
	<div class="style">
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Mobile number</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<String> employees = (List<String>) request.getAttribute("employeeProject");
				String employee = employees.get(0);
				String[] employeeList = employee.split(",");
				%>
				<tr>
					<td><%=employeeList[0]%></td>
					<td><%=employeeList[1]%></td>
					<td><%=employeeList[5]%></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="container">
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Checkbox</th>
				</tr>
			</thead>
			<tbody>
				<p>LIST OF ALL PROJECTS</p>
				<%
				for (int index = 1; index < employees.size(); index++) {
					String project = employees.get(index);
					String[] projectList = project.split(",");
				%>
				<form action="EmployeeController" method="post">
					<tr>
						<td><%=projectList[0]%></td>
						<td><%=projectList[1]%></td>
						<td><input type="checkbox" name="selected"
							value="<%=projectList[0]%>"></td>
					</tr>
					<%
					}
					%>
				
			</tbody>
		</table>
		<input type="hidden" name="action" value="assign"> <input
			type="hidden" name="id" value="<%=employeeList[0]%>"><br>
		<br> <input type="submit" value="Assign">
		</form>
	</div>
	<a href="EmployeeController?action=employeeMainPage"><button>BACK</button></a>

</body>
</html>