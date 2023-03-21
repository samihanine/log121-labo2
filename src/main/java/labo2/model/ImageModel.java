package labo2.model;

import labo2.observer.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The ImageModel class represents the model for an image.
 * It implements the Serializable and Observable interfaces.
 */
public class ImageModel implements Serializable, Observable {
    private ZoomModel zoomModel;
    private TranslationModel translationModel;
    private List<Observer> observers = new ArrayList<>();

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

    /**
     * Constructs a new ImageModel object with default values for zoom and
     * translation.
     */
    public ImageModel() {
        this.zoomModel = new ZoomModel();
        this.translationModel = new TranslationModel();
    }

    /**
     * Returns the zoom model of the image.
     *
     * @return the zoom model
     */
    public ZoomModel getZoomModel() {
        return zoomModel;
    }

    /**
     * Returns the translation model of the image.
     *
     * @return the translation model
     */
    public TranslationModel getTranslationModel() {
        return translationModel;
    }
}
