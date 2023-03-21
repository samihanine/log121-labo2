package labo2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import labo2.observer.*;

public class ZoomModel implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private double zoom = 1.0;
    private Stack<Double> zooms = new Stack<>();

    public double getZoom() {
        return zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
        zooms.push(zoom);
        notifyObservers(); // Ajoutez cette ligne
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
