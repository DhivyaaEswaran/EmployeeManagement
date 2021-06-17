<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List,com.ideas2it.employeemanagement.model.Address"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	font-size: 150%;
}

.container {
	position: fixed;
	top: 15%;
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
	color: black;
	border: none;
	padding: 5px 18px;
	text-decoration: none;
	display: inline-block;
	position: fixed;
	top: 78%;
	left: 23%;
	font-size: 16px;
	font-style: bold;
	background-color: #7DC2F0;
}
</style>
</head>
<body>
<h1>
		<b>INSERTING EMPLOYEES ADDRESS</b>
	</h1>
	<div class="container">
	<form:form action = "insertAddress" method="post" modelAttribute="address">
	<form:label path="doorNumber">doorNumber:</form:label><br>
			<form:input path="doorNumber" placeHolder="enter door number" size="50" /><br>
		<br> 
			<form:label path="streetName">streetName:</form:label><br>
			<form:input path="streetName" placeHolder="enter street name" size="50" /><br>
		<br>  
		
		<form:label path="district">District:</form:label><br>
			<form:input path="district" placeHolder="enter district" size="50"/><br>
			<br>
		<form:label path="state">State:</form:label><br>
			<form:input path="state" placeHolder="enter state" size="50"/><br>
			<br>
			<form:label path="country">Country:</form:label><br>
			<form:input path="country" placeHolder="enter country" size="50" /><br>
			<br>
			<form:label path="pinCode">Pincode:</form:label><br>
			<form:input path="pinCode" placeHolder="enter pinCode" size="50"/><br>
		<input type="hidden" name="id" value="<%=request.getParameter("id")%>"><br><br>
		 <input type="submit" value="SUBMIT">
			
		
	</div>
	</form:form>
	<a href = "employeeMainPage"><button>BACK</button></a>
	
</body>
</html>