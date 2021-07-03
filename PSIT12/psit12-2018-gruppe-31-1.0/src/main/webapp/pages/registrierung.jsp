<%@ page contentType="text/html; charset=ISO-8859-15" pageEncoding="UTF-8"%>
<jsp:useBean id="userController" scope="session" class="ch.hungrystudent.controller.UserController" />

<!doctype html>
<html lang="en">
<head>
<title>Registrierung</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="icon" href="../css/img/Favicon_HungryStudent_Logo.ico" />
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!--main CSS -------->
<link rel="stylesheet" href="../css/main.css">
<link href="../css/registrierung.css" rel="stylesheet">
</head>
<body>
	<jsp:useBean id="newUser" scope="request" class="ch.hungrystudent.domain.User" />
	<jsp:setProperty name="newUser" property="*" />
	
	
	
	<div id="RegisterForm">
		<div class="container">
			<div class="register-form">
				<div class="main-div">
					<%
						String showError = "hidden";
						request.setCharacterEncoding("UTF-8");
						String confirmPassword = request.getParameter("confirmPassword");
						
						if ("add".equals(request.getParameter("register")) && confirmPassword.equals(newUser.getPassword())) {
							
							if(userController.doAction("add", newUser)) {
								
								response.sendRedirect(request.getContextPath() + "/index.jsp");
								
							} else {
								
								showError = "visible";
							}
						} else if(confirmPassword!=null){
							
							if ((!confirmPassword.isEmpty())
	                                && (!confirmPassword.equals(newUser.getPassword()) )) {
				                %>
				                <div id="notification" class="alert alert-info" role="alert">
				                    <p>Passwörter stimmen nicht!</p>
				                </div>
				                <%
							}		
							
			                
						}
						%>
						
					<div class="wrapper">
						<!-- Page Content -->
						<div class="content">
							<a href="../index.jsp" class="title text-dark mt-2 mr-1 float-left">
								<h2>Hungry Student</h2>
							</a>
							<img class="icon"
								src="../css/img/IconTab_100x100px_HungryStudent.ico"
								alt="HungryStudentIconTab">
							<hr>
						</div>
					</div>
					<div class="wrapper" style=<%="visibility:" + showError%>>
						<div class="container">
							<div class="row">
								<dic class="col-md-12 py-0 text-center">
									<p id="errorMessage">Die Eingaben sind nicht korrekt.</p>
								</dic>
							</div>			
						</div>
					</div>
					
					<div class="panel text-center">
						<h2>REGISTRATION</h2>
						<br>
					</div>
					<form id="register"
						action="${pageContext.request.contextPath}/pages/registrierung.jsp"
						method="POST">

						<div class="row">
							<div class="col-md-12 col-sm-12 form-group">
                                <label>
                                    <input id="username" type="text" class="form-control" name="username" required placeholder="Benutzername" value="<%= newUser.getUsername() %>">
                                </label>
                            </div>
						</div>
						<div class="row">
							<div class="col-md-12 col-sm-12 form-group">
                                <label>
                                    <input id="email" type="email" class="form-control" name="email" required placeholder="E-Mail-Adresse" value="<%= newUser.getEmail() %>">
                                </label>
                            </div>
						</div>
						<div class="row">
							<div class="col-md-12 col-sm-12 form-group">
                                <label>
                                    <input id="password" type="password" class="form-control" name="password" required placeholder="Passwort" value="<%= newUser.getPassword() %>">
                                </label>
                            </div>
						</div>
						<div class="row">
							<div class="col-md-12 col-sm-12 form-group">
                                <label>
                                    <input id="confirmPassword" type="password" class="form-control" name="confirmPassword" required placeholder="Passwort bestätigen" value="<%= newUser.getPassword() %>">
                                </label>
                            </div>
						</div>

						<button type="submit" name="register" value="add" class="btn btn-primary">Registrieren</button>
						
						
						<div class="forgot text-right">
							<p>
								Bereits registriert? <a href="login.jsp">Einloggen</a>
							</p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>