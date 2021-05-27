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

.container {
	position: fixed;
	top: 20%;
	left: 14%;
	font-size: 180%;
}

p {
	color: black;
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
</style>
</head>
<body>

	<div class="container">
		<p>Restore projects</p>
		<form action="ProjectController" method="get">
			<label>ProjectId:</label><br> <input type="text"
				name="projectId" placeholder="Enter project id"><br> <input
				type="hidden" name="action" value="restoreProject"> <input
				type="submit" value="Submit"> <br>
		</form>
	</div>
</body>
</html>