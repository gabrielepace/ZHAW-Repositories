<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.employees.access.AdminLogin"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert title here</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
        <script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <style type="text/css">
            .table {
                width: 50%;
                margin: 0 auto !important;
            }
        </style>
    </head>
    <body>
        <%
            request.setCharacterEncoding("UTF-8");
            if (request.getParameter("uname") == null) {
        %>

        <form method="post" action="">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th colspan="2">Login Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>User Name</td>
                        <td><input type="text" name="uname" value="" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="pass" value="" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Login" /><input type="reset"
                            value="Reset" /></td>
                    </tr>
                </tbody>
            </table>
        </form>
        <%
            }else{
                String userid = request.getParameter("uname");    
                String pwd = request.getParameter("pass");
                
                if (AdminLogin.checkLogin(userid, pwd)) {
                    session.setAttribute("userid", userid);
                    String nextPage = request.getContextPath() + "/jsp/list-employees.jsp";
                    response.sendRedirect(nextPage);
                } else {
                    String nextPage = request.getContextPath() + "/jsp/login.jsp";
                    out.println("Invalid password <a href='" + nextPage + "'>try again</a>");
                }
            }
        %>
    </body>
</html>