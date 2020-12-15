<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/styles.css" rel="stylesheet" type="text/css"/>
        <script defer src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" integrity="sha384-9/D4ECZvKMVEJ9Bhr3ZnUAF+Ahlagp1cyPC7h5yDlZdXs4DQ/vRftzfd+2uFUuqS" crossorigin="anonymous"></script>
        <title>JSP Page</title>
    </head>
    <c:set var="page" value="<%= request.getAttribute("page") != null ? (Integer)request.getAttribute("page") : 1 %>" />
    <c:set var="filterCompanyName" value="<%= request.getAttribute("filterCompanyName") != null ? (String)request.getAttribute("filterCompanyName") : "" %>" /> 
    <c:set var="filterGender" value="<%= request.getAttribute("filterGender") != null ? (String)request.getAttribute("filterGender") : "Both" %>" />
    <c:set var="filterName" value="<%= request.getAttribute("filterName") != null ? (String)request.getAttribute("filterName") : "" %>" />
    <c:set var="sortBy" value="<%= request.getAttribute("sortBy") != null ? (String)request.getAttribute("sortBy") : "sort_4" %>" />
    <c:set var="contacts" value="${sessionScope.contacts}" />
    <c:set var="favs" value="${sessionScope.favs}" />
    <body>
        <form method="POST" action="ContactController" id="form">
            <div class="container">
                <div class="search-container">
                    <input type="text" placeholder="Company name" name="filterCompanyName" value="${filterCompanyName}" />
                    <input type="text" placeholder="Name" name="filterName" value="${filterName}" />
                    <input type="text" name="delete" value="" hidden id="deleteInput" />
                    <input type="text" name="favs" value="" hidden id="favInput" />
                    <select name="filterGender">
                        <c:choose>
                            <c:when test="${filterGender == 'Male'}">
                                <option value="Both">Both</option>
                                <option value="Male" selected>Male</option>
                                <option value="Female">Female</option>
                            </c:when>
                            <c:when test="${filterGender == 'Female'}">
                                <option value="Both">Both</option>
                                <option value="Male">Male</option>
                                <option value="Female" selected>Female</option>
                            </c:when>
                            <c:otherwise>
                                <option value="Both" selected>Both</option>
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </c:otherwise>
                        </c:choose>
                    </select>
                    <div>
                        <span>Sort by</span>
                        <select name="sortBy">
                            <c:choose>
                                <c:when test="${sortBy == 'sort_1'}">
                                    <option value="sort_1" selected>Lastname, Firstname</option>
                                    <option value="sort_2">Company, Lastname, Firstname</option>
                                    <option value="sort_3">Age</option>
                                    <option value="sort_4">Id</option>
                                </c:when>
                                <c:when test="${sortBy == 'sort_2'}">
                                    <option value="sort_1">Lastname, Firstname</option>
                                    <option value="sort_2" selected>Company, Lastname, Firstname</option>
                                    <option value="sort_3">Age</option>
                                    <option value="sort_4">Id</option>
                                </c:when>
                                <c:when test="${sortBy == 'sort_3'}">
                                    <option value="sort_1">Lastname, Firstname</option>
                                    <option value="sort_2">Company, Lastname, Firstname</option>
                                    <option value="sort_3" selected>Age</option>
                                    <option value="sort_4">Id</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="sort_1">Lastname, Firstname</option>
                                    <option value="sort_2">Company, Lastname, Firstname</option>
                                    <option value="sort_3">Age</option>
                                    <option value="sort_4" selected>Id</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                    <button type="button" id="submitForm" class="apply" onclick="onSubmitForm()">Apply</button>
                    <button type="button" id="download" onclick="downloadFavourites()"><i class="fas fa-file-download"></i></button>
                    <button type="button" id="submitFormWithDelete" onclick="onSubmitWithDelete()" class="apply">Delete</button>
                </div>
                <div class="table-container">
                    <div class="table">
                        <section class="table-row table-header">
                            <span class="delete"></span>
                            <span class="id">
                                Id
                            </span>
                            <span class="name">
                                Name
                            </span>
                            <span class="gender">
                                Gender
                            </span>
                            <span class="email">
                                Email
                            </span>
                            <span class="birthdate">
                                Birthdate
                            </span>
                            <span class="company">
                                Company
                            </span>
                        </section>
                        <c:forEach var="i" begin="${(page - 1) * 10}" end="${(page - 1) * 10 + 9}">
                            <c:if test="${i < contacts.size()}">
                                <section class="table-row">
                                    <span class="delete">
                                        <c:set var="contains" value="false" />
                                        <c:forEach var="entry" items="${favs}">
                                            <c:if test="${entry.id == contacts[i].id}">
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <i class="fas fa-star fcon ${contains ? "fav-marked" : ""}" id="fav-${contacts[i].id}" onclick="onSwitchFav(${contacts[i].id})"></i>
                                        <i class="far fa-trash-alt del" id="delete-${contacts[i].id}" onclick="onSwitchDelete(${contacts[i].id})"></i>
                                    </span>
                                    <span class="id">
                                        ${contacts[i].id}
                                    </span>
                                    <span class="name">
                                        ${contacts[i].lastname} ${contacts[i].firstname}
                                    </span>
                                    <span class="gender">
                                        ${contacts[i].gender}
                                    </span>
                                    <span class="email">
                                        <c:forEach var="email" items="${contacts[i].email}" varStatus="count">
                                                <c:choose>
                                                    <c:when test="${count.index < contacts[i].email.size() - 1}">
                                                        <c:out value="${email}," />
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${email}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                    </span>
                                    <span class="birthdate">
                                        <c:out value="${contacts[i].birthdate} (${contacts[i].ageInYears})" />
                                    </span>
                                    <span class="company">
                                        <c:out value="${contacts[i].company.name} (${contacts[i].company.stockmarket})" />
                                    </span>
                                </section>
                            </c:if>
                        </c:forEach>
                        <div class="page-container">
                            <span>Page </span>
                            <select name="page" onchange="onSubmitForm()">
                                <c:forEach var="i" begin="1" end="${Math.ceil(contacts.size() / 10)}">
                                    <c:choose>
                                        <c:when test="${page == i}">
                                            <option value="${i}" selected>${i}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${i}">${i}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </body>
    <script src="js/index.js" type="text/javascript"></script>
</html>
