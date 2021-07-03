<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
        <script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%  
            request.setCharacterEncoding("UTF-8");
            String userId = (String) session.getAttribute("userid");
            String nextPage;
            if (userId == null) {
                nextPage = "/login.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(nextPage);
                dispatcher.forward(request, response);
            }else{
                nextPage = "/jsp/list-employees.jsp";
                RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(nextPage);
                dispatcher.forward(request, response);
            }       
        %>
    </body>
</html>
