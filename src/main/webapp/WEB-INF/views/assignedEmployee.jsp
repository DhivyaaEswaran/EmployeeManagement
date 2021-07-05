<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List"
	import="com.ideas2it.employeemanagement.model.Employee,com.ideas2it.projectmanagement.model.Project"%>
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

.container {
	width: 50%;
	height: 50%;
	position: fixed;
	top: 44%;
	left: 60%;
	transform: translate(-50%, -50%);
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 50%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: center;
	padding: 8px;
	color: black;
}

p {
	position: fixed;
	left: 43%;
	top: 8%;
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

input[type=submit]{
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	position: fixed;
	top: 50%;
	left: 40%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
button{
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	position: fixed;
	top: 50%;
	left: 50%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
</style>
</head>
<body>
	<p>
		<b>ASSIGNED PROJECTS</b>
	</p>
	<br>
	<br>
	<div class="container">
		<form action="unassign" method="post">
			<table>
				<thead>
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Checkbox</th>
					</tr>
				</thead>
				<tbody>
					<%
					Employee employee = (Employee)request.getAttribute("assignEmployee");
					int id = employee.getId();
				List<Project> values = employee.getProjects();
				
				for (Project projects :values) {%>
				<tr>
				<td><%=projects.getProjectId()%></td>
				<td><%=projects.getProjectName()%></td>
				<td><input type="checkbox" name="selected" value=<%=projects.getProjectId() %>></td>
				</tr>
				<%} %>
				</tbody>
			</table>
	</div>
	
	<input type="hidden" name="employeeId" value=<%=id%>>
	<br>
	<br>
	<input type="submit" value="Unassign">
	
	</form>
	<a href = "getAllEmployees"><button>BACK</button></a>
</body>
</html>