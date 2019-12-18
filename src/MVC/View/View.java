package MVC.View;

import MVC.Controller.Controller;
import java.util.Scanner;

public class View {
    //VISAR SAKER FRÅN KONTROLLERN
    Scanner sc;
    String input1;
    String input2;

    public void printMessage(String message) {
        System.out.println(message);
    }

    public View() {
        Controller controller = new Controller(this);
        sc = new Scanner(System.in);

        while (true) {
            printMessage("Välj film");
            controller.showMovies();
            input1 = sc.nextLine().toLowerCase().trim();
            switch (input1) {
                case "1":
                    controller.showMovie(Integer.parseInt(input1)-1);
                    printMessage("Välj funktion. Boka biljett, Avboka biljett, Visa lediga platser");
                    input2 = sc.nextLine().toLowerCase().trim();
                    controller.manageSeats(input1, input2);
                    break;
                case "2":
                    controller.showMovie(Integer.parseInt(input1)-1);
                    printMessage("Välj funktion. Boka biljett, Avboka biljett, Visa lediga platser");
                    input2 = sc.nextLine().toLowerCase().trim();
                    controller.manageSeats(input1, input2);
                    break;
                default:
                    printMessage("Angiven film finns inte, välj igen");
                    break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        View view = new View();
    }
}
