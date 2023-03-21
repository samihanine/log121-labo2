package labo2.model;

import java.util.Stack;

import javafx.geometry.Point2D;

public class TranslationModel {
    private double translateX;
    private double translateY;
    private Stack<Point2D> translations = new Stack<>();

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
        Point2D point = new Point2D(translateX, translateY);
        translations.push(point);
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
        Point2D point = new Point2D(translateX, translateY);
        translations.push(point);
    }

    public double getTranslateX() {
        return translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void undo() {
        if (!translations.isEmpty()) {
            Point2D point = translations.pop();
            translateX = point.getX();
            translateY = point.getY();
        }
    }
}
