package labo2.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.stage.FileChooser;
import java.io.File;
import javafx.scene.input.MouseEvent;

public class ImageController {

    @FXML
    private ImageView imageView1;

    @FXML
    private ImageView imageView2;

    @FXML
    private ImageView imageView3;

    private double lastX;
    private double lastY;

    private void initializeImageViews(ImageView... imageViews) {
        for (ImageView imageView : imageViews) {
            imageView.setPreserveRatio(true);
            imageView.setOnScroll(this::handleScroll);
            imageView.setOnMousePressed(event -> {
                imageView.setMouseTransparent(true);
            });
            imageView.setOnMouseReleased(event -> {
                imageView.setMouseTransparent(false);
            });
        }
    }

    @FXML
    public void initialize() {
        initializeImageViews(imageView2, imageView3);
    }

    public void loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(null);

        // Le reste de votre code pour charger l'image
        if (selectedFile != null) {
            System.out.println("Selected image path: " + selectedFile.toURI().toString()); // Print image path
            Image image = new Image(selectedFile.toURI().toString());
            imageView1.setImage(image);
            imageView2.setImage(image);
            imageView3.setImage(image);
        }
    }

    private void handleScroll(ScrollEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        double zoomFactor = 1.05;
        double deltaY = event.getDeltaY();
        if (deltaY < 0) {
            zoomFactor = 0.95;
        }
        imageView.setScaleX(imageView.getScaleX() * zoomFactor);
        imageView.setScaleY(imageView.getScaleY() * zoomFactor);
        event.consume();
    }

    private void handleMousePressed(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        lastX = event.getSceneX();
        lastY = event.getSceneY();
    }

    private void handleMouseDragged(MouseEvent event) {
        ImageView imageView = (ImageView) event.getSource();
        double deltaX = event.getSceneX() - lastX;
        double deltaY = event.getSceneY() - lastY;

        imageView.setTranslateX(imageView.getTranslateX() + deltaX);
        imageView.setTranslateY(imageView.getTranslateY() + deltaY);

        lastX = event.getSceneX();
        lastY = event.getSceneY();
    }
}
