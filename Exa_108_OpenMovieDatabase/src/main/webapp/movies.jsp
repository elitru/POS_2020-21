<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <link href="movies.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container-fluid">
            <form action="MovieController" method="POST" id="form">
                <div class="container top">
                    <h1>Movie Database</h1>
                </div>
                <div class="container">
                    <h2>Search</h2>
                    <input id="details" name="detail" hidden type="text" value="" />
                    <div class="mb-3">
                        <label for="titleSearch" class="form-label">Nach Film suchen</label>
                        <input name="movieFilter" type="text" class="form-control" id="titleSearch" placeholder="James Bond" value="${requestScope.movieName}">
                        <button style="margin-top: 10px; width: 120px;" type="submit" class="btn btn-primary">Search</button>
                    </div>
                </div>
                <c:if test="${requestScope.movies != null}">
                    <div class="container">
                        <h2>Filter & Sorting</h2>
                        <select name="filter" class="form-select" aria-label="Sort by">
                            <c:choose>
                                <c:when test="${requestScope.filter == null || requestScope.filter == 'title'}">
                                    <option value="title" selected>Film-Title</option>
                                    <option value="year">Release Year</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="title">Film-Title</option>
                                    <option value="year" selected>Release Year</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                        <select style="margin-top: 20px;" name="genre" class="form-select" aria-label="Filter by genre">
                            <option value="_all">All</option>
                            <c:forEach var="genre" items="${requestScope.genres}">
                                <c:choose>
                                    <c:when test="${genre == requestScope.genre}">
                                        <option value="${genre}" selected>${genre}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${genre}">${genre}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <button style="margin-top: 10px; width: 120px;" type="button" onclick="submitForm()" class="btn btn-primary">Apply</button>
                    </div>
                </c:if>

                <c:if test="${requestScope.movies != null}">
                    <div class="container" style="margin-top: 20px;">
                        <h2>Results</h2>
                        <table class="table">
                            <thead class="table-dark">
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Movie Name</th>
                                    <th scope="col">Release Year</th>
                                    <th scope="col">Genres</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="movie" items="${requestScope.movies}">
                                    <tr onclick="onDetail('${movie.imdbID}')">
                                        <th scope="row">${movie.imdbID}</th>
                                        <td>${movie.title}</td>
                                        <td>${movie.year}</td>
                                        <td>${movie.genre}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <select style="margin-top: 20px;" name="page" class="form-select" aria-label="Filter by genre" onchange="submitForm();">
                            <c:forEach begin="1" end="${Math.ceil(requestScope.totalResults / 10)}" var="i" step="1">
                                <c:choose>
                                    <c:when test="${i == requestScope.page}">
                                        <option value="${i}" selected>${i}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${i}">${i}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </c:if>
            </form>
        </div>
        <c:if test="${requestScope.details != null}">
            <div class="details-container" id="details-container">
                <div class="boxi">
                    <span onclick="closeDetails()" class="close">✘</span>
                    <div class="container-fluid">
                        <h2>${requestScope.details.title}</h2>
                        <img src="${requestScope.details.poster}" style="margin: 20px 0px;" />
                        <p><b>Released:</b> ${requestScope.details.released}</p>
                        <p><b>Genre(s):</b> ${requestScope.details.genre}</p>
                        <p><b>Awards:</b> ${requestScope.details.awards}</p>
                        <p><b>Plot:</b> ${requestScope.details.plot}</p>
                        <p><b>Director:</b> ${requestScope.details.director}</p>
                        <p><b>Rating:</b> ${requestScope.details.rated}</p>
                        <p><b>Production:</b> ${requestScope.details.production}</p>
                    </div>
                </div>
            </div>
        </c:if>
        <script>
            const onDetail = (id) => {
                document.getElementById("details").value = id;
                submitForm();
            };

            const submitForm = () => {
                document.getElementById("form").submit();
            };

            const closeDetails = () => {
                document.getElementById("details-container").style.display = "none";
            };
        </script>
    </body>
</html>
