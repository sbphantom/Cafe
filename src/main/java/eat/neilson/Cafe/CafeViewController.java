package eat.neilson.Cafe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
//import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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


    public void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
//    public void setPrimaryStage(Stage stage, Scene scene) {
//        primaryStage = stage;
//        primaryScene = scene;
//    }

    @FXML
    protected void onCoffeeButtonClick() {
        try {
            Stage coffeeStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));

            coffeeStage.setScene(new Scene(loader.load()));
            coffeeStage.setResizable(false);
            coffeeStage.setTitle("Coffee Menu");
            coffeeStage.initModality(Modality.APPLICATION_MODAL);
            coffeeStage.setOnHidden(e -> {
                primaryStage.requestFocus();
            });

            CoffeeViewController coffeeController = loader.getController();
            coffeeController.setMainController(this);

            coffeeStage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading coffee-view.fxml.");
            alert.setContentText("Couldn't load coffee-view.fxml.");
            alert.showAndWait();
        }
    }

    public boolean addItemToOrder(MenuItem item, int quantity){
        return main.addItem(item, quantity);
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