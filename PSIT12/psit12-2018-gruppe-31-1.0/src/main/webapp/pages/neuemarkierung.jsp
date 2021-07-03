<%@ page contentType="text/html; charset=ISO-8859-15" pageEncoding="UTF-8"%>
<%@ page import="ch.hungrystudent.domain.Kitchen" %>
<%@ page import="ch.hungrystudent.domain.Price" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ch.hungrystudent.domain.Marker" %>
<%@ page import="java.util.logging.Logger" %>
<%@ page import="java.util.logging.Level" %>
<jsp:useBean id="markerController" scope="request" class="ch.hungrystudent.controller.MarkerController"/>
<jsp:useBean id="userController" scope="session" class="ch.hungrystudent.controller.UserController"/>

<!doctype html>
<html lang="en">

<head>
    <title>Markierung Erstellen</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="icon" href="../css/img/Favicon_HungryStudent_Logo.ico" />
    <!-- css from bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/neuemarkierung.css">

    <!-- js, jquery, popper from bootstrap -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
    <script src="../js/main.js"></script>
</head>

<body id="MarkerForm">
<jsp:useBean id="marker" class="ch.hungrystudent.domain.Marker"/>
<jsp:setProperty name="marker" property="*"/>
    <div class="wrapper">
        <div class="content">
            <a href="../index.jsp" class="title text-dark mt-2 mr-1 float-left">
                <h2>Hungry Student</h2>
            </a>
            <img class="icon" src="../css/img/IconTab_100x100px_HungryStudent.ico" alt="HungryStudentIconTab">

            <hr>

            <%
                Logger logger = Logger.getLogger("neuemarkierung.jsp");
                request.setCharacterEncoding("UTF-8");

                // users can't create markers if not logged in
                long userId = userController.getUser().getId();
                if(userId == -1) {
            %>
            <p class="m-3 container-fluid">Sie müssen angemeldet sein, bevor Sie Markierungen erstellen können.</p>
            <P class="m-3 container-fluid">Klicken Sie <a href="../index.jsp">hier</a>, um wieder auf die Hauptseite zu kommen.</P>
            <%
                }
                else {
            %>
            <div class="formularContent">
                <form method="POST"
                      action="${pageContext.request.contextPath}/pages/neuemarkierung.jsp" role="form"
                      data-toggle="validator">
                    <%
                        String submitText = "";
                        String action;
                        if (request.getParameter("action") == null){
                            action = "add";
                            submitText = "Erstellen";
                        }
                        else if("edit".equals(request.getParameter("action"))) {
                            action = "edit";
                            submitText = "Editieren";
                        }
                        else{
                            action = request.getParameter("action");
                        }
                        if(request.getParameter("coordinates") != null) {
                            marker.setLocation(request.getParameter("coordinates"));
                        }

                        long markerId = 0;
                        if(request.getParameter("marker") != null) {
                            try{
                                markerId = Long.valueOf(request.getParameter("marker"));

                                Marker resultMarker = markerController.getMarkerById(markerId);
                                if(resultMarker != null) {
                                    marker = resultMarker;
                                }
                            } catch (NumberFormatException e) {
                                logger.log(Level.SEVERE, "Couldn't parse markerId", e);
                                response.sendRedirect(request.getContextPath() + "/index.jsp");
                            }
                        }
                    %>
                    <h3>Markierung <%=submitText%></h3>
                    <input type="hidden" name="action" value="<%= action%>">
                    <div>
                        <form>
                            <input type="hidden" name="id" value="<%= marker.getId()%>">
                            <input type="hidden" name="location" value="<%= marker.getLocation()%>">
                            <div class="form-group row">
                                <label for="markerName" class="col-sm-2 col-form-label">Name</label>
                                <div class="col-sm-8">
                                    <input type="text" name="name" class="form-control" id="markerName" value="<%= marker.getName()%>" required placeholder="Name">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="markerAddress" class="col-sm-2 col-form-label">Adresse</label>
                                <div class="col-sm-8">
                                    <input type="text" name="address" class="form-control" id="markerAddress" value="<%= marker.getAddress()%>" required placeholder="Adresse">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="markerWebsite" class="col-sm-2 col-form-label">Webseite</label>
                                <div class="col-sm-8">
                                    <input type="text" name="website" class="form-control" id="markerWebsite" value="<%= marker.getWebsite()%>" required placeholder="Webseite">
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="markerDescription" class="col-sm-2 col-form-label">Beschreibung</label>
                                <div class="col-sm-8">
                                    <input type="text" name="description" class="form-control" id="markerDescription" value="<%= marker.getDescription()%>" required placeholder="Beschreibung"/>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="markerBusinessHours" class="col-sm-2 col-form-label">Öffnungszeiten</label>
                                <div class="col-sm-8">
                                    <input type="text" name="openingHours" class="form-control" id="markerBusinessHours" value="<%= marker.getOpeningHours()%>" required placeholder="Öffnungszeiten">
                                </div>
                            </div>

                            <div class="container mb-3">
                                <div class="row mb-1">
                                    <div class="mr-5">
                                        <label class="btn btn-light">
                                            <input type="checkbox" name="kitchenSelect" id="asian" value="asian" <%
                                                    if(marker.getKitchens().contains(Kitchen.ASIAN)) {
                                                        out.print("checked=\"checked\"");
                                                    }
                                                %>>
                                            Asiatische Küche
                                        </label>
                                    </div>
                                    <div class="mr-5">
                                        <label class="btn btn-light">
                                            <input type="checkbox" name="kitchenSelect" id="fastFood" value="fastFood" <%
                                                    if(marker.getKitchens().contains(Kitchen.FASTFOOD)) {
                                                        out.print("checked=\"checked\"");
                                                    }
                                                %>>
                                            Fast Food
                                        </label>
                                    </div>
                                </div>
                                <div class="row mb-1">
                                    <div class="mr-5">
                                        <label class="btn btn-light">
                                            <input type="checkbox" name="kitchenSelect" id="cafes" value="cafes" <%
                                                    if(marker.getKitchens().contains(Kitchen.CAFE)) {
                                                        out.print("checked=\"checked\"");
                                                    }
                                                %>>
                                            Cafés
                                        </label>
                                    </div>
                                    <div class="mr-5">
                                        <label class="btn btn-light">
                                            <input type="checkbox" name="kitchenSelect" id="european" value="european" <%
                                                    if(marker.getKitchens().contains(Kitchen.EUROPEAN)) {
                                                        out.print("checked=\"checked\"");
                                                    }
                                                %>>
                                            Europäische Küche
                                        </label>
                                    </div>
                                </div>
                                <div class="row mb-1">
                                    <div class="mr-5">
                                        <label class="btn btn-light">
                                            <input class="btn" type="checkbox" name="kitchenSelect" id="swiss" value="swiss" <%
                                                    if(marker.getKitchens().contains(Kitchen.SWISS)) {
                                                        out.print("checked=\"checked\"");
                                                    }
                                                %>>
                                                Schweizerische Küche
                                        </label>
                                    </div>
                                    <div class="mr-5">
                                        <label class="btn btn-light">
                                            <input type="checkbox" name="kitchenSelect" id="bars" value="bars" <%
                                                    if(marker.getKitchens().contains(Kitchen.BARS)) {
                                                        out.print("checked=\"checked\"");
                                                    }
                                                %>>
                                            Bars
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <fieldset class="form-group">
                                <div class="row">
                                    <label class="col-form-label col-sm-2 pt-0">Preisklasse</label>
                                    <div class="col-sm-8">
                                        <div class="form-check">                                       	
                                            <input class="form-check-input" type="radio" name="priceRange" id="cheap" value="cheap"
                                                <%
                                                    if(marker.getPrice() == Price.CHEAP || marker.getPrice() == null) {
                                                        out.print("checked=\"checked\"");
                                                    }
                                                %>>
                                            <label class="form-check-label" for="cheap">
                                                Billig
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="priceRange" id="medium" value="medium"
                                                <%
                                                    if(marker.getPrice() == Price.MEDIUM) {
                                                        out.print("checked=\"checked\"");
                                                    }
                                                %>>
                                            <label class="form-check-label" for="medium">
                                                Mittel
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="priceRange" id="expensive" value="expensive"
                                                <%
                                                    if(marker.getPrice() == Price.EXPENSIVE) {
                                                        out.print("checked=\"checked\"");
                                                    }
                                                %>>
                                            <label class="form-check-label" for="expensive">
                                                Teuer
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>

                            <div class="form-group row">
                                <div class="col-sm-2">Vegetarisch</div>
                                <div class="col-sm-8">
                                    <div class="form-check">
                                        <label for="vegetarianOption"></label><input id="vegetarianOption" class="form-check-input" type="checkbox" name="options" value="vegetarian"
                                            <%
                                                if(marker.isVegetarian()) {
                                                    out.print("checked=\"checked\"");
                                                }
                                            %>>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-2">Take-away</div>
                                <div class="col-sm-8">
                                    <div class="form-check">
                                        <label for="takeAwayOption"></label><input id="takeAwayOption" class="form-check-input" type="checkbox" name="options" value="takeAway"
                                            <%
                                                if(marker.isTakeAway()) {
                                                    out.print("checked=\"checked\"");
                                                }
                                            %>>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-2">Bild hochladen</div>
                                <div class="col-sm-8">
                                    <div class="form-group">
                                        <input type="file" class="form-control-file" id="bildHochladen">
                                    </div>
                                </div>
                            </div>

                            <div class="form-group row">
                                <div class="col-sm-8">
                                    <input type="submit" value="<%= submitText %>" name="submitButton">
                                </div>
                            </div>
                        </form>
                        <%
                            // Options
                            String[] selectedOptions = request.getParameterValues("options");
                            if (selectedOptions == null) {
                                selectedOptions = new String[0];
                            }

                            marker.setVegetarian(false);
                            marker.setTakeAway(false);
                            for (String selectedOption : selectedOptions) {
                                if ("vegetarian".equals(selectedOption)) {
                                    marker.setVegetarian(true);
                                }
                                if ("takeAway".equals(selectedOption)) {
                                    marker.setTakeAway(true);
                                }
                            }

                            //Prices
                            String selectedPrice = request.getParameter("priceRange");
                            if ("cheap".equals(selectedPrice)){
                                marker.setPrice(Price.CHEAP);
                            }
                            else if ("medium".equals(selectedPrice)){
                                marker.setPrice(Price.MEDIUM);
                            }
                            else if ("expensive".equals(selectedPrice)){
                                marker.setPrice(Price.EXPENSIVE);
                            }

                            //Kitchen Options
                            String[] selectedKitchen = request.getParameterValues("kitchenSelect");
                            if (selectedKitchen == null) {
                                selectedKitchen = new String[0];
                            }
                            List<Kitchen> kitchens = new ArrayList<>();

                            for (String s : selectedKitchen) {
                                if ("asian".equals(s)) {
                                    kitchens.add(Kitchen.ASIAN);
                                }
                                if ("fastFood".equals(s)) {
                                    kitchens.add(Kitchen.FASTFOOD);
                                }
                                if ("cafes".equals(s)) {
                                    kitchens.add(Kitchen.CAFE);
                                }
                                if ("european".equals(s)) {
                                    kitchens.add(Kitchen.EUROPEAN);
                                }
                                if ("swiss".equals(s)) {
                                    kitchens.add(Kitchen.SWISS);
                                }
                                if ("bars".equals(s)) {
                                    kitchens.add(Kitchen.BARS);
                                }
                            }
                            marker.setKitchens(kitchens);

                            String createMarker = request.getParameter("submitButton");
                            if(createMarker != null) {

                                markerController.doAction(action, marker, markerId, userId);
                                response.sendRedirect(request.getContextPath() + "/index.jsp");
                            }
                        %>
                    </div>
                </form>
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>