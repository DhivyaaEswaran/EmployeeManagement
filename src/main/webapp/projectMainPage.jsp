<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
}
li {
	float: left;
}
li a {
	display: block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}
li a:hover:not(.active) {
	background-color: #B4D7EF;
}
.container {
	width: 50%;
	height: 50%;
	position: fixed;
	top: 32%;
	left: 40%;
	transform: translate(-50%, -50%);
}
table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 150%;
}
td, th {
	border: 1px solid #dddddd;
	text-align: center;
	padding: 8px;
	color: black;
}
p {
	text-align: center;
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
button {
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
</style>
</head>
<body>
	<ul>
		<li><b><a href="ProjectController?action=index">HOME</a></b></li>
		<li><b><a href="ProjectController?action=projectForm">ADD
					PROJECT</a></b></li>
		<li><b><a href="ProjectController?action=restore">RESTORE</a></b></li>
	</ul>
	<div class="container">
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Manager</th>
					<th>Options</th>
				</tr>
			</thead>
			<tbody>
				<p>
					<b>LIST OF ALL PROJECTS</b>
				</p>
				<c:forEach items="${projectList}" var="project">
					<tr>
						<td><c:out value="${project.projectId}" /></td>
						<td><c:out value="${project.projectName}" /></td>
						<td><c:out value="${project.startDate}" /></td>
						<td><c:out value="${project.endDate}" /></td>
						<td><c:out value="${project.manager}" /></td>
						<td><a
							href="ProjectController?action=editProject&projectId=<c:out value='${project.projectId}'/>"><button>Edit</button></a>&nbsp;&nbsp;
							<a
							href="ProjectController?action=deleteProject&projectId=<c:out value='${project.projectId}'/>"><button>Delete</button></a>
							<a
							href="ProjectController?action=displayProject&projectId=<c:out value='${project.projectId}'/>"><button>Assign</button></a>
							<a
							href="ProjectController?action=assignedProject&projectId=<c:out value='${project.projectId}'/>"><button>Unassign</button></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br>
	</div>
</body>
</html>