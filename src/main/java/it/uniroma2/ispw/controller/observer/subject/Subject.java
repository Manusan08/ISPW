package it.uniroma2.ispw.controller.observer.subject;

import it.uniroma2.ispw.controller.observer.Context;
import it.uniroma2.ispw.controller.observer.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    protected List<Observer> observers;
    protected Boolean isPrenotata;


    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public Subject() {
        this((Observer) null);
    }

    public Subject(Observer obs) {
        this(new ArrayList<>());
        if (obs != null)
            this.observers.add(obs);
    }

    public Subject(List<Observer> observers) {
        this.observers = observers;
        this.isPrenotata = false;
    }

    public void attach(Observer obs) {
        this.observers.add(obs);
    }


    public List<Observer> getObservers() {

        return observers;
    }

    public void detach(Observer obs) {
        this.observers.remove(obs);
    }

    public void notify(Context context) {
        for (Observer observer : observers) {
            observer.update(context);
        }
    }
}