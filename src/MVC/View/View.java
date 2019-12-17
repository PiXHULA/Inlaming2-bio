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

    public View() throws InterruptedException {
        Controller controller = new Controller(this);
        sc = new Scanner(System.in);

        while (true) {
            System.out.println("Välj film");
            controller.showMovies();
            input1 = sc.nextLine().toLowerCase();
            switch (input1) {
                case "1":
                    System.out.println("Välj funktion. Boka biljett, Avboka biljett, Visa lediga platser");
                    input2 = sc.nextLine().toLowerCase();
                    controller.manageSeats(input1, input2);
                    break;
                case "2":
                    System.out.println("Välj funktion. Boka biljett, Avboka biljett, Visa lediga platser");
                    input2 = sc.nextLine().toLowerCase();
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
