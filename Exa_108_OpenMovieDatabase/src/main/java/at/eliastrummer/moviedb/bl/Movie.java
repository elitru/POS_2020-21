package at.eliastrummer.moviedb.bl;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    private String imdbID;
    private String Year;
    private String Title;
    private String Genre;
    private String Runtime;
    private String Actors;
    private String Rated;
    private String Released;
    private String Awards;
    private String Production;
    private String Poster;
    private String Director;
    private String Plot;
}

