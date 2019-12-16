package MVC.Model;

import java.util.List;

public class Film {
    private Biljett[] biljettPlatser;
    private String filmNamn;

    public Film(String filmNamn){
        this.filmNamn = filmNamn;
        biljettPlatser = new Biljett[10];
    }

    public String getModelName() {
        return filmNamn;
    }

    @Override
    public String toString() {
        return this.filmNamn;
    }
}
