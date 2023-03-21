package labo2.model;

import java.util.Stack;

public class ZoomModel {
    private double zoom = 1.0;
    private Stack<Double> zooms = new Stack<>();

    public void setZoom(double zoom) {
        this.zoom = zoom;
        zooms.push(zoom);
    }

    public double getZoom() {
        return zoom;
    }

    public void undo() {
        if (!zooms.isEmpty()) {
            zoom = zooms.pop();
        }
    }
}
