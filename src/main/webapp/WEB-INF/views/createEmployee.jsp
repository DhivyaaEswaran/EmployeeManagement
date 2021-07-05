<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.List,com.ideas2it.employeemanagement.model.Employee"%>
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

h1 {
	text-align: center;
	color: black;
	font-style: oblique;
	font-size: 200%;
}

.container {
	position: fixed;
	top: 20%;
	left: 19%;
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
	top: 87%;
	left: 28%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
</style>
</head>
<body>
<h1>Create new employee</h1>
<div class = "container">
	<form:form method="POST"  modelAttribute="employee">
			<form:hidden path="id" name="id" value="${employee.id}" />
			<form:label path="name">Name:</form:label><br><br>
			<form:input path="name" value = "${employee.name}" /><br><br>
			<form:label path="salary">Salary</form:label><br><br>
			<form:input path="salary" value = "${employee.salary}" /><br><br>
			<form:label path="dateOfBirth">Date Of Birth:</form:label><br>
			<form:input type="date" path="dateOfBirth" value = "${employee.dateOfBirth}"/><br>
			<form:label path="mobileNumber">Mobile Number:</form:label><br><br>
			<form:input path="mobileNumber" value = "${employee.mobileNumber}"/><br><br>
			<form:label path="emailId">Email ID:</form:label><br><br>
			<form:input path="emailId" value = "${employee.emailId}" /><br><br>	
			<c:if test="${0 == employee.id}">
    	    <input formaction="/createEmployee" type="submit" value="Create Employee" />
        </c:if>

        <c:if test="${0 != employee.id}">
    	    <input formaction="/updateEmployee" type="submit" value="Update Employee" />
        </c:if>
	<input type="submit" value="SUBMIT">
	</form:form>
	</div>
	<a href = "getAllEmployees"><button>BACK</button></a>
	
</body>
</html>