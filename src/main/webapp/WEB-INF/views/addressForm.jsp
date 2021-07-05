<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.ideas2it.employeemanagement.model.Address"%>
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
	top: 6%;
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

p {
	position: fixed;
	left: 14%;
	top: 14%;
	color: black;
	font-family: sans-serif;
}

button {
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	position: fixed;
	top: 87%;
	left: 23%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
</style>
</head>
<body>
		
		<div class="container">
		<c:if test="${address != null}">	  
		<form:form action="updateAddress" method="POST"  modelAttribute="address">
			<br>
			<form:hidden path="addressId"/>
			<form:label path="doorNumber">DoorNumber:</form:label><br><br>
			<form:input path="doorNumber" /><br><br>
			<form:label path="streetName">Street name:</form:label><br><br>
			<form:input path="streetName" /><br><br>
			<form:label path="district">District:</form:label><br><br>
			<form:input path="district"/><br><br>
			<form:label path="state">State:</form:label><br><br>
			<form:input path="state"  /><br><br>
			<form:label path="country">Country:</form:label><br><br>
			<form:input path="country"  /><br><br>	
			<form:label path="pinCode">Pincode:</form:label><br><br>
			<form:input path="pinCode"  /><br><br>
			<input type="hidden" name="employeeId" value="<%=request.getAttribute("employeeId") %>">
	<input type="submit" value="SUBMIT">
	</form:form>		
 </c:if>
 </div>
	<a href="getAllEmployees"><button>BACK</button></a>
</body>
</html>