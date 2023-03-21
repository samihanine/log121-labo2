package labo2.model;

import java.io.Serializable;

public class FileModel implements Serializable {

    private String imagePath;

    public FileModel() {
    }

    public FileModel(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
