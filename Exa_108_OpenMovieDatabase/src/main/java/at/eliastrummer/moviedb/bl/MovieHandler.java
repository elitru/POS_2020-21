package at.eliastrummer.moviedb.bl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MovieHandler {

    private static final String BASE_URL = "http://www.omdbapi.com/?apikey=e893b49f&";

    public static MovieResult getMovies(String movieTitle, int page) throws MalformedURLException, UnsupportedEncodingException {
        try {
            URL url = new URL(BASE_URL + "page=" + page + "&s=" + encodeUrlParam(movieTitle));
            JsonMapper mapper = new JsonMapper();
            JsonNode node = mapper.readTree(url);

            if (node.get("Response").asText().equalsIgnoreCase("false")) {
                return new MovieResult(0, null);
            }

            ObjectMapper oMapper = new ObjectMapper();
            oMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
            oMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            List<Movie> result = oMapper.convertValue(node.get("Search"), new TypeReference<List<Movie>>() {
            });

            for (int i = 0; i < result.size(); i++) {
                Movie m = getMovie(result.get(i).getImdbID());
                
                if(m == null)
                    continue;
                
                result.set(i, m);
            }

            return new MovieResult(node.get("totalResults").asInt(), result);
        } catch (IOException e) {
            return new MovieResult(0, new ArrayList<>());
        }
    }

    private static Movie getMovie(String id) throws MalformedURLException, UnsupportedEncodingException, IOException {
        URL url = new URL(BASE_URL + "i=" + id + "&plot=full");
        JsonMapper mapper = new JsonMapper();
        JsonNode node = mapper.readTree(url);

        if (node.get("Response").asText().equalsIgnoreCase("false")) {
            return null;
        }

        ObjectMapper oMapper = new ObjectMapper();
        oMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        oMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            Movie movie = new Movie(id,
                node.get("Year").asText() != null ? node.get("Year").asText() : "",
                node.get("Title").asText() != null ? node.get("Title").asText() : "",
                node.get("Genre").asText() != null ? node.get("Genre").asText() : "",
                node.get("Runtime").asText() != null ? node.get("Runtime").asText() : "",
                node.get("Actors").asText() != null ? node.get("Actors").asText() : "",
                node.get("Rated").asText() != null ? node.get("Rated").asText() : "",
                node.get("Released").asText() != null ? node.get("Released").asText() : "",
                node.get("Awards").asText() != null ? node.get("Awards").asText() : "",
                node.get("Production").asText() != null ? node.get("Production").asText() : "",
                node.get("Poster").asText() != null ? node.get("Poster").asText() : "",
                node.get("Director").asText() != null ? node.get("Director").asText() : "",
                node.get("Plot").asText() != null ? node.get("Plot").asText() : "");
            return movie;
        }catch(NullPointerException e) {
            return null;
        }
    }

    private static String encodeUrlParam(String param) throws UnsupportedEncodingException {
        return URLEncoder.encode(param, StandardCharsets.UTF_8.toString());
    }
}
