<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="translationService" class="at.eliastrummer.utils.TranslationService"/>
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
                            <span>${translationService.translate(lang, "lang")}</span>
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
                            <span>${translationService.translate(lang, "city")}</span>
                            <input id="city" name="city" value="${city}" placeholder="Kaindorf" />
                            <span id="cityErr" class="error">You must specify a location</span>
                        </div>
                    </section>
                    <button>
                        ${translationService.translate(lang, "getdata")}
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
                        ${translationService.translate(lang, "city")} (${loc.city.name})
                    </h2>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "cord")}: </b> ${loc.city.coord.lon}, ${loc.city.coord.lat}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "country")}: </b> ${loc.city.country}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "timezone")}: </b> ${loc.city.timezone}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "sunrise")}: </b> ${loc.city.sun.rise}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "sunset")}: </b> ${loc.city.sun.set}</span>
                    </div>
                </section>
                <section class="info-block">
                    <h2>
                        ${translationService.translate(lang, "info")}
                    </h2>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "temp")}: </b> ${loc.temperature.value} °C, ${loc.temperature.min} °C, ${loc.temperature.max} °C</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "hum")}: </b> ${loc.humidity.value}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "pre")}: </b> ${loc.pressure.value}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "windspe")}: </b> ${loc.wind.speed.value} m/s (${loc.wind.speed.name})</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "winddir")}: </b> ${loc.wind.direction.value} ${loc.wind.direction.name}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "clouds")}: </b> ${loc.clouds.value} (${loc.clouds.name})</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "vis")}: </b> ${loc.visibility.value}</span>
                    </div>
                    <div class="info-row">
                        <span><b>${translationService.translate(lang, "lastup")}: </b> ${loc.lastupdate.value}</span>
                    </div>
                </section>
            </div>
        </c:if>
        <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>
