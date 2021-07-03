// functions for the sidebar
$(document).ready(function () {

    $('#menuButton').on('click', function () {
        // open or close navbar
        $('#sidebar').toggleClass('closed');
    });

    $('#closeMarkerSideBar').on('click', function () {
        // open or close marker-bar
        $('#sidebar-marker').toggleClass('closed');
    });
});

// functions for leaflet map
navigator.geolocation.getCurrentPosition(function (location) {

    var latlng = new L.LatLng(location.coords.latitude, location.coords.longitude);

    var locationTag = document.getElementById("location");
    var currentLocation = locationTag.getAttribute("data-current-coordinates");

    // noinspection Annotator,Annotator
    var pattern = new RegExp("[\d.\d,\d.\d]");
    if(pattern.test(currentLocation)) {
        var locationArray = currentLocation.split(",");
        var result = locationArray.map(function (x) {
            return parseFloat(x);
        });
        latlng = new L.LatLng(result[0], result[1]);
    }

    var mymap = L.map('mapid').setView(latlng, 16);

    var btn = document.getElementById("mapButtons");

    /*roter Icon für aktuellen Standort */
    var redIcon = new L.Icon({
        iconUrl: 'css/img/marker-icon-red.png',
        shadowUrl: 'css/img/marker-shadow.png',
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });

    L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
        attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery &copy; <a href="https://mapbox.com">Mapbox</a>',
        maxZoom: 18,
        zoomControl: true,
        id: 'mapbox.streets',
        accessToken: 'pk.eyJ1IjoiYmJyb29rMTU0IiwiYSI6ImNpcXN3dnJrdDAwMGNmd250bjhvZXpnbWsifQ.Nf9Zkfchos577IanoKMoYQ'
    }).addTo(mymap);

    /*Erstellt marker für den aktuellen Standort*/
    var actualLocationMarker = L.marker((latlng),{icon: redIcon}).addTo(mymap);
    actualLocationMarker.bindPopup("<b>Aktueller Standort</b>");

    /*Erstellt neue marker für die Einträge aus der Datenbank*/
    var markerData = document.getElementsByClassName("markerData");
    Array.prototype.forEach.call(markerData, function(element) {

        var markerId = element.getAttribute("data-marker-id");

        var koordString = element.getAttribute("data-marker-coord");
        var koordArr = koordString.split(",");

        var result = koordArr.map(function (x) {
            return parseFloat(x);
        });

        newmarker = new L.marker([result[ 0 ],result[ 1 ]],{alt: 'markerNr'.concat(markerId)})
            .on('click', onMarkerClick)
            .addTo(mymap);

        function onMarkerClick(e) {
            // open or close marker-bar
            $('#sidebar-marker').toggleClass('closed');
            var url = new URL(window.location.href);
            var searchParams = new URLSearchParams(url.search);

            // if marker id is already in url
            if(searchParams.get('marker') != null) {
                searchParams.set('marker', markerId);
            }
            else {
                // append new parameter to url
                searchParams.append('marker', markerId);
            }

            // redirect to same page with new parameter
            url.search = searchParams.toString();
            window.location.replace(url.toString());
        }
    });

    mymap.zoomControl.setPosition('bottomright');

    /* Popup for new marker*/
    var popup = L.popup();
    function onMapClick(e) {
        // retrieves the current url
        var link = btn.getElementsByTagName("a")
            .item(0)
            .getAttribute("href");
        // removes parameter if it is already in url
        if(link.includes("?coordinates=")) {
            link = link.split("?")[0];
        }
        // adds coordinates to url
        btn.getElementsByTagName("a")
            .item(0)
            .setAttribute("href",
                link + "?coordinates=" +
                e.latlng.lat.toFixed(5) + "," +
                e.latlng.lng.toFixed(5));

        popup.setLatLng(e.latlng)
            .setContent(btn.innerHTML)
            .openOn(mymap);
    }
    mymap.on('click', onMapClick);
});

