package com.karp.javastroids_v2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.image.Image;

/**
 * Contains main program code. The program launches to a GUI menu where user can 
 * choose to enter a game of Asteroids and play until all lives are lost, or they
 * can choose to view the all time leader board scores with importing/exporting 
 * capability for the leader board data.
 *
 * @author Justin Karp
 * @version 1.0
 * @since 12/8/2021
 */
public class App extends Application {

    private static Scene scene;
    private static Stage currentStage;
    
    /**
     * Method for getting the current-active scene of the Application.
     * 
     * @return Scene object of current stage.
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * Method for setting what the current scene should be for the stage.
     * 
     * @param scene The desired current-active scene for the program's GUI.
     */
    public static void setScene(Scene scene) {
        App.scene = scene;
    }
    
    /**
     * Method for getting the current stage used for the Application.
     * 
     * @return Current Stage object of the running application.
     */
    public static Stage getStage() {
        return currentStage;
    }
    
    /**
     * Main method to initiate the program.
     * 
     * @param args 
     */
    public static void main(String[] args) {
        launch();
    }
    
    /**
     * Method for instantiating the scene, stage, App icon and Window/resolution
     * restrictions.
     * 
     * @param stage The desired stage to be used for the Application.
     * @throws IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        ScoreDataManagement.initializeDatabaseConnection();
        currentStage = stage;
        scene = new Scene(loadFXML("mainMenu"), 960, 720);
        stage.setScene(scene);
        stage.setMinWidth(800);
        stage.setMinHeight(640);
        stage.setTitle("JavaStroids");
        Image icon = new Image(getClass().getResourceAsStream("javastroids_icon.png"));
        stage.getIcons().add(icon);
        stage.show();
        
        App.getStage().setOnHiding(e -> {
            GameLoop.stopAllThreads();
            Platform.exit();
        });
    }

    /**
     * Method for setting the current FXML-based scene setup.
     * 
     * @param fxml The string name of the FXML file to be used, excluding filetype.
     * @throws IOException 
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}