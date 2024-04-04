package eat.neilson.Cafe;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.geometry.Pos;

import java.util.Map;
import java.util.Optional;

/**
 * Cart Controller class for viewing the current order
 *
 * @author Danny Onuorah
 */
public class CartViewController {
    private CafeViewController app;
    private Stage stage;
    private Order order;
    private ObservableMap<MenuItem, Integer> cart;

    @FXML
    public Label subtotalText;
    @FXML
    public Label taxText;
    @FXML
    public Label totalText;
    @FXML
    public Button placeOrderButton;
    @FXML
    public Button cancelOrderButton;
    @FXML
    public Label orderNumberLabel;

    @FXML
    private TableView<Map.Entry<MenuItem, Integer>> menuItemTable;

    /**
     * Creates the cart view table
     */
    public void initializeTable() {
        TableColumn<Map.Entry<MenuItem, Integer>, String> itemColumn = getItemColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, String> addOnColumn = getAddOnColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, Void> removeColumn = getRemoveColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, Void> quantityColumn = getQuantityColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, String> priceColumn = getPriceColumn();

        menuItemTable.getColumns().addAll(itemColumn, addOnColumn, removeColumn, quantityColumn, priceColumn);
        menuItemTable.setItems(FXCollections.observableArrayList(cart.entrySet()));
        updateTotal();
    }

    /**
     * Creates the name column in the view table
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
     * Creates the addOn column in the view table
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
     * Creates the remove button column in the view table
     */
    private TableColumn<Map.Entry<MenuItem, Integer>, Void> getRemoveColumn() {
        TableColumn<Map.Entry<MenuItem, Integer>, Void> removeColumn = new TableColumn<>("Remove");
        removeColumn.setCellFactory(param -> new TableCell<>() {
            private final Button removeButton = new Button("x");

            {
                removeButton.setOnAction(event -> {
                    Map.Entry<MenuItem, Integer> item = getTableView().getItems().get(getIndex());
                    MenuItem menuItem = item.getKey();

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Dialog");
                    alert.setHeaderText("Remove Item");
                    alert.setContentText("Are you sure you want to remove: " + menuItem.toString() + "?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        app.removeItemFromOrder(menuItem, app.getItemCount(menuItem));
                        menuItemTable.setItems(FXCollections.observableArrayList(order.getCart().entrySet()));
                        getTableView().refresh();
                        initializeElements();
                        updateTotal();
                    }
                });

                removeButton.setStyle("-fx-background-radius: 50%;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox pane = new HBox(removeButton);
                    pane.setAlignment(Pos.CENTER);
                    pane.setSpacing(10);
                    setGraphic(pane);
                }
            }
        });

        return removeColumn;
    }

    /**
     * Creates the quantity column in the view table
     */
    private TableColumn<Map.Entry<MenuItem, Integer>, Void> getQuantityColumn() {
        TableColumn<Map.Entry<MenuItem, Integer>, Void> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellFactory(param -> new TableCell<>() {
            private final Button incrementButton = new Button("+");
            private final Button decrementButton = new Button("-");
            private final Label quantityLabel = new Label();

            {
                incrementButton.setOnAction(event -> {
                    Map.Entry<MenuItem, Integer> item = getTableView().getItems().get(getIndex());
                    item.setValue(item.getValue() + 1);
                    quantityLabel.setText(item.getValue().toString());
                    getTableView().refresh();
                    updateTotal();
                    decrementButton.setDisable(item.getValue() <= 1); // Disable decrement button when quantity is 1
                });

                decrementButton.setOnAction(event -> {
                    Map.Entry<MenuItem, Integer> item = getTableView().getItems().get(getIndex());
                    if (item.getValue() > 1) {
                        item.setValue(item.getValue() - 1);
                        quantityLabel.setText(item.getValue().toString());
                        getTableView().refresh();
                        updateTotal();
                    }
                    decrementButton.setDisable(item.getValue() <= 1); // Disable decrement button when quantity is 1
                });

                incrementButton.setStyle("-fx-background-radius: 50%;");
                decrementButton.setStyle("-fx-background-radius: 50%;");
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Map.Entry<MenuItem, Integer> entry = getTableView().getItems().get(getIndex());
                    quantityLabel.setText(entry.getValue().toString());
                    decrementButton.setDisable(entry.getValue() <= 1); // Disable decrement button when quantity is 1
                    HBox pane = new HBox(decrementButton, quantityLabel, incrementButton);
                    pane.setAlignment(Pos.CENTER);
                    pane.setSpacing(10);
                    setGraphic(pane);
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
     * Set order number and button binding boolean property
     */
    public void initializeElements() {
        BooleanBinding confirmCondition = Bindings.createBooleanBinding(() ->
                        cart.isEmpty(),
                cart);
        placeOrderButton.disableProperty().bind(confirmCondition);
        orderNumberLabel.setText("Order Number #" + order.getOrderNumber());
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
        app = controller;
        this.order = controller.getOrder();
        this.cart = FXCollections.observableMap(controller.getCart());
        this.stage = stage;
    }

    /**
     * Places the current order
     */
    public void onOrderButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm Order");
        alert.setContentText("Confirm your order with a total of: " + totalText.getText() + "?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            app.placeOrder();
            stage.close();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Order Placed");
            alert.setContentText("Your order has been placed.");
            alert.showAndWait();
        }
    }

    /**
     * Cancels the current order
     */
    public void onCancelButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel Order");
        alert.setContentText("Are you sure you want to cancel your order?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            app.newOrder();
            stage.close();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Order Cancelled");
            alert.setContentText("Your order has been cancelled.");
            alert.showAndWait();
        }
    }
}
