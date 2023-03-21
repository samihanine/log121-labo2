package labo2.model;

import java.io.Serializable;

public class ImageModel implements Serializable {
    private ZoomModel zoomModel;
    private TranslationModel translationModel;
    private FileModel fileModel; // added fileModel field

    public ImageModel() {
        this.zoomModel = new ZoomModel();
        this.translationModel = new TranslationModel();
        this.fileModel = new FileModel(); // initialize fileModel
    }

    public ZoomModel getZoomModel() {
        return zoomModel;
    }

    public TranslationModel getTranslationModel() {
        return translationModel;
    }

    public FileModel getFileModel() { // added getter method
        return fileModel;
    }

    public void setFileModel(FileModel fileModel) { // added setter method
        this.fileModel = fileModel;
    }
}
