package eat.neilson.Cafe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Main Controller of the ordering software main menu
 *
 * @author Danny Onuorah
 */
public class CafeViewController {

    public CafeMain main;
    private Stage primaryStage;

    /**
     * Links the parent application and stage to class
     */
    public void setPrimaryStage(Stage stage, CafeMain main) {
        primaryStage = stage;
        this.main = main;
    }

    /**
     * Returns the order history
     *
     * @return hashmap of orders
     */
    public LinkedHashMap<Integer, Order> getOrderHistory(){
    return main.orderHistory;
}

    /**
     * Returns the current order
     *
     * @return current order
     */
    public Order getOrder() {
        return main.currentOrder;
    }

    /**
     * Returns the cart of the current order
     *
     * @return cart of the current order
     */
    public HashMap<MenuItem, Integer> getCart() {
        return getOrder().getCart();
    }

    /**
     * Returns the quantity of an item in the current order
     *
     * @return quantity in cart
     */
    public int getItemCount(MenuItem item){
        return main.currentOrder.itemCount(item);
    }

    /**
     * Adds the item and quantity to the current order
     *
     * @return true if added successfully
     */
    public boolean addItemToOrder(MenuItem item, int quantity) {
        return main.addItem(item, quantity);
    }

    /**
     * Removes the item and quantity from the current order
     *
     * @return true if removed successfully
     */
    public boolean removeItemFromOrder(MenuItem item, int quantity) {
        return main.removeItem(item, quantity);
    }

    /**
     * Places the current order and prepares for the next order
     */
    public void placeOrder() {
        main.addOrder();
    }

    /**
     * Creates a new order
     */
    public void newOrder() {
        main.currentOrder = main.createOrder();

    }



    /**
     * Launches coffee ordering menu
     */
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

    /**
     * Launches donut ordering menu
     */
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

    /**
     * Launches sandwich ordering menu
     */
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

    /**
     * Launches checkout menu
     */
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

    /**
     * Launches order history menu
     */
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

}