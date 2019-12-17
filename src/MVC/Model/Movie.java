package MVC.Model;

public class Movie {
    private Ticket[] biljetter;
    private String filmNamn;

    public Movie(String filmNamn){
        this.filmNamn = filmNamn;
        biljetter = new Ticket[]{
                new Ticket("1"),
                new Ticket("2"),
                new Ticket("3"),
                new Ticket("4"),
                new Ticket("5"),
                new Ticket("6"),
                new Ticket("7"),
                new Ticket("8"),
                new Ticket("9"),
                new Ticket("10"),
        };
    }

    public String getFilmName() {
        return filmNamn;
    }

    public Ticket[] getTickets() {
        return biljetter;
    }


    @Override
    public String toString() {
        return this.filmNamn;
    }
}
