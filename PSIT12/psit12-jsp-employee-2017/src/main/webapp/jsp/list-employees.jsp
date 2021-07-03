<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="employeeController" scope="session" class="com.example.employees.controller.EmployeeController" />

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/employee.css" />
        <script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </head> 
    <body>
		
		<jsp:useBean id="employee" scope="request" class="com.example.employees.domain.Employee" />
		<jsp:setProperty name="employee" property="*" />
		
		<%
            employeeController.doAction(request.getParameter("action"), employee, request.getParameter("employeeName"));
        %>
    
        <div id="logout">
            <a href="${pageContext.request.contextPath}/jsp/logout.jsp" class="logout">Logout</a>
        </div>
    
        <div class="container">
            <h2>Employees</h2>
    
            <!-- Search Form -->
			<jsp:include page="/WEB-INF/jsp/search.jsp" />
    
            <!-- List employees -->
            <jsp:include page="/WEB-INF/jsp/content.jsp" />
    
            <!-- Add employee -->
            <form action="${pageContext.request.contextPath}/jsp/new-employee.jsp">
                <button type="submit" class="btn btn-primary  btn-md">New employee</button>
            </form>
            <br>
            <!-- View statistic -->
            <form action="${pageContext.request.contextPath}/jsp/statistic.jsp">
                <button type="submit" class="btn btn-primary  btn-md">View statistic</button>
            </form>
            
        </div>
        
    </body>
</html>