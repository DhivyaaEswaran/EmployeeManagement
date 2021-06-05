<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.ideas2it.employeemanagement.model.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
p {
	font-size: 120%;
}

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
	width: 55%;
	height: 42%;
	position: fixed;
	top: 40%;
	left: 28%;
	transform: translate(-50%, -50%);
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 190%;
}

td, th {
	border: 1px solid #dddddd;
	text-align: center;
	padding: 6px;
	color: black;
}

p {
	position: fixed;
	left: 40%;
	top: 8%;
	color: black;
	font-family: sans-serif;
}

a {
	color: black;
	border: none;
	padding: 3px 8px;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	font-style: bold;
}

button {
	color: black;
	border: none;
	padding: 8px 18px;
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
		<li><b><a href="EmployeeController?action=index">HOME</a></b></li>
		<li><b><a href="EmployeeController?action=employeeForm">ADD
					EMPLOYEE</a></b></li>

		<li><b><a href="EmployeeController?action=goToRestore">RESTORE
					EMPLOYEE</a></b></li>
	</ul>
	<p>
		<b>List of all employees</b>
	</p>
	<div class="container">
		<table>
			<thead>
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Salary</th>
					<th>Dob</th>
					<th>Emailid</th>
					<th>Mobile Number</th>
					<th>Options</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Employee> employeeValues = (List<Employee>)request.getAttribute("employeeList");
				
				for (Employee employees : employeeValues) {%>
				<tr>
					<td><%=employees.getId()%></td>
					<td><%=employees.getName()%></td>
					<td><%=employees.getSalary()%></td>
					<td><%=employees.getDateOfBirth()%></td>
					<td><%=employees.getMobileNumber()%></td>
					<td><%=employees.getEmailId()%></td>
					<td><a href="EmployeeController?action=goToEditEmployee&id=<%=employees.getId() %>"><button>Edit</button></a> 
					<a href="EmployeeController?action=deleteEmployee&id=<%=employees.getId() %>"><button>Delete</button></a> 
					<a href="EmployeeController?action=addressForm&id=<%=employees.getId() %>"><button>Add address</button></a> 
					<a href="EmployeeController?action=viewAddress&id=<%=employees.getId() %>"><button>View address</button></a> 
					<a href="EmployeeController?action=displayProject&id=<%=employees.getId() %>"><button>Assign</button></a> 
					<a href="EmployeeController?action=goToAssignedEmployee&id=<%=employees.getId() %>"><button>Unassign</button></a></td>
				</tr>
				<%} %>
			</tbody>
		</table>
		</tbody>
		</table>
	</div>
</body>
</html>