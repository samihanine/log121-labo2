package labo2.controller;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.ImageView;
import labo2.model.ImageModel;
import labo2.model.TranslationModel;
import labo2.model.ZoomModel;
import labo2.observer.Observable;
import labo2.observer.Observer;


public class ImageController implements Observer {

    public Map<ImageView, ImageModel> imageModelMap = new HashMap<>();

    public ImageController(ImageView... imageViews) {
        for (ImageView imageView : imageViews) {
            imageModelMap.put(imageView, new ImageModel());

            ImageModel model = imageModelMap.get(imageView);
            model.getZoomModel().addObserver(this);
            model.getTranslationModel().addObserver(this);
        }
    }

    public ImageModel getModel(ImageView imageView) {
        return imageModelMap.get(imageView);
    }

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
