<%@ page contentType="text/html; charset=ISO-8859-15" pageEncoding="UTF-8"%>
<%@ page import="ch.hungrystudent.domain.Kitchen" %>
<%@ page import="ch.hungrystudent.domain.Price" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ch.hungrystudent.domain.Marker" %>
<%@ page import="ch.hungrystudent.domain.Review" %>
<%@ page import="java.util.logging.Level" %>
<%@ page import="java.util.logging.Logger" %>
<jsp:useBean id="mapController" scope="request" class="ch.hungrystudent.controller.MapController"/>
<jsp:useBean id="markerController" scope="request" class="ch.hungrystudent.controller.MarkerController"/>
<jsp:useBean id="userController" scope="session" class="ch.hungrystudent.controller.UserController"/>

<!doctype html>
<html class="no-js" lang="de-CH">

<head>
    <meta charset="utf-8">
    <title>Hungry Student</title>
    <!-- Leaflet -->
    <script src="https://d3js.org/d3.v3.min.js"></script>
    <script src="https://d3js.org/topojson.v0.min.js"></script>
    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.5.1/dist/leaflet.css"
          integrity="sha512-xwE/Az9zrjBIphAcBb3F6JVqxf46+CDLwfLMHloNu6KEQCAWi6HcDUbeOfBIptF7tcCzusKFjFw2yuvEpDL9wQ=="
          crossorigin=""/>
    <script src="https://unpkg.com/leaflet@1.5.1/dist/leaflet.js"
            integrity="sha512-GffPMF3RvMeYyc1LWMHtK8EbPv0iNZ8/oTtHPx9/cc2ILxQ+u905qIwdpULaqDkyBKgOaB57QTMg7ztg8Jm2Og=="
            crossorigin=""></script>

    <link rel="icon" href="css/img/Favicon_HungryStudent_Logo.ico"/>
    <!-- CSS from Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/index.css">
    <!-- JS, jquery, popper from bootstrap -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>

    <script src="js/main.js"></script>
</head>
<body>
<div id="mapid"></div>
<%
    Logger logger = Logger.getLogger("index.jsp");
    boolean shouldDisplayMarker = request.getParameter("marker") != null;
    long markerId = -1;
    if(shouldDisplayMarker) {
    	try {
        markerId = Long.valueOf(request.getParameter("marker"));
    	} 
    	catch (NumberFormatException e) {	
    		logger.log(Level.SEVERE, "Couldn't convert int to string", e);
    		shouldDisplayMarker = false;
    	}
    }

    long userId = -1;
    if(userController.getUser() != null) {
        userId = userController.getUser().getId();
    }
    String action = "";
    if(request.getParameter("action") != null) {
        action = request.getParameter("action");
    }
    if("remove".equals(action)) {
        shouldDisplayMarker = false;
    }

    markerController.doAction(action, markerId, userId);

    // handling review submissions
    if("review".equals(action)) {
        String reviewText = request.getParameter("reviewText");
        markerController.writeReview(userId, markerId, reviewText);
    }

    // loads coordinates to display
    // default coordinates are at ZHAW in Zurich
    String coordinates = "47.37761,8.53273";
    if(request.getParameter("coordinates") != null) {
        coordinates = request.getParameter("coordinates");
    }

    // loads every marker to the map
    mapController.loadMarkers();

    // loads favourite markers from user
    userController.loadFavouriteMarkers();
    List<Marker> favouritedMarkers = userController.getFavouriteMarkers();
    
