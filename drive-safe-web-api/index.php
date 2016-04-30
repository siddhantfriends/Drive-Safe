<!DOCTYPE html>
<html>
    <head>
        <title>Drive Safe - Home</title>
        <!-- Adding polyfill support. -->
        <script src="bower_components/webcomponentsjs/webcomponents-lite.min.js"></script>

        <!-- Import elements to be used -->
        <link rel="import" href="bower_components/paper-card/paper-card.html">
        <link rel="import" href="bower_components/google-map/google-map.html">
        <link rel="import" href="bower_components/geo-location/geo-location.html">

        <!-- css goes here -->
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body onload="getLocation()">
        <!-- Use elements -->
        <div id="top-bar">
            <h1>Drive Safe</h1>
        </div>
        <div id="map-area">
            <google-map id="location" latitude="{{ latitude }}" longitude="{{ longitude }}"></google-map>
        </div>

    </body>
    <script src="bower_components/jquery/dist/jquery.min.js">
</html>
