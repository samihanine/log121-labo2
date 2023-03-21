package labo2.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.stage.FileChooser;
import labo2.model.ImageModel;
import labo2.model.TranslationModel;
import labo2.model.ZoomModel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ImageController {

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    private Map<ImageView, ImageModel> imageModelMap = new HashMap<>();

    private double lastX;
    private double lastY;

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
        }
    }

    @FXML
    public void initialize() {
        initializeImageViews(imageView1, imageView2, imageView3);
    }

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
            for (ImageView imageView : imageModelMap.keySet()) {
                ImageModel model = imageModelMap.get(imageView);
                model.getZoomModel().setZoom(1.0);
                model.getTranslationModel().setTranslateX(0.0);
                model.getTranslationModel().setTranslateY(0.0);
                syncImageViewWithModel(imageView, model);
            }
        }
    }

    private void handleScroll(ScrollEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        ImageModel model = imageModelMap.get(imageView);
        ZoomModel zoomModel = model.getZoomModel();
        double zoomFactor = 1.05;
        double deltaY = event.getDeltaY();
        if (deltaY < 0) {
            zoomFactor = 0.95;
        }
        zoomModel.setZoom(zoomModel.getZoom() * zoomFactor);
        syncImageViewWithModel(imageView, model);
        event.consume();
    }

    private void handleMousePressed(javafx.scene.input.MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        ImageModel model = imageModelMap.get(imageView);
        TranslationModel translationModel = model.getTranslationModel();
        lastX = event.getSceneX() - translationModel.getTranslateX();
        lastY = event.getSceneY() - translationModel.getTranslateY();
    }

    private void handleMouseDragged(javafx.scene.input.MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        ImageModel model = imageModelMap.get(imageView);
        TranslationModel translationModel = model.getTranslationModel();
        double deltaX = event.getSceneX() - lastX;
        double deltaY = event.getSceneY() - lastY;

        translationModel.setTranslateX(deltaX);
        translationModel.setTranslateY(deltaY);
        syncImageViewWithModel(imageView, model);
    }

    private void syncImageViewWithModel(ImageView imageView, ImageModel model) {
        TranslationModel translationModel = model.getTranslationModel();
        ZoomModel zoomModel = model.getZoomModel();
        imageView.setTranslateX(translationModel.getTranslateX());
        imageView.setTranslateY(translationModel.getTranslateY());
        imageView.setScaleX(zoomModel.getZoom());
        imageView.setScaleY(zoomModel.getZoom());
    }

    public void undo() {
        for (ImageView imageView : imageModelMap.keySet()) {
            ImageModel model = imageModelMap.get(imageView);
            TranslationModel translationModel = model.getTranslationModel();
            ZoomModel zoomModel = model.getZoomModel();
            translationModel.undo();
            zoomModel.undo();
            syncImageViewWithModel(imageView, model);
        }
    }
}
