<%@ page contentType="text/html; charset=ISO-8859-15" pageEncoding="UTF-8" %>
<jsp:useBean id="userController" scope="session" class="ch.hungrystudent.controller.UserController"/>

<!doctype html>
<html lang="en">

<head>
    <title>Benutzer Einstellungen</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <link rel="icon" href="../css/img/Favicon_HungryStudent_Logo.ico"/>
    <!-- css from bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/benutzereinstellungen.css">

    <!-- js, jquery, popper from bootstrap -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>
    <script src="../js/main.js"></script>
</head>

<body id="UserForm">
<jsp:useBean id="user" class="ch.hungrystudent.domain.User"/>
<jsp:setProperty name="user" property="*"/>
<jsp:useBean id="userDao" class="ch.hungrystudent.dataaccess.UserDaolmpl"/>
<jsp:setProperty name="userDao" property="*"/>
<div class="wrapper">
    <div class="content">
        <a href="../index.jsp" class="title text-dark mt-2 mr-1 float-left">
            <h2>Hungry Student</h2>
        </a>
        <img class="icon" src="../css/img/IconTab_100x100px_HungryStudent.ico" alt="HungryStudentIconTab">

        <hr>

            <%
                // users can't edit or remove it if not logged in
                long userId = userController.getUser().getId();
                if(userId == -1) {
            %>
        <p class="m-3 container-fluid">Sie müssen angemeldet sein, bevor Sie der Benutzer bearbeiten bzw. löschen
            können.</p>
        <P class="m-3 container-fluid">Klicken Sie <a href="../index.jsp">hier</a>, um wieder auf die Hauptseite zu
            kommen.</P>
            <%
		}
		else {
		%>
        <div class="formularContent">
            <form method="POST"
                  action="" role="form"
                  data-toggle="validator">
                <%
                    String action = request.getParameter("action");
                    if (user.getUsername().isEmpty()) {
                        user.setEmail(userController.getUser().getEmail());
                        user.setUsername(userController.getUser().getUsername());
                    }
                    String confirmPassword = request.getParameter("confirmPassword");
                    if (confirmPassword == null) {
                        confirmPassword = "";
                    }
                %>
                <h3 id="userSettingsTitle">Benutzer bearbeiten</h3>
                <div>
                    <input type="hidden" name="id" value="<%= user.getId()%>">
                    <div class="form-group row">
                        <label for="username" class="col-sm-2 col-form-label">Benutzername</label>
                        <div class="col-sm-8">
                            <input type="text" name="username" class="form-control" id="username"
                                   required placeholder="Benutzername"
                                   value="<%= user.getUsername()%>">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="emailAddress" class="col-sm-2 col-form-label">E-Mail-Adresse</label>
                        <div class="col-sm-8">
                            <input type="email" name="email" class="form-control" id="emailAddress"
                                   required placeholder="E-Mail"
                                   value="<%= user.getEmail()%>">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="password" class="col-sm-2 col-form-label">Neues Passwort</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="Passwort"
                                   value="<%= user.getPassword()%>">
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="confirmPassword" class="col-sm-2 col-form-label">Passwort bestätigen</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" name="confirmPassword"
                                   id="confirmPassword"
                                   placeholder="Passwort bestätigen">
                        </div>
                    </div>
                </div>
                <%
                    request.setCharacterEncoding("UTF-8");
                    if (!confirmPassword.isEmpty()
                            && confirmPassword.equals(user.getPassword())) {
                    	
                        if (request.getParameter("submitButton") != null) {
                            user.setId(userController.getUser().getId());
                            
                            if (userController.doAction(action, user)){
                            	
                            	response.sendRedirect(request.getContextPath() + "/index.jsp");
                            } else {
                            	%><div class="wrapper" style="visibility:visible">
	        						<div class="container">
	        							<div class="row">
	        								<div class="col-md-12 py-0 text-center">
	        									<p id="errorMessage">Die Eingaben sind nicht korrekt.</p>
	        								</div>
	        							</div>			
	        						</div>
	        					</div> 
                        		<%
                            }
                        } 
                    } else {
                        if ((!confirmPassword.isEmpty())
                                && (!confirmPassword.equals(user.getPassword()) ) ) {
			                %>
			                <div id="notification" class="alert alert-info" role="alert">
			                    <p>Passwörter stimmen nicht!</p>
			                </div>
			                <%
                        }

                        if (request.getParameter("removeButton") != null) {
                            user.setId(userController.getUser().getId());
                            userController.doAction("remove", user);
                            response.sendRedirect(request.getContextPath() + "/index.jsp");
                        }
                    }
                %>
                <div class="form-group row">
                    <div class="col-sm-8">
                        <input id="userEditButton" class="btn btn-primary" type="submit" value="Bearbeiten" name="submitButton">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="user" value="<%= user.getId()%>">
                    </div>
                </div>

                <div class="forgot text-left">
                    <form method="POST"
                          action=""
                          role="form">
                        <input id="userRemoveButton" class="btn btn-danger" type="submit" value="Benutzer löschen" name="removeButton">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="user" value="<%= user.getId()%>">
                    </form>
                    <%
                        }
                    %>
                </div>
            </form>
        </div>
</body>
</html>