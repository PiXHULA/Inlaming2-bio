package MVC.Model;

import MVC.Controller.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Facade implements ISubject {
    MovieTheater movieTheater;
    List<IObserver> observerList;

    public Facade() {
        this.movieTheater = new MovieTheater("Mall of Scandinavia");
        this.observerList = new ArrayList<>();
    }

    public void createSalong(String salongName) {
        this.movieTheater = new MovieTheater(salongName);
    }

    public void addFilmTillSalong(String filmTitel) throws InterruptedException {
        movieTheater.addMovie(new Movie(filmTitel));
        notify1();
    }

    public List<String> showMovies() {
        List<String> filmNamn = new ArrayList<>();
        int counter = 1;
        for (Movie movie : movieTheater.getMovieLista()){
            filmNamn.add(counter + ") " + movie.getFilmName());
            counter++;
        }
        return filmNamn;
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
    }

    public void bookTicket(int filmVal, int biljettVal) {
        movieTheater.getMovieLista().get(filmVal).getTickets()[biljettVal].setUnavailable(true);
        notify1();
    }

    @Override
    public void notify1() {
        for (IObserver observer : observerList) {
            observer.update();
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