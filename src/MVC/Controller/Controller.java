package MVC.Controller;

import MVC.Model.Facade;
import MVC.View.View;

import java.util.Scanner;

public class Controller implements IObserver {
    private View view;
    private Facade facade = new Facade();

    public Controller(View view) throws InterruptedException {
        this.view = view;
        facade.attach(this);
    }

    public void showMovies() {
        view.printMessage(facade.showMovies().toString());
    }

    public void showMovie(int filmVal) {
        view.printMessage(facade.showMovies().get(filmVal));
        facade.getAvailableTickets(filmVal);
    }

    public void manageSeats(String input1, String input2) {
        Scanner sc = new Scanner(System.in);
        switch (input2) {
            case "boka":
                showMovie(Integer.parseInt(input1) - 1);
                view.printMessage(facade.getAvailableTickets(Integer.parseInt(input1) - 1).toString());
                view.printMessage(facade.showTicketStatus(Integer.parseInt(input1) - 1).toString());

                view.printMessage("Ange stolsnummer");
                sc = new Scanner(System.in);
                String s = sc.nextLine();
                sc = new Scanner(s);

                while (sc.hasNextInt()) {
                    try {
                        int val = sc.nextInt();
                        if (!facade.ticketStatus((Integer.parseInt(input1) - 1), val - 1))
                            facade.bookTicket(Integer.parseInt(input1) - 1, val - 1);
                        else
                            view.printMessage("Platsen är redan bokad.");
                    } catch (Exception e) {
                        System.out.println("Felaktig inmatning");
                    }
                }
                break;
            case "avboka":
                showMovie(Integer.parseInt(input1) - 1);
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
                        System.out.println("Felaktig inmatning");
                    }
                }
                break;
            case "visa":
                showMovie(Integer.parseInt(input1) - 1);
                view.printMessage(facade.getAvailableTickets(Integer.parseInt(input1) - 1).toString());
                view.printMessage(facade.showTicketStatus(Integer.parseInt(input1) - 1));
                break;
            default:
                view.printMessage("Testa igen");
        }
    }

    @Override
    public void update() {
        view.printMessage("Platsen är bokad");
    }

    public Facade getFacade() {
        return this.facade;
    }
}
