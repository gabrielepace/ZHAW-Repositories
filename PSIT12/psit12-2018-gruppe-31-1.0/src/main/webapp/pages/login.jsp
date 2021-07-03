<%@ page contentType="text/html; charset=ISO-8859-15" pageEncoding="UTF-8"%>
<jsp:useBean id="userController" scope="session" class="ch.hungrystudent.controller.UserController"/>

<!doctype html>
<html lang="en">
<head>
    <title>Login</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="../css/img/Favicon_HungryStudent_Logo.ico"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!--main CSS -------->
    <link href="../css/registrierung.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/main.css">

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

</head>
<body>
<jsp:useBean id="userLogin" scope="request" class="ch.hungrystudent.domain.User"/>
<jsp:setProperty name="userLogin" property="*"/>
	<% 
		String showError = "hidden";
		request.setCharacterEncoding("UTF-8");

		String action;
		if(request.getParameter("action") != null) {
            action = request.getParameter("action");
            if (userController.doAction(action, userLogin)) {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else if(!"logout".equals(action)){
                showError = "visible";
            }
        }
	%>

	<div id="RegisterForm">
    <div class="container">
        <div class="register-form">
            <div class="login-main-div">
                <div class="wrapper">
                    <!-- Page Content -->
                    <div class="content">
                        <a href="../index.jsp" class="title text-dark mt-2 mr-1 float-left">
                            <h2>Hungry Student</h2>
                        </a>
                        <img class="icon" src="../css/img/IconTab_100x100px_HungryStudent.ico"
                             alt="HungryStudentIconTab">
                        <hr>
                    </div>
                </div>
                <div class="wrapper" style=<%="visibility:" + showError%>>
						<div class="container">
							<div class="row">
								<div class="col-md-12 py-0 text-center">
									<p id="errorMessage">Die Eingaben sind nicht korrekt.</p>
								</div>
							</div>
						</div>
					</div>
                <div class="panel text-center">
                    <h2>LOGIN</h2>
                    <p class="forgot text-left">Geben Sie Ihre E-Mail-Adresse und Passwort ein</p>
                </div>
                <form id="Register" method="post" action="${pageContext.request.contextPath}/pages/login.jsp">
                    <div class="form-group">
                        <label for="inputEmail"></label><input type="email" name="email" class="form-control" id="inputEmail"
                                                               required placeholder="E-Mail-Adresse" value="<%= userLogin.getEmail() %>">
                    </div>
                    <div class="form-group">
                        <label for="inputPassword"></label><input type="password" name="password" class="form-control" id="inputPassword"
                                                                  required placeholder="Passwort" value="<%= userLogin.getPassword()%>">
                    </div>
                    <div class="forgot text-left">
                        <a href="reset.jsp">Passwort vergessen?</a>
                    </div>
                    <button type="submit" name="action" value="login" class="btn btn-primary">Einloggen</button>
                    <div class="forgot text-right">
                        <p>Noch kein Mitglied? <a href="registrierung.jsp">Registrieren</a>
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>