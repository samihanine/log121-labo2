package labo2.command;

import javafx.geometry.Point2D;
import labo2.model.TranslationModel;

/**
 * The TranslationCommand class represents a translation command for a graphical
 * object.
 */
public class TranslationCommand implements Command {
    private TranslationModel translationModel;
    private Point2D oldTranslation;
    private Point2D newTranslation;

    /**
     * Constructs a new TranslationCommand object.
     *
     * @param translationModel the translation model to use
     * @param newX             the new horizontal position of the object
     * @param newY             the new vertical position of the object
     */
    public TranslationCommand(TranslationModel translationModel, double newX, double newY) {
        this.translationModel = translationModel;
        this.oldTranslation = new Point2D(translationModel.getTranslateX(), translationModel.getTranslateY());
        this.newTranslation = new Point2D(newX, newY);
    }

    /**
     * Executes the translation command.
     */
    @Override
    public void execute() {
        translationModel.setTranslateX(newTranslation.getX());
        translationModel.setTranslateY(newTranslation.getY());
    }

    /**
     * Undoes the last translation command.
     */
    @Override
    public void undo() {
        translationModel.setTranslateX(oldTranslation.getX());
        translationModel.setTranslateY(oldTranslation.getY());
    }
}
