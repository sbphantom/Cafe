package eat.neilson.Cafe;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * History Controller class for viewing the transaction history
 *
 * @author Danny Onuorah
 */
public class HistoryViewController {
    @FXML
    public Label subtotalText;
    @FXML
    public Label taxText;
    @FXML
    public Label totalText;
    @FXML
    public Label orderNumberLabel;
    @FXML
    public TextField currentPageTextField;
    @FXML
    public TextField pageCountTextField;
    @FXML
    public MenuButton orderNumberSelector;
    @FXML
    public Button viewPreviousButton;
    @FXML
    public Button viewFirstButton;
    @FXML
    public Button viewLastButton;
    @FXML
    public Button viewNextButton;
    @FXML
    public Button cancelOrderButton;
    @FXML
    public Button exportButton;
    @FXML
    private TableView<Map.Entry<MenuItem, Integer>> menuItemTable;

    private Stage stage;
    private CafeViewController app;
    private ObservableMap<Integer, Order> history;
    private Order order;
    private ObservableMap<MenuItem, Integer> cart;

    private List<Integer> keys;

    /**
     * Updates the view to newly selected order
     */
    private void handleOrderNumberSelection(Integer id) {
        orderNumberSelector.setUserData(id);
        int index = keys.indexOf(id);
        currentPageTextField.setText(String.valueOf(index + 1));

        order = history.get(id);
        cart = FXCollections.observableMap(order.getCart());

        orderNumberSelector.setText(keys.indexOf(id) + 1 + ")  #" + id.toString());
        initializeTable();

        viewPreviousButton.setDisable(index == 0);
        viewNextButton.setDisable(index == keys.size() - 1);

//        menuItemTable.setItems(FXCollections.observableArrayList(cart.entrySet()));
//        menuItemTable.refresh();
    }

    /**
     * Creates the order view table
     */
    public void initializeTable() {
        TableColumn<Map.Entry<MenuItem, Integer>, String> itemColumn = getItemColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, String> addOnColumn = getAddOnColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, Integer> quantityColumn = getQuantityColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, String> priceColumn = getPriceColumn();

        menuItemTable.getColumns().setAll(itemColumn, addOnColumn, quantityColumn, priceColumn);
        menuItemTable.setItems(FXCollections.observableArrayList(cart.entrySet()));
        updateTotal();
    }

