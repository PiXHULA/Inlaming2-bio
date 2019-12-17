package MVC.Model;

import java.util.ArrayList;
import java.util.List;

public class MovieTheater {

    private List<Movie> movieLista;
    private String movieName;

    MovieTheater(String movieName) {
        this.movieName = movieName;
        movieLista = new ArrayList<>();
        addMovie(new Movie("Life about Nadja"));
        addMovie(new Movie("What about dead baby Jesus"));
    }

    public void addMovie(Movie movie) {
        movieLista.add(movie);
    }

    public List<Movie> getMovieLista() {
        return movieLista;
    }
}