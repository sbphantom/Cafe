package eat.neilson.Cafe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CafeViewController {

    private Stage primaryStage;
    private Scene primaryScene;


    @FXML
    private Label welcomeText;




    @FXML
    public void initialize() {



    }

    public void setPrimaryStage(Stage stage, Scene scene) {
        primaryStage = stage;
        primaryScene = scene;
    }

    @FXML
    protected void onCoffeeButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        Stage coffee = new Stage();
        AnchorPane root;
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            CoffeeViewController coffeeController = loader.getController();

            coffeeController.setMainController(this, coffee, primaryStage, primaryScene);

        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading coffee-view.fxml.");
            alert.setContentText("Couldn't load coffee-view.fxml.");
            alert.showAndWait();
        }

    }
    @FXML
    protected void onDonutButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onSandwichButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onCurrentOrderButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    protected void onOrderHistoryClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }






}