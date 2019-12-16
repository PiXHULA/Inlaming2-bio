package MVC.Model;

import java.util.ArrayList;
import java.util.List;

public class Biosalong {

    private List<Film> filmLista;
    private String name;

    Biosalong(String name) {
        this.name = name;
        filmLista = new ArrayList<>();
        läggTillFilm(new Film("Film 1"));
        läggTillFilm(new Film("Film 2"));
    }

    public void läggTillFilm(Film film) {
        filmLista.add(film);
    }

    public List<Film> getFilmLista() {
        return filmLista;
    }
}