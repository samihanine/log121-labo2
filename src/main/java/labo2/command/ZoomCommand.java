package labo2.command;

import labo2.model.ZoomModel;

public class ZoomCommand implements Command {
    private ZoomModel zoomModel;
    private double oldZoom;
    private double newZoom;

    public ZoomCommand(ZoomModel zoomModel, double newZoom) {
        this.zoomModel = zoomModel;
        this.oldZoom = zoomModel.getZoom();
        this.newZoom = newZoom;
    }

    @Override
    public void execute() {
        zoomModel.setZoom(newZoom);
    }

    @Override
    public void undo() {
        zoomModel.setZoom(oldZoom);
    }
}
