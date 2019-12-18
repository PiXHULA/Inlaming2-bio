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

    private void showMovieAndTickets(String movieChoice) {
        view.printMessage(facade.showMovie(movieChoice));
        facade.getAvailableTickets(movieChoice);
    }

    public void manageSeats(String input1, String input2) {
        Scanner sc = new Scanner(System.in);
        switch (input2) {
            case "boka":
                showMovieAndTickets(input1);
                view.printMessage(facade.getAvailableTickets(input1).toString());
                view.printMessage(facade.showTicketStatus(input1).toString());
                if (facade.ticketsSoldOut(input1))
                    view.printMessage("Föreställningen är fullbokad.");
                else {
                    view.printMessage("Ange stolsnummer");
                    facade.bookTicket(input1, sc.nextLine());
                }
                break;
            case "avboka":
                showMovieAndTickets(input1);
                view.printMessage(facade.getAvailableTickets(input1).toString());
                view.printMessage(facade.showTicketStatus(input1).toString());
                view.printMessage("Ange stolsnummer");
                facade.cancelTicket(input1, sc.nextLine());
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
        //add messages
        if (msg.startsWith("add"))
            view.printMessage("Ny bio har lagts till");
        //Booking messages
        if (msg.startsWith("book")) {
            System.out.println(msg.length());
            if (msg.startsWith("book tickets") && msg.length() > 14)
                view.printMessage("Platserna [" + msg.substring(msg.lastIndexOf("s") +1,msg.length()-1)
                        + "] är nu bokade");
            if (msg.startsWith("book ticket") && msg.length() <= 14)
                view.printMessage("Plats [" + msg.substring(msg.lastIndexOf("t") +1,msg.length()-1)
                        + "] är nu bokad");
        }
        //Cancel messages
        if (msg.startsWith("cancel")) {
            if (msg.startsWith("cancel tickets") && msg.length() > 16)
                view.printMessage("Platserna [" + msg.substring(msg.lastIndexOf("s") +1,msg.length()-1)
                        + "] är nu avbokade");
            if (msg.startsWith("cancel ticket") && msg.length() <= 16)
                view.printMessage("Plats [" + msg.substring(msg.lastIndexOf("t") +1,msg.length()-1)
                        + "] är nu avbokad");
        }
        //Fail messages
        if (msg.startsWith("fail")) {
            if (msg.startsWith("fail to cancel") && msg.length() > 17)
                view.printMessage("Platserna [" + msg.substring(msg.lastIndexOf("l")+1, msg.length()-1)
                        + "] är inte bokade än");
            if(msg.startsWith("fail to cancel") && msg.length() <= 17)
                view.printMessage("Plats [" + msg.substring(msg.lastIndexOf("l")+1, msg.length()-1)
                        + "] är inte bokad än");
            if (msg.startsWith("fail to book")  && msg.length() > 15)
                view.printMessage("Platserna [" + msg.substring(msg.lastIndexOf("k")+1, msg.length()-1)
                        + "] var redan uppbokade");
            if (msg.startsWith("fail to book")  && msg.length() <= 15)
                view.printMessage("Plats [" + msg.substring(msg.lastIndexOf("k")+1, msg.length()-1)
                        + "] är inte bokad än");
            if (msg.startsWith("fail to write"))
                view.printMessage("Verkar som det blivit fel med någon/några utav inmatningarna, testa igen.");
        }
    }
    public Facade getFacade() {
        return this.facade;
    }
}
