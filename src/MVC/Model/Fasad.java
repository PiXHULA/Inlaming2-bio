package MVC.Model;

import MVC.Controller.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Fasad implements ISubject {
    Biosalong biosalong;
    List<IObserver> observerList;

    public Fasad() {
        this.biosalong = new Biosalong("Mall of Scandinavia");
        this.observerList = new ArrayList<>();
    }

    public void createSalong(String salongName) {
        this.biosalong = new Biosalong(salongName);
    }

    public void addFilmTillSalong(String filmTitel) throws InterruptedException {
        biosalong.läggTillFilm(new Film(filmTitel));
        notify1();
    }
    public void visaFilmer(){
        for(Film film : biosalong.getFilmLista())
            System.out.println(film);
    }

    public List<String> getLedigaBiljetter() {
        List<String> biljettLista= new ArrayList<>();
        for(int i = 0; i < biosalong.getFilmLista().size(); i++){
            biljettLista.add(biosalong.getFilmLista().get(i).getModelName());
        }
        return biljettLista;
    }

    @Override
    public void notify1() throws InterruptedException {
        for(IObserver observer : observerList){
            observer.update();
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

    public Biosalong getBiosalong() {
        return biosalong;
    }
}