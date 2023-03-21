package labo2.observer;

/**
 * The Observable interface represents an observable object.
 * An observable object can have one or more observers.
 * An observer is an object that is notified when the observable object is
 * changed.
 */
public interface Observable {

    /**
     * Adds an observer to the list of observers.
     *
     * @param observer the observer to add
     */
    void addObserver(Observer observer);

    /**
     * Removes an observer from the list of observers.
     *
     * @param observer the observer to remove
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all observers when the observable object is changed.
     */
    void notifyObservers();
}
