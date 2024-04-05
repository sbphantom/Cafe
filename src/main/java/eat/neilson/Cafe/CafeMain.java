package eat.neilson.Cafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * Main Model of Neilson Cafe ordering software
 *
 * @author Danny Onuorah
 */
public class CafeMain extends Application {

    private LinkedHashMap<Integer, Order> orderHistory = new LinkedHashMap<>();
    private Order currentOrder;

    /**
     * Get order history
     *
     * @return transaction history
     */
    public LinkedHashMap<Integer, Order> getOrderHistory() {
        return orderHistory;
    }

    /**
     * Get current Order
     *
     * @return current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Creates an order with a random order number
     **/
    public void createOrder() {
        Random rand = new Random();
        int id;
        do id = rand.nextInt(9000) + 1000;
        while (orderHistory.containsKey(id));
        currentOrder = new Order(id);
    }

    /**
     * Places the current order amd setups the next order
     *
     * @return true if successfully added order
     */
    public boolean addOrder() {
        if (currentOrder.cartSize() > 0) {
            orderHistory.put(currentOrder.getOrderNumber(), currentOrder);
            createOrder();
            return true;
        }
        return false;
    }

    /**
     * Remove a previously placed order
     */
    public void removeOrder(int orderNumber) {
        orderHistory.remove(orderNumber);
    }

    /**
     * Add item and quantity to the current order cart
     *
     * @return true is successfully added
     */
    public boolean addItem(MenuItem item, int quantity) {
        return currentOrder.addItem(item, quantity);
    }

    /**
     * Remove item and quantity to the current order cart
     *
     * @return true is successfully removed
     */
    public boolean removeItem(MenuItem item, int quantity) {

        return currentOrder.removeItem(item, quantity);
    }


    /**
     * Starts the controller class
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeMain.class.getResource("cafe-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 540, 320);
        CafeViewController cafeController = fxmlLoader.getController();
        cafeController.setPrimaryStage(stage, this);
        stage.setTitle("Neilson Cafe");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        createOrder();
    }

    /**
     * Software entry point
     */
    public static void main(String[] args) {
        launch();
    }
}