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

p {
	position: fixed;
	left: 14%;
	top: 14%;
	color: black;
	font-family: sans-serif;
}
</style>
</head>
<body>

	<c:if test="${address != null}">
		<form action="EmployeeController?action=updateAddress"
				method="post">
	</c:if>
	<c:if test="${address == null}">
		<form action="EmployeeController?action=insertAddress" method="post">
	</c:if>
	<p>
		<b>UPDATING EMPLOYEES ADDRESS</b>
	</p>
	<div class="container">
		<br> <label>Door Number: </label> <br> <input type="text"
			name="doorNumber"placeHolder="enter door number" size="50"
			value="<c:out value='${address.get(0).doorNumber}' />"> <br>
		<label>Street Name: </label><br> <input type="text"
			name="streetName" placeHolder="enter street name" size="50"
			value="<c:out value='${address.get(0).streetName}' />"> <br>
		<label>District: </label><br> <input type="text" name="district" placeHolder="enter district"
			size="50" value="<c:out value='${address.get(0).district}' />">
		<br> <label>State: </label><br> <input type="text"
			name="state"  placeHolder="enter state" size="50"
			value="<c:out value='${address.get(0).state}' />"> <br>
		<label>Country: </label><br> <input type="text" name="country" placeHolder="enter country"
			size="50" value="<c:out value='${address.get(0).country}' />">
		<br> <label>Pin code: </label><br> <input type="text"
			name="pinCode" placeHolder="enter pin code"size="50"
			value="<c:out value='${address.get(0).pinCode}' />"> <br>
		<input type="hidden" name="id" value="<%=request.getParameter("employeeId")%>"><br><br>
		<input type="hidden" name="addressId"
			value="${address.get(0).addressId}"> <input type="submit"
			value="SUBMIT">
		</form>
	</div>
</body>
</html>