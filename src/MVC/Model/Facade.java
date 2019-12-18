package MVC.Model;

import MVC.Controller.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Facade implements ISubject {
   private MovieTheater movieTheater;
   private List<IObserver> observerList;

    public Facade() {
        this.movieTheater = new MovieTheater("Mall of Scandinavia");
        this.observerList = new ArrayList<>();
    }

    public void createSalong(String theaterName) {
        this.movieTheater = new MovieTheater(theaterName);
    }

    public void addMovieToTheater(String movieTitle){
        movieTheater.addMovie(new Movie(movieTitle));
        notify1("add movie");
    }

    public List<String> showMovies() {
        List<String> movieName = new ArrayList<>();
        int counter = 1;
        for (Movie movie : movieTheater.getMovieLista()){
            movieName.add(counter + ") " + movie.getMovieTitle());
            counter++;
        }
        return movieName;
    }

    public List<String> getAvailableTickets(int movieChoice) {
        List<String> ticketList = new ArrayList<>();
        for (int i = 0; i < movieTheater.getMovieLista().get(movieChoice).getTickets().length; i++) {
            ticketList.add("" + movieTheater.getMovieLista().get(movieChoice).getTickets()[i]);
        }
        return ticketList;
    }
    public boolean ticketStatus(int movieChoice, int ticketChoice){
        return movieTheater.getMovieLista().get(movieChoice).getTickets()[ticketChoice].isUnavailable();
    }

    public boolean ticketsSoldOut(int movieChoice){
        int counter = 0;
        for(Ticket ticket : movieTheater.getMovieLista().get(movieChoice).getTickets()){
            if(ticket.isUnavailable())
                counter++;
        }
        return counter == 10;
    }

    public String showTicketStatus(int movieChoice) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < movieTheater.getMovieLista().get(movieChoice).getTickets().length; i++) {
            if (!movieTheater.getMovieLista().get(movieChoice).getTickets()[i].isUnavailable())
                sb.append("O");
            else
                sb.append("X");
            if(i == movieTheater.getMovieLista().get(movieChoice).getTickets().length-1)
                sb.append("]");
            else
                sb.append(", ");
        }
        return sb.toString();
    }

    public void cancelTicket(int movieChoice, int ticketChoice){
        movieTheater.getMovieLista().get(movieChoice).getTickets()[ticketChoice].setUnavailable(false);
        notify1("cancel");
    }

    public void bookTicket(int movieChoice, int ticketChoice) {
        movieTheater.getMovieLista().get(movieChoice).getTickets()[ticketChoice].setUnavailable(true);
        notify1("book ticket");
    }


    @Override
    public void notify1(String msg) {
        for (IObserver observer : observerList) {
            observer.update(msg);
        }
    }

    @Override
    public void attach(IObserver observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        observerList.remove(observer);
    }

    public MovieTheater getMovieTheater() {
        return this.movieTheater;
    }
}