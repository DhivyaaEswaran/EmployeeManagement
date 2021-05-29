<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Project</title>
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
	background-color: #7DC2F0;
	border: none;
	color: black;
	padding: 5px 18px;
	position:fixed;
	top:75%;
	left:24%;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-style: bold;
}
</style>
</head>
<body>
	<c:if test="${project != null}">
		<form action="ProjectController?action=update" method="post">
	</c:if>
	<c:if test="${project == null}">
		<form action="ProjectController?action=insert" method="post">
	</c:if>
	<c:if test="${project != null}">
		<input type="hidden" name="projectId" value="${project.projectId}">
	</c:if>
	<div class="container">
		<form action="ProjectController?action=insert" method="post">
			<h1>Project Details</h1>

			<label>Name: </label> <br> <input type="text" name="projectName"
				placeholder="project name"
				value="<c:out value='${project.projectName}' />"> <br>
			<br> <label>Start Date: </label> <br> <input type="date"
				name="startDate" placeholder="start date"
				value="<c:out value='${project.startDate}' />"> <br> <br>
			<label>End Date: </label> <br> <input type="date" name="endDate"
				placeholder="end date" value="<c:out value='${project.endDate}' />">
			<br> <br> <label>Manager: </label><br> <input
				type="text" name="manager" placeholder="manager"
				value="<c:out value='${project.manager}' />"> <br> <br>
			<input type="submit" value="SUBMIT">
		</form>
	</div>
	<a href=ProjectController?action=projectMainPage><button>BACK</button></a>
</body>
</html>