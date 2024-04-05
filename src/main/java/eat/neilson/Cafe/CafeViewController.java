package eat.neilson.Cafe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

/**
 * Main Controller of the ordering software main menu
 *
 * @author Danny Onuorah
 */
public class CafeViewController {
    @FXML
    public Button coffeeButton;
    @FXML
    public Button donutButton;
    @FXML
    public Button sandwichButton;
    @FXML
    public Button cartButton;
    @FXML
    public Button historyButton;
    private CafeMain main;
    private Stage primaryStage;

    /**
     * Links the parent application and stage to class
     *
     * @param stage Cafe view stage
     * @param main  cafe application
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
    public LinkedHashMap<Integer, Order> getOrderHistory() {
        return main.getOrderHistory();
    }

    /**
     * Returns the current order
     *
     * @return current order
     */
    public Order getOrder() {
        return main.getCurrentOrder();
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
     * @param item to check
     * @return quantity in cart
     */
    public int getItemCount(MenuItem item) {
        return main.getCurrentOrder().itemCount(item);
    }

    /**
     * Adds the item and quantity to the current order
     *
     * @param item     to add
     * @param quantity to add
     * @return true if added successfully
     */
    public boolean addItemToOrder(MenuItem item, int quantity) {
        return main.addItem(item, quantity);
    }

    /**
     * Removes the item and quantity from the current order
     *
     * @param item     to remove
     * @param quantity to remove
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
        main.createOrder();
    }

    /**
     * Remove a previously placed order
     */
    public void removeOrder(int orderNumber) {
        main.removeOrder(orderNumber);
    }

    /**
     * Initialize button ImageViews
     */
    @FXML
    protected void initialize(){
        Image coffeeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/coffee_icon.jpg")));
        ImageView coffeeImageView = new ImageView(coffeeImage);
        coffeeImageView.setFitWidth(50);
        coffeeImageView.setFitHeight(50);
        coffeeButton.setGraphic(coffeeImageView);

        Image donutImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/donut_icon.jpg")));
        ImageView donutImageView = new ImageView(donutImage);
        donutImageView.setFitWidth(50);
        donutImageView.setFitHeight(50);
        donutButton.setGraphic(donutImageView);

        Image sandwichImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/sandwich_icon.jpg")));
        ImageView sandwichImageView = new ImageView(sandwichImage);
        sandwichImageView.setFitWidth(50);
        sandwichImageView.setFitHeight(50);
        sandwichButton.setGraphic(sandwichImageView);

        Image cartImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/cart_icon.jpg")));
        ImageView cartImageView = new ImageView(cartImage);
        cartImageView.setFitWidth(50);
        cartImageView.setFitHeight(50);
        cartButton.setGraphic(cartImageView);

        Image historyImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/history_icon.jpg")));
        ImageView historyImageView = new ImageView(historyImage);
        historyImageView.setFitWidth(50);
        historyImageView.setFitHeight(50);
        historyButton.setGraphic(historyImageView);


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
    protected void onOrderHistoryButtonClick() {
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
        }
    }

}