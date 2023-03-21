package labo2.model;

import labo2.observer.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ImageModel implements Serializable, Observable {
    private ZoomModel zoomModel;
    private TranslationModel translationModel;
    private List<Observer> observers = new ArrayList<>();

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

    public ImageModel() {
        this.zoomModel = new ZoomModel();
        this.translationModel = new TranslationModel();
    }

    public ZoomModel getZoomModel() {
        return zoomModel;
    }

    public TranslationModel getTranslationModel() {
        return translationModel;
    }
}
