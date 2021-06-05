<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,
	com.ideas2it.employeemanagement.model.Employee,com.ideas2it.projectmanagement.model.Project"%>
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
	top: 48%;
	left: 40%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}

button {
	background-color: #7DC2F0;
	border: none;
	color: black;
	padding: 5px 18px;
	position:fixed;
	top:48%;
	left:50%;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-style: bold;
}
</style>
</head>
<body>
	<p>
		<b>ASSIGNED EMPLOYEE</b>
	</p>
	<br>
	<br>
	<div class="container">
		<form action="ProjectController" method="post">
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
					Project project = (Project)request.getAttribute("project");
					int id = project.getProjectId();
				List<Employee> values = project.getEmployees();
				
				for (Employee employees :values) {%>
				<tr>
				<td><%=employees.getId()%></td>
				<td><%=employees.getName()%></td>
				<td><input type="checkbox" name="selected" value=<%=employees.getId() %>></td>
				</tr>
				<%} %>
					
				</tbody>
			</table>
	</div>
	<input type="hidden" name="action" value="unassign">
	<input type="hidden" name="projectId" value=<%=id%>>
	<br>
	<br>
	<input type="submit" value="Unassign">
	</form>
	<a href=ProjectController?action=projectMainPage><button>BACK</button></a>
</body>
</html>