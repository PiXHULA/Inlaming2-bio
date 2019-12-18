package MVC.Model;

public class Movie {

    private Ticket[] tickets;
    private String movieTitle;

    public Movie(String movieTitle){
        this.movieTitle = movieTitle;
        tickets = new Ticket[]{
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

    public String getMovieTitle() {
        return movieTitle;
    }

    public Ticket[] getTickets() {
        return tickets;
    }


    @Override
    public String toString() {
        return this.movieTitle;
    }
}
