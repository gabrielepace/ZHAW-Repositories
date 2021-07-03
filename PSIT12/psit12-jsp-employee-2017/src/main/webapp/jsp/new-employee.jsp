<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.employees.domain.Employee"%>
<jsp:useBean id="employeeController" scope="session" class="com.example.employees.controller.EmployeeController" />

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
        <script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </head>
    <body>  
    
    	<jsp:useBean id="employee" scope="request" class="com.example.employees.domain.Employee" />
		<jsp:setProperty name="employee" property="*" />
        <div class="container">
            <form action="${pageContext.request.contextPath}/jsp/list-employees.jsp" method="post" role="form" data-toggle="validator">
                <%               
                    if(employee.getId() > 0){
                        employee = employeeController.getEmployeeById(employee.getId());
                    }
                    String action = "";
                    if (request.getParameter("action")==null){
                        action = "add";
                    }else{
                        action = request.getParameter("action");
                    }
                %>
                <input type="hidden" name="action" value="<%= action%>">
                <input type="hidden" name="id" value="<%= employee.getId()%>">
                <h2>Employee</h2>
                <div class="form-group col-xs-4">
                    <label for="name" class="control-label col-xs-4">Name:</label>
                    <input type="text" name="name" id="name" class="form-control" value="<%= employee.getName()%>" required placeholder="Name" /> 
                    
                    <label for="lastName" class="control-label col-xs-4">Last name:</label>
                    <input type="text" name="lastName" id="lastName" class="form-control" value="<%= employee.getLastName()%>" required placeholder="Lastname"/>
                    
                    <label for="userName" class="control-label col-xs-4">Username:</label>
                    <input type="text" name="userName" id="userName" class="form-control" value="<%= employee.getUserName()%>" required placeholder="Username"/>
                    
                    <label for="birthdate" class="control-label col-xs-4">Birth date</label>
                    <input type="text" pattern="^\d{2}-\d{2}-\d{4}$" name="birthDate" id="birthdate" class="form-control" value="<%= employee.getBirthDate()%>" maxlength="10" placeholder="dd-MM-yyyy" required />
                    
                    <label for="role" class="control-label col-xs-4">Role:</label>
                    <input type="text" name="role" id="role" class="form-control" value="<%= employee.getRole()%>" required placeholder="Role"/>
                    
                    <label for="department" class="control-label col-xs-4">Department:</label>
                    <input type="text" name="department" id="department" class="form-control" value="<%= employee.getDepartment()%>" required placeholder="Department"/>
                    
                    <label for="email" class="control-label col-xs-4">E-mail:</label>
                    <input type="text" name="email" id="email" class="form-control" value="<%= employee.getEmail()%>" placeholder="smith@aol.com" required />
                    
                    <label for="telephone" class="control-label col-xs-4">Tel.:</label>
                    <input type="text" name="tel" id="tel" class="form-control" value="<%= employee.getTelephone()%>" placeholder="076 345 6789" required /> <br>             
                    
                    <button type="submit" class="btn btn-primary  btn-md">Accept</button>
                </div>
            </form>
        </div>
    </body>
</html>