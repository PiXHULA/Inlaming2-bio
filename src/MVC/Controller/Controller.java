package MVC.Controller;

import MVC.Model.Fasad;
import MVC.View.View;

public class Controller implements IObserver {
   View view;
   Fasad fasad = new Fasad();

    public Controller(View view) throws InterruptedException {
        this.view = view;
        fasad.attach(this);
    }

    public void getLedigaBiljetter() {
        for(String s : fasad.getLedigaBiljetter())
        view.printMessage(s);
    }


    @Override
    public void update(){
        view.printMessage("Plats har bokats");
//        getLedigaBiljetter();
//        Thread.sleep(1000);
    }

    public Fasad getFasad() {
        return fasad;
    }
}
