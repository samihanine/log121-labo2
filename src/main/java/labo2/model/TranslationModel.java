package labo2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import labo2.observer.*;
import javafx.geometry.Point2D;

/**
 * The TranslationModel class represents the model for the translation of an
 * object.
 * It implements the Observable interface.
 */
public class TranslationModel implements Observable {
    // ...
    private List<Observer> observers = new ArrayList<>();
    private double translateX;
    private double translateY;
    private Stack<Point2D> translations = new Stack<>();

    /**
     * Returns the current translation along the x-axis.
     *
     * @return the current translation along the x-axis
     */
    public double getTranslateX() {
        return translateX;
    }

    /**
     * Returns the current translation along the y-axis.
     *
     * @return the current translation along the y-axis
     */
    public double getTranslateY() {
        return translateY;
    }

    /**
     * Sets the translation along the x-axis.
     * Adds the translation to the stack of translations and notifies the observers.
     *
     * @param translateX the translation along the x-axis
     */
    public void setTranslateX(double translateX) {
        this.translateX = translateX;
        Point2D point = new Point2D(translateX, translateY);
        translations.push(point);
        notifyObservers();
    }

    /**
     * Sets the translation along the y-axis.
     * Adds the translation to the stack of translations and notifies the observers.
     *
     * @param translateY the translation along the y-axis
     */
    public void setTranslateY(double translateY) {
        this.translateY = translateY;
        Point2D point = new Point2D(translateX, translateY);
        translations.push(point);
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
