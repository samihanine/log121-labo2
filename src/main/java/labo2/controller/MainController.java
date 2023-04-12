package labo2.controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import labo2.model.ImageModel;
import labo2.model.TranslationModel;
import labo2.model.ZoomModel;


public class MainController {

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    private ImageController imageController;

    private CommandController commandController;

    private FileUploadController fileUploadController = new FileUploadController();

    @FXML
    public void initialize() {
        imageController = new ImageController(imageView2, imageView3);
        commandController = new CommandController();

        imageView2.setOnScroll(this::handleScroll);
        imageView3.setOnScroll(this::handleScroll);

        imageView2.setOnMousePressed(event -> {
            imageView2.setMouseTransparent(true);
            handleMousePressed(event);
        });
        imageView2.setOnMouseDragged(event -> {
            imageView2.setMouseTransparent(true);
            handleMouseDragged(event);
        });
        imageView2.setOnMouseReleased(event -> {
            imageView2.setMouseTransparent(false);
        });
    
        imageView3.setOnMousePressed(event -> {
            imageView3.setMouseTransparent(true);
            handleMousePressed(event);
        });
        imageView3.setOnMouseDragged(event -> {
            imageView3.setMouseTransparent(true);
            handleMouseDragged(event);
        });
        imageView3.setOnMouseReleased(event -> {
            imageView3.setMouseTransparent(false);
        });
    }
    
    public void loadImage() {
        fileUploadController.loadImage(imageView1, imageView2, imageView3);
    }
    
    private void handleScroll(ScrollEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        ImageModel model = imageController.getModel(imageView);
        ZoomModel zoomModel = model.getZoomModel();
        double deltaY = event.getDeltaY();
        commandController.handleScroll(zoomModel, deltaY);
        event.consume();
    }
    
    private double lastX;
    private double lastY;
    
    private void handleMousePressed(javafx.scene.input.MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        ImageModel model = imageController.getModel(imageView);
        TranslationModel translationModel = model.getTranslationModel();
        lastX = event.getSceneX() - translationModel.getTranslateX();
        lastY = event.getSceneY() - translationModel.getTranslateY();
    }
    
    private void handleMouseDragged(javafx.scene.input.MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        ImageModel model = imageController.getModel(imageView);
        TranslationModel translationModel = model.getTranslationModel();
        double deltaX = event.getSceneX() - lastX;
        double deltaY = event.getSceneY() - lastY;
        commandController.handleMouseDragged(translationModel, deltaX, deltaY);
    }
    
    @FXML
    public void undoImageView2() {
        commandController.undoCommand();
    }
    
    @FXML
    public void undoImageView3() {
        commandController.undoCommand();
    }
}    