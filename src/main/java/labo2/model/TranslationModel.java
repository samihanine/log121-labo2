package labo2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import labo2.observer.*;
import javafx.geometry.Point2D;

public class TranslationModel implements Observable {
    // ...
    private List<Observer> observers = new ArrayList<>();
    private double translateX;
    private double translateY;
    private Stack<Point2D> translations = new Stack<>();

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
        Point2D point = new Point2D(translateX, translateY);
        translations.push(point);
        notifyObservers(); // Ajoutez cette ligne
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
        Point2D point = new Point2D(translateX, translateY);
        translations.push(point);
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
