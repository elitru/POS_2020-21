<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="at.eliastrummer.beans.DaysOfWeek;" %>
<!DOCTYPE html>
<html>
    <c:set var="daysOfWeek" value="<%=DaysOfWeek.values()%>" />
    <c:set var="rows" value="${applicationScope.rowLessons}" />
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/styles.css" rel="stylesheet" type="text/css" />
        <title>Supplierplan Verwaltung</title>
    </head>
    <body>
        <div class="box-container">
            <div class="editor-container">
                <h1>Stundenplanverwerwaltung</h1>
                <h2>${applicationScope.class}</h2>
                <c:if test="${requestScope.supplementError != null}">
                    <h3 class="error">${requestScope.supplementError}</h3>
                </c:if>
                <form method="POST" onsubmit="return isValid();">
                    <section class="input-section">
                        <span class="title">Tag</span>
                        <select id="dayOfWeek" name="dayOfWeek">
                            <c:forEach var="dayOfWeek" items="${daysOfWeek}">
                                <option value=${dayOfWeek.id}>${dayOfWeek.name}</option>
                            </c:forEach>
                        </select>
                    </section>
                    <section class="input-section">
                        <span class="title">Stunde</span>
                        <select if="lesson" name="lesson">
                            <c:forEach var="counter" begin="1" end="10">
                                <option value=${counter}>${counter}</option>
                            </c:forEach>
                        </select>
                    </section>
                    <section class="input-section">
                        <span class="title">Fach</span>
                        <input type="text" placeholder="POS" name="subject"  id="subject" class="input" />
                        <span id="subject-error" class="error"></span>
                    </section>
                    <section class="input-section">
                        <span class="title">Lehrer</span>
                        <input type="text" placeholder="SF, TD, ..." name="teachers" id="teacher" class="input" />
                        <span id="teachers-error" class="error"></span>
                    </section>
                    <section class="input-section">
                        <button type="submit">Übernehmen</button>
                    </section>
                </form>
            </div>
            <div class="lessons-container">
                <div class="table-container">
                    <div class="table-row">
                        <section class="lesson table-header"></section>
                        <section class="mon table-header">Mo</section>
                        <section class="tue table-header">Di</section>
                        <section class="wed table-header">Mi</section>
                        <section class="thu table-header">Do</section>
                        <section class="fir table-header">Fr</section>
                    </div>
                    <!--<div class="table-row">
                        <section class="lesson table-header">1</section>
                        <section class="tue ">
                            <span>D</span>
                            <span>SB</span>
                        </section>
                        <section class="mon">
                            <span>D</span>
                            <span>SB</span>
                        </section>
                        <section class="wed">
                            <span>D</span>
                            <span>SB</span>
                        </section>
                        <section class="thu">
                            <span>D</span>
                            <span>SB</span>
                        </section>
                        <section class="fir">
                            <span>D</span>
                            <span>SB</span>
                        </section>
                    </div>-->
                    <c:forEach var="row" items="${rows}" varStatus="lesson">
                        <div class="table-row">
                            <section class="lesson table-header">${lesson.index + 1}</section>
                            <c:forEach var="lessonEntry" items="${row.lessons}">
                                <section class="${lessonEntry.supplement ? "supplement" : ""}">
                                    <span>${lessonEntry.subject}</span>
                                    <span>
                                        <c:forEach var="teacher" items="${lessonEntry.teachers}" varStatus="teacherCount">
                                            <c:choose>
                                                <c:when test="${teacherCount.index < lessonEntry.teachers.size() - 1}">
                                                    <c:out value="${teacher.abbreviation}," />
                                                </c:when>
                                                <c:otherwise>
                                                    ${teacher.abbreviation}
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </span>
                                </section>
                            </c:forEach>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
    <script src="js/index.js" type="text/javascript"></script>
</html>
