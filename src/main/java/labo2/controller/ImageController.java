package labo2.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import labo2.command.Command;
import labo2.command.TranslationCommand;
import labo2.command.ZoomCommand;
import labo2.model.ImageModel;
import labo2.model.TranslationModel;
import labo2.model.ZoomModel;
import labo2.observer.Observer;
import labo2.singleton.CommandManager;
import labo2.observer.Observable;

/**
 * The ImageController class handles user input and updates the graphical
 * representation of the image.
 * It implements the Observer interface to update the views when the models are
 * changed.
 */
public class ImageController implements Observer {

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    @FXML
    private BorderPane root;

    private Map<ImageView, ImageModel> imageModelMap = new HashMap<>();

    private Stack<Command> commands = new Stack<>();

    private CommandManager commandManager = CommandManager.getInstance();

    private double lastX;
    private double lastY;

    /**
     * Initializes the ImageView objects.
     *
     * @param imageViews the ImageView objects to initialize
     */
    private void initializeImageViews(ImageView... imageViews) {
        for (ImageView imageView : imageViews) {
            imageView.setPreserveRatio(true);
            imageView.setOnScroll(this::handleScroll);
            imageView.setOnMousePressed(event -> {
                imageView.setMouseTransparent(true);
                handleMousePressed(event);
            });
            imageView.setOnMouseDragged(event -> {
                imageView.setMouseTransparent(true);
                handleMouseDragged(event);
            });
            imageView.setOnMouseReleased(event -> {
                imageView.setMouseTransparent(false);
            });
            imageModelMap.put(imageView, new ImageModel());

            ImageModel model = imageModelMap.get(imageView);
            model.getZoomModel().addObserver(this);
            model.getTranslationModel().addObserver(this);
        }
    }

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        initializeImageViews(imageView2, imageView3);
    }

    /**
     * Loads an image and sets it to the ImageView objects.
     */
    public void loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            System.out.println("Selected image path: " + selectedFile.toURI().toString());
            Image image = new Image(selectedFile.toURI().toString());
            imageView1.setImage(image);
            imageView2.setImage(image);
            imageView3.setImage(image);

            // Set file model for each image view
            for (ImageView imageView : imageModelMap.keySet()) {
                ImageModel model = imageModelMap.get(imageView);
                model.getZoomModel().setZoom(1.0);
                model.getTranslationModel().setTranslateX(0.0);
                model.getTranslationModel().setTranslateY(0.0);
            }
        }
    }

    /**
     * Handles the scroll event to zoom in and out of the image.
     *
     * @param event the scroll event
     */
    private void handleScroll(ScrollEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        ImageModel model = imageModelMap.get(imageView);
        ZoomModel zoomModel = model.getZoomModel();
        double zoomFactor = 1.05;
        double deltaY = event.getDeltaY();
        if (deltaY < 0) {
            zoomFactor = 0.95;
        }

        double newZoom = zoomModel.getZoom() * zoomFactor;
        Command zoomCommand = new ZoomCommand(zoomModel, newZoom);
        commandManager.executeCommand(zoomCommand);

        event.consume();
    }

    /**
     * Handles the mouse pressed event to start dragging the image.
     *
     * @param event the mouse pressed event
     */
    private void handleMousePressed(javafx.scene.input.MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        ImageModel model = imageModelMap.get(imageView);
        TranslationModel translationModel = model.getTranslationModel();
        lastX = event.getSceneX() - translationModel.getTranslateX();
        lastY = event.getSceneY() - translationModel.getTranslateY();
    }

    /**
     * Handles the mouse dragged event to move the image.
     *
     * @param event the mouse dragged event
     */
    private void handleMouseDragged(javafx.scene.input.MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        ImageModel model = imageModelMap.get(imageView);
        TranslationModel translationModel = model.getTranslationModel();
        double deltaX = event.getSceneX() - lastX;
        double deltaY = event.getSceneY() - lastY;

        Command translateCommand = new TranslationCommand(translationModel, deltaX, deltaY);
        commandManager.executeCommand(translateCommand);
        commands.push(translateCommand);
    }

    /**
     * Undoes the last command executed on ImageView2.
     */
    @FXML
    public void undoImageView2() {
        commandManager.undoCommand();
    }

    /**
     * Undoes the last command executed on ImageView3.
     */
    @FXML
    public void undoImageView3() {
        commandManager.undoCommand();
    }

    /**
     * Updates the views when a model is changed.
     *
     * @param o the Observable object that changed
     */
    @Override
    public void update(Observable o) {
        for (ImageView imageView : imageModelMap.keySet()) {
            ImageModel model = imageModelMap.get(imageView);
            if (o == model.getZoomModel() || o == model.getTranslationModel()) {
                TranslationModel translationModel = model.getTranslationModel();
                ZoomModel zoomModel = model.getZoomModel();
                imageView.setTranslateX(translationModel.getTranslateX());
                imageView.setTranslateY(translationModel.getTranslateY());
                imageView.setScaleX(zoomModel.getZoom());
                imageView.setScaleY(zoomModel.getZoom());
            }
        }
    }

}
