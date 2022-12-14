package model.Observer;

import java.util.ArrayList;

public abstract class Subject {
    private final ArrayList<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers){
            observer.update(true);
        }
    }
    public ArrayList<Observer> getObservers() {
        return observers;
    }
}
