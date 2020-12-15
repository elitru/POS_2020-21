package at.eliastrummer.moviedb.controller;

import at.eliastrummer.moviedb.bl.Movie;
import at.eliastrummer.moviedb.bl.MovieHandler;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.eliastrummer.moviedb.bl.MovieResult;

@WebServlet(name = "MovieController", urlPatterns = {"/MovieController"})
public class MovieController extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("page", 1);
        request.setAttribute("filter", "title");
        request.getRequestDispatcher("movies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String movie = (String) request.getParameter("movieFilter");
        int page = Integer.parseInt(request.getParameter("page") == null ? "1" : request.getParameter("page"));

        if (movie == null) {
            movie = "";
        }

        MovieResult result = MovieHandler.getMovies(movie, page);

        List<Movie> movies = result.getMovies();

        String filter = request.getParameter("filter");
        String genre = request.getParameter("genre");

        try {
            if (filter == null || filter.equalsIgnoreCase("title")) {
                movies.sort(Comparator.comparing(Movie::getTitle));
            } else {
                movies.sort(Comparator.comparing(Movie::getYear));
            }

            if (genre != null && !genre.equalsIgnoreCase("_all")) {
                movies.removeIf(m -> !m.getGenre().toLowerCase().contains(genre.toLowerCase()));
            }
        } catch (NullPointerException e) {
        }

        request.setAttribute("movies", movies);
        request.setAttribute("genres", result.getGenres());
        request.setAttribute("totalResults", result.getTotalResults());
        request.setAttribute("page", page);
        request.setAttribute("filter", filter);
        request.setAttribute("genre", genre);
        request.setAttribute("movieName", movie);

        String details = request.getParameter("detail");

        if (details != null) {
            Movie det = movies.stream().filter(m -> m.getImdbID().equals(details)).findFirst().orElse(null);
            request.setAttribute("details", det);
        }

        request.getRequestDispatcher("movies.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
