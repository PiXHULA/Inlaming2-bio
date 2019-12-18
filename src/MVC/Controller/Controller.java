package MVC.Controller;

import MVC.Model.Facade;
import MVC.View.View;

import java.util.Scanner;

public class Controller implements IObserver {
    private View view;
    private Facade facade = new Facade();

    public Controller(View view) {
        this.view = view;
        facade.attach(this);
    }

    public void showMovies() {
        view.printMessage(facade.showMovies().toString());
    }

    public void showMovie(int movieChoice) {
        view.printMessage(facade.showMovies().get(movieChoice));
    }

    public void showMovieAndTickets(String movieChoice) {
        view.printMessage(facade.showMovie(movieChoice));
        facade.getAvailableTickets(movieChoice);
    }

    public void manageSeats(String input1, String input2) {
        Scanner sc = new Scanner(System.in);
        String s;
        switch (input2) {
            case "boka":
                showMovieAndTickets(input1);
                view.printMessage(facade.getAvailableTickets(input1).toString());
                view.printMessage(facade.showTicketStatus(input1).toString());
                if (facade.ticketsSoldOut(input1))
                    view.printMessage("Föreställningen är fullbokad.");
                else {
                    view.printMessage("Ange stolsnummer");
                    sc = new Scanner(System.in);
                    facade.bookTicket(input1, sc.nextLine());
                }
                break;
            case "avboka":
                showMovieAndTickets(input1);
                view.printMessage(facade.getAvailableTickets(input1).toString());
                view.printMessage(facade.showTicketStatus(input1).toString());
                view.printMessage("Ange stolsnummer");
                sc = new Scanner(System.in);
                String val = sc.nextLine();
                facade.cancelTicket(input1, val);
                break;
            case "visa":
                showMovieAndTickets(input1);
                view.printMessage(facade.getAvailableTickets(input1).toString());
                view.printMessage(facade.showTicketStatus(input1));
                break;
            default:
                view.printMessage("Testa igen");
        }
    }

    @Override
    public void update(String msg) {
        if (msg.startsWith("add"))
            view.printMessage("Ny bio har lagts till");
        if (msg.startsWith("book")) {
            if (msg.equalsIgnoreCase("book ticket"))
                view.printMessage("Platsen är bokad");
            if (msg.equalsIgnoreCase("book tickets"))
                view.printMessage("Platserna är bokade");
        }
        if (msg.startsWith("cancel"))
            if (msg.equalsIgnoreCase("cancel ticket"))
                view.printMessage("Platsen är avbokad");
        if (msg.equalsIgnoreCase("cancel tickets"))
            view.printMessage("Platserna är avbokade");
    }

    public Facade getFacade() {
        return this.facade;
    }
}
