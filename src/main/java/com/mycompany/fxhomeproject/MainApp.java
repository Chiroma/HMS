package com.mycompany.fxhomeproject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Application Class.
 * @author Josh
 */
public class MainApp extends Application {
    private Stage stage;
    /**
     * The main entry point for all JavaFX applications.
     * @param stage the primary stage for this application
     * @throws Exception if there is a problem
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        home();

    }
    /**
     * Opens the Main scene
     */
    public void home(){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
            Scene scene = new Scene(root);
            
            stage.setTitle("JavaFX and Maven");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
     /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
