package org.vin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * User Application that allows simple encryption and decryption of selected text
 */
public class App extends Application {

    public static void main(String[] args) {
        launch();
    }

    /**
     * Initializes the window and shows to the user
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        int width = 600;
        int height = 400;
        FXMLLoader loader = loadFXML("app");
        Parent root = loader.load(); // Required to use in order to initialize ANY VALUE.
        myController = loader.getController();
        myData = new Model(myController); // Instantiates Controller class in Model class
        myController.setModel(myData); // Instantiates Model class in Controller class
        scene = new Scene(root);
        String css = getClass().getResource("/org/vin/styles.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cryption");
        primaryStage.initStyle(StageStyle.UNIFIED);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - width)/ 2);
        primaryStage.setY((screenBounds.getHeight() - height)/ 2);
        primaryStage.show();
    }

    /**
     * Sets the new current window
     * @param fxml the current fxml used
     * @throws IOException if there is no fxml file with that name
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    /**
     *
     * @param fxml
     * @return
     * @throws IOException if there is no fxml file with that name
     */
    private static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }


    /**
     * Getter method for Controller
     * @return      Controller for app laoded with fxml
     */
    public Controller getMyController(){
        return myController;
    }


    /**
     * Getter method for Model
     * @return      Model for app laoded with fxml
     */
    public Model getMyData(){
        return myData;
    }



    private Controller myController;
    private Model myData;
    private static Scene scene;

}