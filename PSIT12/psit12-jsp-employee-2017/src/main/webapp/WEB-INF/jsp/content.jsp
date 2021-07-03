<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.example.employees.domain.Employee"%>
<%@ page import="com.example.employees.controller.EmployeeController"%>
<%@ page import="java.util.List"%>
<jsp:useBean id="employeeController" scope="session"
	class="com.example.employees.controller.EmployeeController" />

<form action="" method="post" id="employeeForm" role="form">

	<%
		List<Employee> employeeList = employeeController.getAllEmployees();

		if (!employeeController.getSearchResult().isEmpty()
				&& request.getParameter("action").equalsIgnoreCase("search")) {
			employeeList = employeeController.getSearchResult();
		}

		if (!employeeList.isEmpty()) {
	%>

	<table id="employees-table" class="table table-striped">
		<thead>
			<tr>
				<td>#</td>
				<td>Name</td>
				<td>Last name</td>
				<td>Username</td>
				<td>Birth date</td>
				<td>Role</td>
				<td>Department</td>
				<td>E-mail</td>
				<td>Tel.</td>
				<td></td>
			</tr>
		</thead>
		<%
			for (Employee employee : employeeList) {
				if((session.getAttribute("userid").equals("Admin")) || (session.getAttribute("userid").equals("pacegab1") && employee.getDepartment().equals("Finance"))){
		%>
		<tr>
			<td><a
				href="/employees-app/jsp/new-employee.jsp?id=<%=employee.getId()%>&action=edit"><%=employee.getId()%></a>
			</td>
			<td><%=employee.getName()%></td>
			<td><%=employee.getLastName()%></td>
			<td><%=employee.getUserName()%></td>
			<td><%=employee.getBirthDate()%></td>
			<td><%=employee.getRole()%></td>
			<td><%=employee.getDepartment()%></td>
			<td><a href="mailto:<%=employee.getEmail()%>"><%=employee.getEmail()%></a>
			<td><%=employee.getTelephone()%></td>
			</td>
			<td><a href="?id=<%=employee.getId()%>&action=remove"><span
					class="glyphicon glyphicon-trash"></span></a></td>
			
		</tr>
		<%
				}
			}
		%>

	</table>

	<%
		} else {
	%>
	<br>
	<div class="alert alert-info">No records</div>
	<%
		}
	%>
</form>