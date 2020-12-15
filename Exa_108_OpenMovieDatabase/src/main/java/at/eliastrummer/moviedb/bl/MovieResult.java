package at.eliastrummer.moviedb.bl;

import at.eliastrummer.moviedb.bl.Movie;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieResult {
    private int totalResults;
    private List<Movie> movies;
    
    public List<String> getGenres() {
        List<String> result = new ArrayList<>();
        
        for(Movie m : movies) {
            String[] genres = m.getGenre().split(", ");
            
            for(String g : genres) {
                g = g.trim();
                if(!result.contains(g)) {
                    result.add(g);
                }
            }
        }
        
        result.sort((a, b) -> a.compareTo(b));
        return result;
    }
}