%>
<div class="wrapper">
    <div id="sidebar" data-spy="scroll" class="closed">
        <jsp:useBean id="filterMarker" scope="session"
                     class="ch.hungrystudent.domain.Marker"/>
        <jsp:setProperty name="filterMarker" property="*"/>
        <%
            request.setCharacterEncoding("UTF-8");

     		// Options
            String[] selectedOptions = request.getParameterValues("options");
            if (selectedOptions == null) {
                selectedOptions = new String[0];
            }

            filterMarker.setVegetarian(false);
            filterMarker.setTakeAway(false);
            for (String selectedOption : selectedOptions) {
                if ("vegetarian".equals(selectedOption)) {
                    filterMarker.setVegetarian(true);
                }
                if ("takeAway".equals(selectedOption)) {
                    filterMarker.setTakeAway(true);
                }
            }

          	//Prices
            String selectedPrice = request.getParameter("priceRange");
            if ("cheap".equals(selectedPrice)){
                filterMarker.setPrice(Price.CHEAP);
            }
            else if ("medium".equals(selectedPrice)){
                filterMarker.setPrice(Price.MEDIUM);
            }
            else if ("expensive".equals(selectedPrice)){
                filterMarker.setPrice(Price.EXPENSIVE);
            }

          	//Kitchen Options
            String[] selectedKitchen = request.getParameterValues("kitchenFilter");
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
            filterMarker.setKitchens(kitchens);

            String filterMarkers = request.getParameter("filterButton");
            if(filterMarkers != null) {

                mapController.doAction("filter", filterMarker);
            }
        %>
        <form method="GET"
              action="${pageContext.request.contextPath}/index.jsp" role="form"
              data-toggle="validator">
            <input type="hidden" name="action" value="filter">
            <input type="hidden" name="coordinates" value="<%=coordinates%>">

            <!-- title -->
            <div class="sidebar-header">
                <h3 class="font-weight-bold mt-3 mr-1 float-left">Hungry
                    Student</h3>
                <img class="icon" src="css/img/IconTab_100x100px_HungryStudent.ico"
                     alt="HungryStudentIconTab">
            </div>

            <hr class="style1">

            <!-- Dropdown -->
            <div class="form-inline">
                <label for="standardSchool" class="mr-2">Schule</label>
                <select id="standardSchool" name="school" class="form-control"
                        onchange="this.options[this.selectedIndex].value
                        && (window.location = this.options[this.selectedIndex].value);">
                    <option>Bitte wählen...</option>
                    <option value="${pageContext.request.contextPath}/index.jsp?coordinates=47.37761,8.53273">ZHAW Zürich</option>
                    <option value="${pageContext.request.contextPath}/index.jsp?coordinates=47.49711,8.72991">ZHAW Winterthur</option>
                    <option value="${pageContext.request.contextPath}/index.jsp?coordinates=current">Aktueller Standort</option>
                </select>
            </div>

            <hr class="style1">

            <h4>Filter</h4>
            <!-- Checkboxes -->
            <div class="mb-2">
                <div class="custom-control custom-checkbox mb-2">
                    <input id="vegetarian" class="checkbox float-left" type="checkbox"
                           name="options" value="vegetarian"
                           <%
                             if(filterMarker.isVegetarian()) {
                                 out.print("checked=\"checked\"");
                             }
                            %>>
                    <label class="float-right h6" for="vegetarian">Vegetarisch</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input id="takeAway" class="checkbox float-left" type="checkbox"
                           name="options" value="takeAway"
                           <%
                             if(filterMarker.isTakeAway()) {
                                 out.print("checked=\"checked\"");
                             }
                            %>>
                    <label class="float-right h6"
                           for="takeAway">Take-away</label>
                </div>
            </div>

            <!-- Price -->
            <div class="container">
                <fieldset class="form-group">
                    <div class="row">
	                    <label class="col-form-label col-sm-2 pt-0">Preisklasse</label>
                        <div class="col-md-12">
                            <div class="form-check float-left mr-3">
                                <input id="cheap" class="form-check-input" type="radio" name="priceRange" value="cheap"
                                    <%
                                        if(filterMarker.getPrice() == Price.CHEAP || filterMarker.getPrice() == null) {
                                            out.print("checked=\"checked\"");
                                        }
                                    %>>
                                <label class="form-check-label" for="cheap">
                                    Billig
                                </label>
                            </div>
                            <div class="form-check float-left mr-3">
                                <input id="medium" class="form-check-input" type="radio" name="priceRange" value="medium"
                                    <%
                                        if(filterMarker.getPrice() == Price.MEDIUM) {
                                            out.print("checked=\"checked\"");
                                        }
                                    %>>
                                <label class="form-check-label" for="medium">
                                    Mittel
                                </label>
                            </div>
                            <div class="form-check float-left mr-3">
                                <input id="expensive" class="form-check-input" type="radio" name="priceRange" value="expensive"
                                    <%
                                        if(filterMarker.getPrice() == Price.EXPENSIVE) {
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
            </div>
            <div class="container mb-1">
                <div class="row">
                    <div class="mr-1">
                        <label class="btn btn-light">
                            <input type="checkbox" name="kitchenFilter" value="asian" <%
                                    if(filterMarker.getKitchens().contains(Kitchen.ASIAN)) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>>
                            Asiatische Küche
                        </label>
                    </div>
                    <div class="mr-1">
                        <label class="btn btn-light">
                            <input type="checkbox" name="kitchenFilter" value="bars" <%
                                    if(filterMarker.getKitchens().contains(Kitchen.BARS)) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>>
                            Bars
                        </label>
                    </div>
                </div>
                <div class="row">
                    <div class="mr-1">
                        <label class="btn btn-light">
                            <input type="checkbox" name="kitchenFilter" value="cafes" <%
                                    if(filterMarker.getKitchens().contains(Kitchen.CAFE)) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>>
                            Cafés
                        </label>
                    </div>
                    <div class="mr-1">
                        <label class="btn btn-light">
                            <input type="checkbox" name="kitchenFilter" value="european" <%
                                    if(filterMarker.getKitchens().contains(Kitchen.EUROPEAN)) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>>
                            Europäische Küche
                        </label>
                    </div>
                </div>
                <div class="row">
                    <div class="mr-1">
                        <label class="btn btn-light">
                            <input class="btn" type="checkbox" name="kitchenFilter" value="swiss" <%
                                    if(filterMarker.getKitchens().contains(Kitchen.SWISS)) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>>
                                Schweizerische Küche
                        </label>
                    </div>
                    <div class="mr-1">
                        <label class="btn btn-light">
                            <input type="checkbox" name="kitchenFilter" value="fastFood" <%
                                    if(filterMarker.getKitchens().contains(Kitchen.FASTFOOD)) {
                                        out.print("checked=\"checked\"");
                                    }
                                %>>
                            Fast Food
                        </label>
                    </div>
                </div>
            </div>

            <input class="btn btn-secondary mb-3" type="submit" value="Filter" name="filterButton">

            <!-- Buttons -->
            <div class="list-group">
                <a id="favouritePageButton" href="pages/favoriten.jsp" class="list-group-item list-group-item-action list-group-item-warning font-weight-bold text-dark">
                    Gespeicherte Markierungen
                </a>
                <a id="savedPageButton" href="pages/markierungen.jsp" class="list-group-item list-group-item-action list-group-item-danger font-weight-bold text-dark">
                    Erstellte Markierungen
                </a>
            </div>
            <hr class="style1">

            <div class="float-right">
                <!-- About button -->
                <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#aboutModalBox">
                    About Us
                </button>
            </div>
        </form>


    </div>

    <jsp:useBean id="displayMarker" scope="request"
                 class="ch.hungrystudent.domain.Marker"/>
    <jsp:setProperty name="displayMarker" property="*"/>
    <%
        // loads marker to display on
        List<Review> reviews = new ArrayList<>();
        if(shouldDisplayMarker) {
        	
                displayMarker = markerController.getMarkerById(markerId);
                reviews = markerController.getReviewsOfMarker(markerId);
            } 

        boolean isFavourite = false;
        for(Marker marker : favouritedMarkers) {
            if(marker.getId() == displayMarker.getId()) {
                isFavourite = true;
            }
        }
    %>
    <div id="sidebar-marker"
         <%
             if(!shouldDisplayMarker) {
                 out.print("class=\"closed\"");
             }
         %>>
        <div class="row">
            <button id="closeMarkerSideBar" class="btn btn-secondary ml-3">Close</button>
            <form method="POST"
                  action="${pageContext.request.contextPath}/pages/neuemarkierung.jsp"
                  role="form">
                <input id="markerEditButton" class="btn btn-info ml-2" type="submit" value="Edit">


                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="marker" value="<%= displayMarker.getId()%>">
            </form>

            <form method="POST"
                  action="${pageContext.request.contextPath}/index.jsp"
                  role="form">

                <input id="markerRemoveButton" class="btn btn-danger ml-2" type="submit" value="Remove">
                <input type="hidden" name="action" value="remove">
                <input type="hidden" name="marker" value="<%= displayMarker.getId()%>">
            </form>
        </div>

        <!-- Title -->
        <div class="sidebar-header">
            <h4 id="markerName" class="font-weight-bold mt-3 mr-1"><%=displayMarker.getName()%></h4>
        </div>
        <hr class="style1">
        <!-- Image & Description -->
        <img class="w-75" src="css/img/noImgAvailable.png" alt="No Image Available">
        <p id="markerDescription" class="border border-black mt-2"><%=displayMarker.getDescription()%></p>

        <!-- Address -->
        <div class="row">
            <img id="addressIcon" class="ml-3" src="css/img/marker-icon-red.png" alt="Address">
            <p id="markerAddress" class="ml-1"><%=displayMarker.getAddress()%></p>
        </div>

        <!-- Boxes -->
        <div class="marker-boxes">

            <label class="iconButton btn border border-dark">
                <a id="markerWebsiteButton" href="//<%=displayMarker.getWebsite()%>">
                    <img class="iconImage" src="css/img/Web_icon.ico" alt="Web">
                </a>
            </label>

            <form method="POST"
                  action="${pageContext.request.contextPath}/index.jsp"
                  role="form">
                <label class="iconButton btn border border-dark">
                    <input id="markerFavouriteButton"
                           class="iconImage"
                           type="image"
                           name="marker"
                           src="<%=isFavourite?"css/img/emptyStar.png":"css/img/starIcon.png"%>" alt="Favourite icon">
                </label>
                <input type="hidden" name="marker" value="<%= displayMarker.getId()%>">
                <input type="hidden" name="action" value="<%=isFavourite?"unfavourite":"favourite"%>">
            </form>
        </div>

        <hr class="style1">

        <div id="kitchen">
            <div class="btn-light btn-sm w-100 border border-dark">
                <%
                    for (Kitchen kitchen : displayMarker.getKitchens()) {
                %>
                <p><%=kitchen.getValue()%></p>
                <%
                    }
                %>
            </div>
        </div>
        <div>
            <%
                Price price = displayMarker.getPrice();
                if(price == null) {
                    price = Price.MEDIUM;
                }
            %>
            <p id="markerPrice">Preisklasse: <%=price.getValue()%></p>
        </div>

        <!-- Options -->
        <div>
            <ul>
                <li id="markerVegetarian">Vegetarisch: <%=displayMarker.isVegetarian()?"Ja":"Nein"%></li>
                <li id="markerTakeAway">Take Away: <%=displayMarker.isTakeAway()?"Ja":"Nein"%></li>
            </ul>
        </div>

        <hr class="style1">

        <!-- Opening Hours -->
        <div>
            <h5>Öffnungszeiten</h5>
            <p id="markerOpeningHours"><%=displayMarker.getOpeningHours()%>
            </p>
        </div>

        <hr class="style1">

        <!-- Review section -->
        <h5>Rezensionen</h5>
        <%
            for(Review review : reviews) {
        %>
        <div class="border border-black">
            <p class="font-weight-bold mb-0 mt-2"><%=review.getUsername()%></p>
            <p><%=review.getReviewText()%></p>
        </div>
        <%
            }
        %>

        <%
            if(userId != -1) {
        %>
        <form method="POST"
              action="${pageContext.request.contextPath}/index.jsp?marker=<%=markerId%>"
              role="form">
            <label for="reviewField"></label><textarea id="reviewField"
                                                       class="form-control"
                                                       name="reviewText"
                                                       placeholder="Teile Ihre Erfahrungen zu diesem Ort..."
                                                       rows="4"></textarea>
            <input type="submit"
                   name="submitReview"
                   class="list-group-item list-group-item-action list-group-item-success font-weight-bold text-dark"
                   value="Rezension erfassen">
            <input type="hidden" name="marker" value="<%= displayMarker.getId()%>">
            <input type="hidden" name="action" value="review">
        </form>
        <%
            }
            else {
        %>
        <p>Sie müssen eingeloggt sein, damit Sie eine Rezension schreiben dürfen.</p>
        <%
            }
        %>
    </div>

    <!-- About us modal -->
    <jsp:include page="/WEB-INF/jsp/aboutUs.jsp" />

    <!-- displays where the map should disploy on load -->
    <div id="location" hidden data-current-coordinates="<%=coordinates%>"></div>

    <!-- Page Content -->
    <div id="content">
        <div id="menuPart" class="position-fixed">
            <div class="input-group">
                <button id="menuButton" type="button" class="btn btn-secondary btn-sm mr-2">
                    <img id="menuImage" src="css/img/menu-icon.png" alt="Menu">
                </button>

                <form method="GET"
                      action=""
                      role="form">
                    <%
                        String searchInput = request.getParameter("searchBar");
                        if(searchInput != null) {

                            mapController.doAction("search", null,  searchInput);
                        } else {
                            searchInput = "";
                        }
                    %>
                    <label for="searchBar"></label>
                    <input id="searchBar" type="text" name="searchBar" value="<%=searchInput%>">
                    <input class="btn btn-primary btn-lg" type="submit" value="Suchen" name="searchButton">
                </form>
            </div>
        </div>

        <div id="loginSpace">
            <% if(userController.getUser() != null && userController.getUser().getId() != -1){ %>
            <div>
                <div id="logoutSpace">
                    <a href="pages/login.jsp?action=logout">Logout</a>
                </div>
                <a href="pages/benutzereinstellungen.jsp">Benutzer Einstellungen</a>
            </div>
            <%}

            else{ %>

            <a href="pages/login.jsp">Login</a>
            /
            <a href="pages/registrierung.jsp">Registrieren</a>

            <%}%>
        </div>

        <div id="mapButtons" class="d-none">
            <a class="btn btn-primary active createMarkerButton" href="pages/neuemarkierung.jsp" role="button">
                Markierung erstellen
            </a>
        </div>
    </div>

    <!-- displays id and coordinates of every marker for js -->
    <div id="markerValues">
        <%
            for(Marker marker : mapController.getMarkers()) {
        %>
        <div class="markerData" hidden="false"
             data-marker-id="<%=marker.getId()%>"
             data-marker-coord="<%=marker.getLocation()%>"></div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>