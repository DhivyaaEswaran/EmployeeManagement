<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.ideas2it.projectmanagement.model.Project"%>
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
	
	<%
	Project projectValues = (Project)request.getAttribute("project");
	if (projectValues != null) {
	%>
	<form action="ProjectController?action=update" method="post">
	<div class="container">
		<form action="ProjectController?action=insert" method="post">
			<h1>Project Details</h1>

			<label>Name: </label> <br> <input type="text" name="projectName"
				placeholder="project name"
				value=<%=projectValues.getProjectName() %>> <br>
			<br> <label>Start Date: </label> <br> <input type="date"
				name="startDate" placeholder="start date"
				value=<%=projectValues.getStartDate() %>> <br> <br>
			<label>End Date: </label> <br> <input type="date" name="endDate"
				placeholder="end date" value=<%=projectValues.getEndDate() %>>
			<br> <br> <label>Manager: </label><br> <input
				type="text" name="manager" placeholder="manager"
				value=<%=projectValues.getManager() %>> <br> <br>
				<input type="hidden" name="projectId" value="${project.projectId}">
			<input type="submit" value="SUBMIT">
		</form>
	</div>
		<%
		}
		%>
		<%
		if (projectValues == null) {
		%>
		<form action="ProjectController?action=insert" method="post">
		<div class="container">
		<form action="ProjectController?action=insert" method="post">
			<h1>Project Details</h1>

			<label>Name: </label> <br> <input type="text" name="projectName"
				placeholder="project name"> <br>
			<br> <label>Start Date: </label> <br> <input type="date"
				name="startDate" placeholder="start date"> <br> <br>
			<label>End Date: </label> <br> <input type="date" name="endDate"
				placeholder="end date">
			<br> <br> <label>Manager: </label><br> <input
				type="text" name="manager" placeholder="manager"> <br> <br>
			<input type="submit" value="SUBMIT">
		</form>
	</div>
			<%
			}
			%>
			<%
			if (projectValues != null) {
			%>
			
			<%
			}
			%>
	
	<a href=ProjectController?action=projectMainPage><button>BACK</button></a>
</body>
</html>