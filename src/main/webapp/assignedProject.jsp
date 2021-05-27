<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
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
	top: 48%;
	left: 44%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
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
					<c:forEach items="${project.getEmployees()}" var="assign">
						<tr>
							<td><c:out value="${assign.id}" /></td>
							<td><c:out value="${assign.name}" /></td>
							<td><input type="checkbox" name="selected" value="${assign.id}"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
	<input type="hidden" name="action" value="unassign">
	<input type="hidden" name="projectId" value="${project.projectId}">
	<br>
	<br>
	<input type="submit" value="Unassign">
	</form>
</body>
</html>