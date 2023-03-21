package labo2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import labo2.observer.*;

/**
 * The ZoomModel class represents the model for the zoom of an object.
 * It implements the Observable interface.
 */
public class ZoomModel implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private double zoom = 1.0;
    private Stack<Double> zooms = new Stack<>();

    /**
     * Returns the current zoom level.
     *
     * @return the current zoom level
     */
    public double getZoom() {
        return zoom;
    }

    /**
     * Sets the zoom level.
     * Adds the zoom level to the stack of zooms and notifies the observers.
     *
     * @param zoom the zoom level
     */
    public void setZoom(double zoom) {
        this.zoom = zoom;
        zooms.push(zoom);
        notifyObservers();
    }

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer the observer to add
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer the observer to remove
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all observers when the model is changed.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
