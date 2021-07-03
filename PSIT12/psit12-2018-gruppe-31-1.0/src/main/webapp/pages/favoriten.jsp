<%@ page contentType="text/html; charset=ISO-8859-15" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="ch.hungrystudent.domain.Marker" %>
<jsp:useBean id="userController" scope="session" class="ch.hungrystudent.controller.UserController"/>

<!doctype html>
<html class="no-js" lang="">
<head>
    <title>Gespeicherte Markierungen</title>
    <meta charset="utf-8">
    <link rel="icon" href="../css/img/Favicon_HungryStudent_Logo.ico"/>
    <!-- css from bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/favoriten.css">

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
</head>
<body>
<div class="wrapper">
    <!-- Page Content -->
    <div class="content">
        <a href="../index.jsp" class="title text-dark mt-2 mr-1 float-left">
            <h2>Hungry Student</h2>
        </a>
        <img class="icon" src="../css/img/IconTab_100x100px_HungryStudent.ico" alt="HungryStudentIconTab">

        <hr>

        <h3>Gespeicherte Markierungen</h3>
        <div id="markerList" class="border border-dark">
            <%
                List<Marker> favouriteMarkers;
                favouriteMarkers = userController.getFavouriteMarkers();

                // If no user is not logged in
                if(userController.getUser().getId() == -1) {
            %>
                <p class="m-3 container-fluid">Sie müssen angemeldet sein, um Ihre gespeicherte Markierungen zu sehen.</p>
            <%
                }
                // If no markers are favourited
                else if(favouriteMarkers.isEmpty()) {
            %>
                <p class="m-3 container-fluid">Es wurden keine Markierungen gespeichert.</p>
            <%
                }
                // print markers
                else {
                    for(Marker marker : favouriteMarkers) {
            %>
            <div class="markerEntry mb-2 container-fluid">
                <div class="row">
                    <h4 class="markerTitle col"><%=marker.getName()%></h4>
                </div>
                <div class="row">
                    <img class="markerListImage col" src="../css/img/noImgAvailable.png" alt="No Image Available">
                    <p class="border border-dark col mr-2"><%=marker.getDescription()%></p>
                    <p class="border border-dark col-2"><%=marker.getOpeningHours()%></p>
                    <div class="col">
                        <p>Preisklasse: <%=marker.getPrice() != null?marker.getPrice().getValue():"Keine Preisangaben"%></p>
                        <ul>
                            <li>Vegetarisch: <%=marker.isVegetarian()?"Ja":"Nein"%></li>
                            <li>Take Away: <%=marker.isTakeAway()?"Ja":"Nein"%></li>
                        </ul>
                    </div>
                    <a href="../index.jsp?marker=<%=marker.getId()%>" class="btn col-1 mr-2">
                        <img class="w-100" src="../css/img/markerImage.png" alt="Marker Image">
                    </a>
                </div>
                <hr>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
