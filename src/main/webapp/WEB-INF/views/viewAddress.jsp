<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.ideas2it.employeemanagement.model.Address"%>
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
	position: fixed;
	top: 30%;
	left: 20%;
	color: black;
	font-size: 120%;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 100%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: left;
	padding: 8px;
	color: black;
}

input[type=submit] {
	background-color: white;
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
	left: 40%;
	top: 19%;
	color: black;
	font-family: sans-serif;
}
button{
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	position: fixed;
	top: 50%;
	left: 45%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
</style>
</head>
<body>
	<p>
		<b>ADDRESS OF AN EMPLOYEE</b>
	</p>
	<div class="container">
		<table>
			<thead>
				<tr>
					<th>Door number</th>
					<th>Street name</th>
					<th>District</th>
					<th>State</th>
					<th>Country</th>
					<th>Pin code</th>
					<th>Options</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${address}" var="addresses">
					<tr>
					<c:if test="${addresses.isDeleted == false}">
						<td><c:out value="${addresses.doorNumber}" /></td>
						<td><c:out value="${addresses.streetName}" /></td>
						<td><c:out value="${addresses.district}" /></td>
						<td><c:out value="${addresses.state}" /></td>
						<td><c:out value="${addresses.country}" /></td>
						<td><c:out value="${addresses.pinCode}" /></td>
						<td><a
							href="goToEditAddress?addressId=${addresses.addressId}&id=${addresses.employee.id}">Edit</a>&nbsp;&nbsp;
							<a
							href="deleteAddress?addressId=${addresses.addressId}&employeeId=${addresses.employee.id }">Delete</a>&nbsp;&nbsp;
							<input type = "hidden" name = "id" value = "${addresses.employee.id}">
						</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<a href = "employeeMainPage"><button>BACK</button></a>
	
</body>
</html>