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

    public void showMovieAndTickets(int movieChoice) {
        view.printMessage(facade.showMovies().get(movieChoice));
        facade.getAvailableTickets(movieChoice);
    }

    public void manageSeats(String input1, String input2) {
        Scanner sc = new Scanner(System.in);
        String s;
        switch (input2) {
            case "boka":
                showMovieAndTickets(Integer.parseInt(input1) - 1);
                view.printMessage(facade.getAvailableTickets(Integer.parseInt(input1) - 1).toString());
                view.printMessage(facade.showTicketStatus(Integer.parseInt(input1) - 1).toString());
                if (facade.ticketsSoldOut((Integer.parseInt(input1) - 1)))
                    view.printMessage("Föreställningen är fullbokad.");
                else {
                    view.printMessage("Ange stolsnummer");
                    sc = new Scanner(System.in);
                    s = sc.nextLine();
                    sc = new Scanner(s);

                    while (sc.hasNextLine()) {
                        try {
                            int val = sc.nextInt();
                            if (!facade.ticketStatus((Integer.parseInt(input1) - 1), val - 1))
                                facade.bookTicket(Integer.parseInt(input1) - 1, val - 1);
                            else
                                view.printMessage("Platsen är redan bokad.");
                        } catch (Exception e) {
                            view.printMessage("Felaktig inmatning");
                        }
                    }
                }
                break;
            case "avboka":
                showMovieAndTickets(Integer.parseInt(input1) - 1);
                view.printMessage(facade.getAvailableTickets(Integer.parseInt(input1) - 1).toString());
                view.printMessage(facade.showTicketStatus(Integer.parseInt(input1) - 1).toString());

                view.printMessage("Ange stolsnummer");
                sc = new Scanner(System.in);
                s = sc.nextLine();
                sc = new Scanner(s);

                while (sc.hasNextInt()) {
                    try {
                        int val = sc.nextInt();
                        if (facade.ticketStatus((Integer.parseInt(input1) - 1), val - 1))
                            facade.cancelTicket(Integer.parseInt(input1) - 1, val - 1);
                        else
                            view.printMessage("Platsen är inte bokad.");
                    } catch (Exception e) {
                        view.printMessage("Felaktig inmatning");
                    }
                }
                break;
            case "visa":
                showMovieAndTickets(Integer.parseInt(input1) - 1);
                view.printMessage(facade.getAvailableTickets(Integer.parseInt(input1) - 1).toString());
                view.printMessage(facade.showTicketStatus(Integer.parseInt(input1) - 1));
                break;
            default:
                view.printMessage("Testa igen");
        }
    }

    @Override
    public void update(String msg) {
        if(msg.startsWith("add"))
            view.printMessage("Ny bio har lagts till");
        if(msg.startsWith("book"))
            view.printMessage("Platsen är bokad");
        if(msg.startsWith("cancel"))
            view.printMessage("Platsen är avbokad");
    }

    public Facade getFacade() {
        return this.facade;
    }
}
