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
        
        try { //it is possible to have an IOException because of the errors in the fxml file

            FXMLLoader loader = new FXMLLoader(getClass().getResource("coffee-view.fxml"));

            System.out.println("1");

            root = (AnchorPane) loader.load(); //type-cast to the data type of the root node


            System.out.println("2");


            Scene scene = new Scene(root, 500, 400);
//            System.out.println(scene);

            System.out.println("a");
            
            //view1.setScene(scene); //if you want to use the new window to display the new scene
            //view1.setTitle("view1");
            //view1.show();

//            Stage primaryStage;
            primaryStage.setScene(scene); //use the primary stage to display the new scene graph
            CoffeeViewController coffeeController = loader.getController();

            System.out.println("b");

            /*
              The statement below is to pass the reference of the MainController object
              to the View1Controller object so the View1Controller can call the
              public methods in the MainController.
             */
            coffeeController.setMainController(this, coffee, primaryStage, primaryScene);
            System.out.println("c");

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