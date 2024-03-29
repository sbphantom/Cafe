package eat.neilson.Cafe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CafeViewController {

    public CafeMain main = new CafeMain();
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

    public boolean addItemToOrder(MenuItem item, int quantity){
        return main.addItem(item, quantity);
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

        Stage donut = new Stage();
        AnchorPane root;

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sandwiches-view.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            DonutViewController donutViewController = loader.getController();
            donutViewController.setMainController(this, donut, primaryStage, primaryScene);
        }
        catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading sandwiches-view.fxml.");
            alert.setContentText("Couldn't load sandwiches-view.fxml.");
            alert.showAndWait();
        }
    }
    @FXML
    protected void onSandwichButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

        Stage sandwich = new Stage();
        AnchorPane root;

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sandwiches-view.fxml"));
            root = (AnchorPane) loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            SandwichViewController sandwichController = loader.getController();
            sandwichController.setMainController(this, sandwich, primaryStage, primaryScene);
        }
        catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading sandwiches-view.fxml.");
            alert.setContentText("Couldn't load sandwiches-view.fxml.");
            alert.showAndWait();
        }
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