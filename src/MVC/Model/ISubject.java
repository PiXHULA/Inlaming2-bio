package MVC.Model;

import MVC.Controller.IObserver;

public interface ISubject {
   void notify1(String msg);
   void attach(IObserver observer);
   void detach(IObserver observer);
}
