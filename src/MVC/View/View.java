package MVC.View;

import MVC.Controller.Controller;

import java.util.Scanner;

public class View {
    //VISAR SAKER FRÅN KONTROLLERN
    Scanner sc;
    String input;

    public void printMessage(String message) {
        System.out.println(message);
    }

    public View() throws InterruptedException {
        Controller controller = new Controller(this);
        sc = new Scanner(System.in);

        while (true) {
            System.out.println("Välj film");
            System.out.println(controller.getFasad().getBiosalong().getFilmLista());
            input = sc.nextLine().toLowerCase();
            switch (input) {
                case "1":
                    System.out.println("Välj funktion. Boka biljett, Avboka biljett, Visa lediga platser");
                    input = sc.nextLine().toLowerCase();
                    bokaPlats(controller);
                    break;
                case "2":
                    System.out.println("Välj funktion. Boka biljett, Avboka biljett, Visa lediga platser");
                    input = sc.nextLine().toLowerCase();
                    bokaPlats(controller);
                    break;
                default:
                    break;
            }
        }
    }

    private void bokaPlats(Controller controller) throws InterruptedException {
        switch (input) {
            case "boka biljett":
                printMessage("Ange stolsnummer");
                controller.getFasad().addFilmTillSalong(sc.nextLine());
                break;
            case "avboka biljett":
                printMessage("Ange stolsnummer");
                controller.getFasad().createSalong(sc.nextLine());
                break;
            case "lediga platser":
                controller.getLedigaBiljetter();
                break;
            default:
                printMessage("Testa igen");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
    }
}
