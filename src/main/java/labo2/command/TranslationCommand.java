package labo2.command;

import javafx.geometry.Point2D;
import labo2.model.TranslationModel;

public class TranslationCommand implements Command {
    private TranslationModel translationModel;
    private Point2D oldTranslation;
    private Point2D newTranslation;

    public TranslationCommand(TranslationModel translationModel, double newX, double newY) {
        this.translationModel = translationModel;
        this.oldTranslation = new Point2D(translationModel.getTranslateX(), translationModel.getTranslateY());
        this.newTranslation = new Point2D(newX, newY);
    }

    @Override
    public void execute() {
        translationModel.setTranslateX(newTranslation.getX());
        translationModel.setTranslateY(newTranslation.getY());
    }

    @Override
    public void undo() {
        translationModel.setTranslateX(oldTranslation.getX());
        translationModel.setTranslateY(oldTranslation.getY());
    }
}