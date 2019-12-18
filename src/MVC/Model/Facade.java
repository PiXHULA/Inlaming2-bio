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

    public void addMovieToTheater(String movieTitle) {
        movieTheater.addMovie(new Movie(movieTitle));
        notify1("add movie");
    }

    public List<String> showMovies() {
        List<String> movieName = new ArrayList<>();
        int counter = 1;
        for (Movie movie : movieTheater.getMovieLista()) {
            movieName.add(counter + ") " + movie.getMovieTitle());
            counter++;
        }
        return movieName;
    }
    public String showMovie(String movieChoice){
        List<String> movieName = new ArrayList<>();
        int counter = 1;
        for (Movie movie : movieTheater.getMovieLista()) {
            movieName.add(counter + ") " + movie.getMovieTitle());
            counter++;
        }
        return movieName.get(Integer.parseInt(movieChoice) - 1);
    }

    public List<String> getAvailableTickets(String movieChoice) {
        List<String> ticketList = new ArrayList<>();
        for (int i = 0; i < movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets().length; i++) {
            ticketList.add("" + movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets()[i]);
        }
        return ticketList;
    }

    public boolean ticketStatus(String movieChoice, String ticketChoice) {
        return movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) -1).getTickets()[Integer.parseInt(ticketChoice) -1].isUnavailable();
    }

    public boolean ticketsSoldOut(String movieChoice) {
        int counter = 0;
        for (Ticket ticket : movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets()) {
            if (ticket.isUnavailable())
                counter++;
        }
        return counter == 10;
    }

    public String showTicketStatus(String movieChoice) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets().length; i++) {
            if (!movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets()[i].isUnavailable())
                sb.append("O");
            else
                sb.append("X");
            if (i == movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets().length - 1)
                sb.append("]");
            else
                sb.append(", ");
        }
        return sb.toString();
    }

    public void cancelTicket(String movieChoice, String ticketChoice) {
        //if (facade.ticketStatus(input1, val))
        //facade.cancelTicket(input1, val);
        //else
        //view.printMessage("Platsen är inte bokad.");
        String[] arr = ticketChoice.split(" ");
        if (arr.length > 1) {
            for (String s : arr) {
                movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets()[Integer.parseInt(s) - 1].setUnavailable(false);
            }
            notify1("cancel tickets");
        }
        if (arr.length == 1) {
            movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets()[Integer.parseInt(arr[0]) - 1].setUnavailable(false);
            notify1("cancel ticket");
        }
    }

    public void bookTicket(String movieChoice, String ticketChoice) {
        String[] arr = ticketChoice.split(" ");
        if (arr.length > 1) {
            for (String s : arr) {
                movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets()[Integer.parseInt(s) - 1].setUnavailable(true);
            }
            notify1("book tickets");
        }
        if (arr.length == 1) {
            movieTheater.getMovieLista().get(Integer.parseInt(movieChoice) - 1).getTickets()[Integer.parseInt(arr[0]) - 1].setUnavailable(true);
            notify1("book ticket");
        }
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