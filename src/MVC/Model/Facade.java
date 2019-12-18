package MVC.Model;

import MVC.Controller.IObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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

    public void addMovieToTheater(String movieTitle) {
        movieTheater.addMovie(new Movie(movieTitle));
        notify1("add movie");
    }

    public List<String> showMovies() {
        List<String> movieName = new ArrayList<>();
        int counter = 1;
        for (Movie movie : movieTheater.getMovieList()) {
            movieName.add(counter + ") " + movie.getMovieTitle());
            counter++;
        }
        return movieName;
    }

    public String showMovie(String movieChoice) {
        List<String> movieName = new ArrayList<>();
        int counter = 1;
        for (Movie movie : movieTheater.getMovieList()) {
            movieName.add(counter + ") " + movie.getMovieTitle());
            counter++;
        }
        return movieName.get(Integer.parseInt(movieChoice) - 1);
    }

    public List<String> getAvailableTickets(String movieChoice) {
        List<String> ticketList = new ArrayList<>();
        for (int i = 0; i < movieTheater.getMovieList().get(Integer.parseInt(movieChoice) - 1).getTickets().length; i++) {
            ticketList.add("" + movieTheater.getMovieList().get(Integer.parseInt(movieChoice) - 1).getTickets()[i]);
        }
        return ticketList;
    }

    public boolean ticketStatus(String movieChoice, String ticketChoice) {
       // if (!ticketChoice.equals(" "))
            return movieTheater.getMovieList().get(Integer.parseInt(movieChoice) - 1).getTickets()[Integer.parseInt(ticketChoice) - 1].isUnavailable();
       // else
         //   return false;
    }

    public boolean ticketsSoldOut(String movieChoice) {
        int counter = 0;
        for (Ticket ticket : movieTheater.getMovieList().get(Integer.parseInt(movieChoice) - 1).getTickets()) {
            if (ticket.isUnavailable())
                counter++;
        }
        return counter == 10;
    }

    public String showTicketStatus(String movieChoice) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < movieTheater.getMovieList().get(Integer.parseInt(movieChoice) - 1).getTickets().length; i++) {
            if (!movieTheater.getMovieList().get(Integer.parseInt(movieChoice) - 1).getTickets()[i].isUnavailable())
                sb.append("O");
            else
                sb.append("X");
            if (i == movieTheater.getMovieList().get(Integer.parseInt(movieChoice) - 1).getTickets().length - 1)
                sb.append("]");
            else
                sb.append(", ");
        }
        return sb.toString();
    }

    public void cancelTicket(String movieChoice, String ticketChoice) {
        int failCounter = 0;
        int successCounter = 0;
        StringBuilder fail = new StringBuilder("");
        StringBuilder success = new StringBuilder("");
        String[] arr = ticketChoice.split(" ");
        for (String s : arr) {
            if (!Pattern.matches("[a-zA-Z ]", s)) {
                if (ticketStatus(movieChoice, s)) {
                    success.append(s + " ");
                    movieTheater.getMovieList().get(Integer.parseInt(movieChoice) - 1).getTickets()[Integer.parseInt(s) - 1].setUnavailable(false);
                    successCounter++;
                } else if (!ticketStatus(movieChoice, s)) {
                    fail.append(s + " ");
                    failCounter++;
                }
            } else
                notify1("fail to write");
        }
        if (successCounter == 1)
            notify1("cancel ticket" + success);
        if (successCounter > 1)
            notify1("cancel tickets" + success);
        if (failCounter > 0){
            notify1("fail to cancel" + fail);
        }
    }
    public void sendNotify(String success, int successCounter, String fail, int failCounter){
        if (successCounter == 1)
            notify1("cancel ticket" + success);
        if (successCounter > 1)
            notify1("cancel tickets" + success);
        if (failCounter > 0)
            notify1("fail to cancel" + fail);
    }

    public void bookTicket(String movieChoice, String ticketChoice) {
        int failCounter = 0;
        int successCounter = 0;
        StringBuilder fail = new StringBuilder("");
        StringBuilder success = new StringBuilder("");
        String[] arr = ticketChoice.split(" ");
        for (String s : arr) {
            if (!Pattern.matches("[a-zA-Z ]", s)) {
                if (!ticketStatus(movieChoice, s)) {
                    success.append(s + " ");
                    movieTheater.getMovieList().get(Integer.parseInt(movieChoice) - 1)
                            .getTickets()[Integer.parseInt(s) - 1].setUnavailable(true);
                    successCounter++;
                } else if (ticketStatus(movieChoice, s)) {
                    fail.append(s + " ");
                    failCounter++;
                }
            } else
                notify1("fail to write");
        }
        if (successCounter == 1)
            notify1("book ticket" + success);
        if (successCounter > 1)
            notify1("book tickets" + success);
        if (failCounter > 0)
            notify1("fail to book" + fail);
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