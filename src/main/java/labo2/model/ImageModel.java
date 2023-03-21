package labo2.model;

public class ImageModel {
    private TranslationModel translationModel;
    private ZoomModel zoomModel;

    public ImageModel() {
        this.translationModel = new TranslationModel();
        this.zoomModel = new ZoomModel();
    }

    public TranslationModel getTranslationModel() {
        return translationModel;
    }

    public ZoomModel getZoomModel() {
        return zoomModel;
    }
}
