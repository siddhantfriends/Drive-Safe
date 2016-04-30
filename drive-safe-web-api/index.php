<!DOCTYPE html>
<html>
    <head>
        <title>Drive Safe - Home</title>
        <!-- Adding polyfill support. -->
        <script src="bower_components/webcomponentsjs/webcomponents-lite.min.js"></script>

        <!-- Import elements to be used -->
        <link rel="import" href="bower_components/paper-card/paper-card.html">
        <link rel="import" href="bower_components/google-map/google-map.html">

        <!-- css goes here -->
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <!-- Use elements -->
        <div id="top-bar">
            <h1>Drive Safe</h1>
        </div>
        <div id="map-area">
            <google-map id="map" latitude="37.790" longitude="-122.390" zoom="17" >
                <google-map-marker id="map-maker" latitude="37.77493" longitude="-122.41942" icon="images/icon.png" draggable="false"></google-map-marker>
            </google-map>
        </div>

    </body>
    <script src="bower_components/jquery/dist/jquery.min.js"></script>
    <script>
    $(document).ready(getLocation());

    function getLocation() {
        if(navigator.geolocation)
            navigator.geolocation.getCurrentPosition(handleGetCurrentPosition, onError);

        function handleGetCurrentPosition(location){
            $('#map').attr('latitude', location.coords.latitude);
            $('#map').attr('longitude', location.coords.longitude);

            $('#map-maker').attr('latitude', location.coords.latitude);
            $('#map-maker').attr('longitude', location.coords.longitude);
        }

        function onError(){
        }

        setTimeout(function() {
            getLocation();
        }, 500);
    }

    </script>
</html>
