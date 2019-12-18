package MVC.Model;

import java.util.ArrayList;
import java.util.List;

public class MovieTheater {

    private List<Movie> movieList;
    private String movieTitle;

    MovieTheater(String movieTitle) {
        this.movieTitle = movieTitle;
        movieList = new ArrayList<>();
        addMovie(new Movie("Life about Nadja"));
        addMovie(new Movie("What about dead baby Jesus"));
    }

    public void addMovie(Movie movie) {
        movieList.add(movie);
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}