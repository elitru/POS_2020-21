<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="style/weather.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form id="form" method="POST" action="./WeatherController" onsubmit="return onSubmit();">
            <div class="top-container">
                    <section class="input-container">
                        <div>
                            <span>${lang == 'en' ? "Language" : "Sprache"}</span>
                            <select name="lang" onchange="onChangeLanguage()">
                                <c:choose>
                                    <c:when test="${lang == 'de'}">
                                        <option value="de" selected>Deutsch</option>
                                        <option value="en">English</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="de">Deutsch</option>
                                        <option value="en" selected>English</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                    </section>
                    <section class="input-container">
                        <div>
                            <span>${lang == 'en' ? "City" : "Stadt"}</span>
                            <input id="city" name="city" value="${city}" placeholder="Kaindorf" />
                            <span id="cityErr" class="error">You must specify a location</span>
                        </div>
                    </section>
                    <button>
                        ${lang == 'en' ? "Get weather data" : "Wetterdaten abrufen"}
                    </button>
            </div>
        </form>
        <c:if test="${notFound != null}">
            <p class="not-found">${notFound}</p>
        </c:if>
        <c:if test="${loc != null}">
            <div class="info-container">
                <section class="info-block">
                    <h2>
                        ${lang == 'de' ? "Stadt" : "City"} (${loc.city.name})
                    </h2>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Coordinates (Lo, La)" : "Koordinaten (L, B)"}: </b> ${loc.city.coord.lon}, ${loc.city.coord.lat}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Country" : "Land"}: </b> ${loc.city.country}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Timezone" : "Zeitzone"}: </b> ${loc.city.timezone}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Sunrise" : "Sonnenaufgang"}: </b> ${loc.city.sun.rise}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Sunset" : "Sonnenuntergang"}: </b> ${loc.city.sun.set}</span>
                    </div>
                </section>
                <section class="info-block">
                    <h2>
                        ${lang == 'de' ? "Wetterdaten" : "Weather info"}
                    </h2>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Temperature (Cur, Min, Max)" : "Temperatur (Akt, Min, Max)"}: </b> ${loc.temperature.value} °C, ${loc.temperature.min} °C, ${loc.temperature.max} °C</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Humidity" : "Luftfeuchtigkeit"}: </b> ${loc.humidity.value}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Pressure" : "Luftdruck"}: </b> ${loc.pressure.value}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Wind speed" : "Windgeschwindigkeit"}: </b> ${loc.wind.speed.value} m/s (${loc.wind.speed.name})</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Wind direction" : "Windrichtung"}: </b> ${loc.wind.direction.value} ${loc.wind.direction.name}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Clouds" : "Wolken"}: </b> ${loc.clouds.value} (${loc.clouds.name})</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Visibility" : "Sichtbarkeit"}: </b> ${loc.visibility.value}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${lang == 'en' ? "Last update" : "Letzte Aktualisierung"}: </b> ${loc.lastupdate.value}</span>
                    </div>
                </section>
            </div>
        </c:if>
        <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>
