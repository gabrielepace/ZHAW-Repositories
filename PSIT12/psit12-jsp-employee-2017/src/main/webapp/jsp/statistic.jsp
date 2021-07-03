<%@ page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="employeeController" scope="session"
	class="com.example.employees.controller.EmployeeController" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/employee.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
	<div id="logout">
		<a href="${pageContext.request.contextPath}/jsp/logout.jsp"
			class="logout">Logout</a>
	</div>
	<div class="container">
		<h2>Employees statistic</h2>
		<p>
			Average Age:
			<%
			out.print(employeeController.getAvgEmployeeAge());
		%>
		</p>
		<p>
			Count Department(s):
			<%
			out.print(employeeController.countDepartments());
		%>
		</p>
		<table>
			<tr>
				<th>Role</th>
				<th>#</th>
			</tr>
			<%
				for (String role : employeeController.getCountPerRole().keySet()) {
			%>
			<tr>
				<td><%=role%></td>
				<td><%=employeeController.getCountPerRole().get(role)%></td>
			</tr>
			<%
				}
			%>
		</table>
		<br>
		<form
			action="${pageContext.request.contextPath}/jsp/list-employees.jsp">
			<button type="submit" class="btn btn-primary  btn-md">Back</button>
		</form>
	</div>
</body>
</html>