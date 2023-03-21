package labo2.model;

import java.io.Serializable;

public class ImageModel implements Serializable {
    private ZoomModel zoomModel;
    private TranslationModel translationModel;

    public ImageModel() {
        this.zoomModel = new ZoomModel();
        this.translationModel = new TranslationModel();
    }

    public ZoomModel getZoomModel() {
        return zoomModel;
    }

    public TranslationModel getTranslationModel() {
        return translationModel;
    }
}
