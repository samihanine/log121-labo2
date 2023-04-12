package labo2.controller;

import labo2.command.Command;
import labo2.command.TranslationCommand;
import labo2.command.ZoomCommand;
import labo2.model.TranslationModel;
import labo2.model.ZoomModel;
import labo2.singleton.CommandManager;

public class CommandController {

    private CommandManager commandManager = CommandManager.getInstance();

    public void handleScroll(ZoomModel zoomModel, double deltaY) {
        double zoomFactor = 1.05;
        if (deltaY < 0) {
            zoomFactor = 0.95;
        }

        double newZoom = zoomModel.getZoom() * zoomFactor;
        Command zoomCommand = new ZoomCommand(zoomModel, newZoom);
        commandManager.executeCommand(zoomCommand);
    }

    public void handleMouseDragged(TranslationModel translationModel, double deltaX, double deltaY) {
        Command translateCommand = new TranslationCommand(translationModel, deltaX, deltaY);
        commandManager.executeCommand(translateCommand);
    }

    public void undoCommand() {
        commandManager.undoCommand();
    }
}
