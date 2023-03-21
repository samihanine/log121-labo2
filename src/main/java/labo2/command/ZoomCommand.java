package labo2.command;

import labo2.model.ZoomModel;

/**
 * The ZoomCommand class represents a zoom command for a graphical object.
 */
public class ZoomCommand implements Command {
    private ZoomModel zoomModel;
    private double oldZoom;
    private double newZoom;

    /**
     * Constructs a new ZoomCommand object.
     *
     * @param zoomModel the zoom model to use
     * @param newZoom   the new zoom value
     */
    public ZoomCommand(ZoomModel zoomModel, double newZoom) {
        this.zoomModel = zoomModel;
        this.oldZoom = zoomModel.getZoom();
        this.newZoom = newZoom;
    }

    /**
     * Executes the zoom command.
     */
    @Override
    public void execute() {
        zoomModel.setZoom(newZoom);
    }

    /**
     * Undoes the last zoom command.
     */
    @Override
    public void undo() {
        zoomModel.setZoom(oldZoom);
    }
}