    /**
     * Creates the name column for the view table
     */
    private TableColumn<Map.Entry<MenuItem, Integer>, String> getItemColumn() {
        TableColumn<Map.Entry<MenuItem, Integer>, String> itemColumn = new TableColumn<>("Item");

        itemColumn.setCellValueFactory(data -> {
            MenuItem menuItem = data.getValue().getKey();
            return new SimpleStringProperty(menuItem.name());
        });

        itemColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER_LEFT);
                }
            }
        });
        return itemColumn;
    }

    /**
     * Creates the addOn column for the view table
     */
    private TableColumn<Map.Entry<MenuItem, Integer>, String> getAddOnColumn() {
        TableColumn<Map.Entry<MenuItem, Integer>, String> addOnColumn = new TableColumn<>("Add Ons");

        addOnColumn.setCellValueFactory(data -> {
            MenuItem menuItem = data.getValue().getKey();
            return new SimpleStringProperty(menuItem.addOnString());
        });

        addOnColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER_LEFT);
                }
            }
        });
        return addOnColumn;
    }

    /**
     * Creates the quantity column in the view table
     */
    private TableColumn<Map.Entry<MenuItem, Integer>, Integer> getQuantityColumn() {
        TableColumn<Map.Entry<MenuItem, Integer>, Integer> quantityColumn = new TableColumn<>("Quantity");

        quantityColumn.setCellValueFactory(data -> {
            int count = cart.get(data.getValue().getKey());
            return new SimpleIntegerProperty(count).asObject();
        });

        quantityColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER);
                }
            }
        });


        return quantityColumn;
    }

    /**
     * Creates the price column in the view table
     */
    private TableColumn<Map.Entry<MenuItem, Integer>, String> getPriceColumn() {
        TableColumn<Map.Entry<MenuItem, Integer>, String> priceColumn = new TableColumn<>("Price");

        priceColumn.setCellValueFactory(data -> {
            String price = String.format("%.2f", data.getValue().getKey().price() * data.getValue().getValue());
            return new SimpleStringProperty(price);
        });

        priceColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER_RIGHT);
                }
            }
        });

        return priceColumn;
    }

    /**
     * Initializes UI elements and views first order
     */
    public void initializeElements() {
        pageCountTextField.setText(String.valueOf(history.size()));
        orderNumberSelector.getItems().clear();

        BooleanBinding disableButtons = Bindings.createBooleanBinding(() ->
                        history.isEmpty(),
                history);

        cancelOrderButton.disableProperty().bind(disableButtons);
        exportButton.disableProperty().bind(disableButtons);
        viewFirstButton.disableProperty().bind(disableButtons);
        viewLastButton.disableProperty().bind(disableButtons);

        int i = 1;
        for (Integer id : history.keySet()) {
            javafx.scene.control.MenuItem menuItem = new javafx.scene.control.MenuItem(i + ")  #" + id.toString());
            menuItem.setOnAction(event -> handleOrderNumberSelection(id));
            menuItem.setUserData(id);
            orderNumberSelector.getItems().add(menuItem);
            i++;
        }
        if (history.isEmpty()) {
            viewNextButton.setDisable(true);
            viewPreviousButton.setDisable(true);
            orderNumberSelector.setText("Select");
            currentPageTextField.setText(" ");

        } else
            onFirstButtonClick();
    }

    /**
     * Updates the text regarding total
     */
    public void updateTotal() {
        subtotalText.setText("$" + String.format("%.2f", order.getSubtotal()));
        taxText.setText("$" + String.format("%.2f", order.tax()));
        totalText.setText("$" + String.format("%.2f", order.getTotal()));
    }

    /**
     * Links the parent controller to child
     *
     * @param controller Parent CafeViewController
     * @param stage      Cafe view stage
     */
    public void setMainController(CafeViewController controller, Stage stage) {
        this.stage = stage;
        this.app = controller;
        this.order = controller.getOrder();
        this.cart = FXCollections.observableMap(controller.getCart());
        this.history = FXCollections.observableMap(controller.getOrderHistory());
        this.keys = new ArrayList<>(history.keySet());
    }

    /**
     * View previous order
     */
    public void onPreviousButtonClick() {
        viewNextButton.setDisable(false);
        handleOrderNumberSelection(keys.get(keys.indexOf(orderNumberSelector.getUserData()) - 1));
    }

    /**
     * View next order
     */
    public void onNextButtonClick() {
        viewPreviousButton.setDisable(false);
        handleOrderNumberSelection(keys.get(keys.indexOf(orderNumberSelector.getUserData()) + 1));

    }

    /**
     * View first order
     */
    public void onFirstButtonClick() {
        orderNumberSelector.setUserData(keys.getFirst());
        handleOrderNumberSelection(keys.getFirst());
    }

    /**
     * View last order
     */
    public void onLastButtonClick() {
        orderNumberSelector.setUserData(keys.getLast());
        handleOrderNumberSelection(keys.getLast());

    }

    /**
     * Cancel selected order
     */
    public void onCancelOrderButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Delete Order");
        alert.setContentText("Are you sure you want to delete Order Number #" + order.getOrderNumber() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            history.remove(order.getOrderNumber());
            keys.remove(order.getOrderNumber());
            menuItemTable.setItems(FXCollections.observableArrayList());
            subtotalText.setText("");
            taxText.setText("");
            totalText.setText("");

            initializeElements();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Order Removed");
            alert.setContentText("Order has been deleted.");
            alert.showAndWait();
        }
    }

    /**
     * Export orders to a textFile
     */
    public void onExportButton() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt"));
            for (Integer orderId : history.keySet()) {
                Order order = history.get(orderId);
                String orderText = generateOrderText(orderId, order);
                writer.write(orderText);
                writer.newLine();
            }
            writer.close();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Exporting Order");
            alert.setContentText("Your Orders have been exported to 'orders.txt'");
            alert.showAndWait();

        } catch (IOException e) {
            System.err.println("Error exporting orders: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Error exporting orders");
            alert.setContentText("Couldn't Export File \n" + e.getMessage());
            alert.showAndWait();
        }

    }

    /**
     * Generates export text for order
     *
     * @return formatted order string
     */
    public String generateOrderText(int orderID, Order order) {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderID).append("\n");
        sb.append("Items:\n");
        for (MenuItem item : order.getCart().keySet()) {
            int quantity = order.getCart().get(item);
            sb.append(" - ").append(item.name()).append(": ").append(quantity).append("x\n");
            sb.append("   Add-ons: ").append(item.addOnString()).append("\n");
            sb.append("   Price: $").append(String.format("%.2f", item.price() * quantity)).append("\n");
        }
        sb.append("Subtotal: $").append(String.format("%.2f", order.getSubtotal())).append("\n");
        sb.append("Tax: $").append(String.format("%.2f", order.tax())).append("\n");
        sb.append("Total: $").append(String.format("%.2f", order.getTotal())).append("\n");
        sb.append("\n");
        return sb.toString();

    }
}
