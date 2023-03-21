package labo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    /**
     * The start() method is called when the application is launched.
     * It loads the primary FXML file and sets the scene for the stage.
     *
     * @param stage the primary stage for the application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1200, 600);
        scene.getStylesheets().add(App.class.getResource("view/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Sets the root node for the scene.
     *
     * @param fxml the name of the FXML file to load
     * @throws IOException if the FXML file cannot be loaded
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Loads an FXML file and returns its root node.
     *
     * @param fxml the name of the FXML file to load
     * @return the root node of the FXML file
     * @throws IOException if the FXML file cannot be loaded
     */
    private static Parent loadFXML(String fxml) throws IOException {
        URL fxmlUrl = App.class.getResource("/labo2/view/" + fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
        return fxmlLoader.load();
    }

    /**
     * The main() method is the entry point for the application.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }

}
