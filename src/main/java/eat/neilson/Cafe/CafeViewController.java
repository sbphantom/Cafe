package eat.neilson.Cafe;

import javafx.collections.ObservableMap;
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
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CafeViewController {

    public CafeMain main;
    private Stage primaryStage;
    private Scene primaryScene;


    @FXML
    private Label welcomeText;


    @FXML
    public void initialize() {

    }


    public void setPrimaryStage(Stage stage, CafeMain main) {
        primaryStage = stage;
        this.main = main;
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

    public boolean addItemToOrder(MenuItem item, int quantity) {
        return main.addItem(item, quantity);
    }

    public boolean removeItemFromOrder(MenuItem item, int quantity) {
        return main.removeItem(item, quantity);
    }


    public int getItemCount(MenuItem item){
        return main.currentOrder.itemCount(item);
    }

    public Order getOrder() {
        return main.currentOrder;
    }

    public LinkedHashMap<Integer, Order> getOrderHistory(){
        return main.orderHistory;
    }

    @FXML
    protected void onDonutButtonClick() {
        try {
            Stage donutStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("donuts-view.fxml"));

            donutStage.setScene(new Scene(loader.load()));
            donutStage.setResizable(false);
            donutStage.setTitle("Donut Menu");
            donutStage.initModality(Modality.APPLICATION_MODAL);
            donutStage.setOnHidden(e -> {
                primaryStage.requestFocus();
            });

            DonutViewController donutController = loader.getController();
            donutController.setMainController(this);

            donutStage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading donuts-view.fxml.");
            alert.setContentText("Couldn't load donuts-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onSandwichButtonClick() {
        try {
            Stage sandwichStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sandwiches-view.fxml"));

            sandwichStage.setScene(new Scene(loader.load()));
            sandwichStage.setResizable(false);
            sandwichStage.setTitle("Sandwich Menu");
            sandwichStage.initModality(Modality.APPLICATION_MODAL);
            sandwichStage.setOnHidden(e -> {
                primaryStage.requestFocus();
            });

           SandwichViewController sandwichController = loader.getController();
            sandwichController.setMainController(this);

            sandwichStage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading coffee-view.fxml.");
            alert.setContentText("Couldn't load coffee-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onCurrentOrderButtonClick() {
        try {
            Stage cartStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart-view.fxml"));

            cartStage.setScene(new Scene(loader.load()));
            cartStage.setResizable(false);
            cartStage.setTitle("Current Order");
            cartStage.initModality(Modality.APPLICATION_MODAL);
            cartStage.setOnHidden(e -> {
                primaryStage.requestFocus();
            });

            CartViewController cartController = loader.getController();
            cartController.setMainController(this, cartStage);
            cartController.initializeTable();
            cartController.initializeElements();

            cartStage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading cart-view.fxml.");
            alert.setContentText("Couldn't load cart-view.fxml.");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onOrderHistoryClick() {
        try {
            Stage historyStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history-view.fxml"));

            historyStage.setScene(new Scene(loader.load()));
            historyStage.setResizable(false);
            historyStage.setTitle("Order History");
            historyStage.initModality(Modality.APPLICATION_MODAL);
            historyStage.setOnHidden(e -> {
                primaryStage.requestFocus();
            });

            HistoryViewController historyController = loader.getController();
            historyController.setMainController(this, historyStage);
            historyController.initializeElements();
            historyStage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Loading history-view.fxml.");
            alert.setContentText("Couldn't load history-view.fxml.");
            alert.showAndWait();
        }    }

    public HashMap<MenuItem, Integer> getCart() {
        return getOrder().getCart();
    }

    public void placeOrder() {
        main.addOrder();
    }

    public void newOrder() {
        main.currentOrder = main.createOrder();

    }
}