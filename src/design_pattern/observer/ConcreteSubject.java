package design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteSubject implements Subject {

    private List<Observer> observers;

    public ConcreteSubject() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void nofifyAll(String mess) {
        if(observers.isEmpty()){
            return;
        }
        for (Observer observer : observers) {
            observer.update(mess);
        }
    }


}
