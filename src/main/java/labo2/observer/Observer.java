package labo2.observer;

/**
 * The Observer interface represents an observer object that watches an
 * observable object.
 * The update() method is called when the observable object is changed.
 */
public interface Observer {

    /**
     * Called when an observable object is changed.
     *
     * @param o the observable object that has changed
     */
    void update(Observable o);
}
