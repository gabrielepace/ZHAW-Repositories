<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Log Out</title>
    </head>
    <body>
        <%  
            session.setAttribute("userid", null);
            session.invalidate();
            String nextPage = request.getContextPath() + "/index.jsp";
            response.sendRedirect(nextPage);
        %>
        Logged out.
    </body>
</html>