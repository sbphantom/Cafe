package eat.neilson.Cafe;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.*;

/**
 * History Controller class for viewing the transaction history
 *
 * @author Danny Onuorah
 */
public class HistoryViewController {

    public Label subtotalText;
    public Label taxText;
    public Label totalText;
    public Label orderNumberLabel;
    public TextField currentPageTextField;
    public TextField pageCountTextField;
    public MenuButton orderNumberSelector;
    public Button viewPreviousButton;
    public Button viewFirstButton;
    public Button viewLastButton;
    public Button viewNextButton;
    @FXML
    private TableView<Map.Entry<MenuItem, Integer>> menuItemTable;

    private CafeViewController app;

    private Stage stage;

    ObservableMap<MenuItem, Integer> items;

    public Order order;

    public ObservableMap<MenuItem, Integer> cart;
    public ObservableMap<Integer, Order> history;

    List<Integer> keys;



    public void initializeElements() {
        pageCountTextField.setText(String.valueOf(history.size()));
        orderNumberSelector.getItems().clear();

        int i = 1;
        for (Integer id : history.keySet()) {
            javafx.scene.control.MenuItem menuItem = new javafx.scene.control.MenuItem(i + ")  #" + id.toString());
            menuItem.setOnAction(event -> handleOrderNumberSelection(id));
            menuItem.setUserData(id);
            orderNumberSelector.getItems().add(menuItem);
            i++;
        }
        onFirstButtonClick();
    }

    private void handleOrderNumberSelection(Integer id) {
        System.out.println(id);
        System.out.println(keys.indexOf(id));


        orderNumberSelector.setUserData(id);
        int index = keys.indexOf(id);
        currentPageTextField.setText(String.valueOf(index + 1));


        order = history.get(id);
        cart = FXCollections.observableMap(order.getCart());

        System.out.println(order.getCart());
        orderNumberSelector.setText(keys.indexOf(id) + 1 +")  #" + id.toString());
        initializeTable();

        viewPreviousButton.setDisable(index == 0);
        viewNextButton.setDisable(index == keys.size() - 1);

//        menuItemTable.setItems(FXCollections.observableArrayList(cart.entrySet()));
//        menuItemTable.refresh();
    }

    public void initializeTable() {
        TableColumn<Map.Entry<MenuItem, Integer>, String> itemColumn = getItemColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, String> addOnColumn = getAddOnColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, Integer> quantityColumn = getQuantityColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, String> priceColumn = getPriceColumn();

//        menuItemTable.getColumns().set
        menuItemTable.getColumns().setAll(itemColumn, addOnColumn, quantityColumn, priceColumn);
        menuItemTable.setItems(FXCollections.observableArrayList(cart.entrySet()));
        updateTotal();
    }

//    public void initializeElements() {
//        BooleanBinding confirmCondition = Bindings.createBooleanBinding(() ->
//                        cart.isEmpty(),
//                cart);
//        placeOrderButton.disableProperty().bind(confirmCondition);
//    }


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


    public void updateTotal() {
        subtotalText.setText("$" + String.format("%.2f", order.getSubtotal()));
        taxText.setText("$" + String.format("%.2f", order.tax()));
        totalText.setText("$" + String.format("%.2f", order.getTotal()));
    }

    public void setMainController(CafeViewController controller, Stage stage) {
        this.stage = stage;

        app = controller;
        this.order = controller.getOrder();
//        Map<MenuItem, Integer> hashMap = controller.getCart();
        this.cart = FXCollections.observableMap(controller.getCart());
        this.history = FXCollections.observableMap(controller.getOrderHistory());
        this.keys = new ArrayList<>(history.keySet());

    }


    public void onPreviousButtonClick() {
        viewNextButton.setDisable(false);
        handleOrderNumberSelection(keys.get(keys.indexOf(orderNumberSelector.getUserData()) - 1));
    }

    public void onNextButtonClick() {
        viewPreviousButton.setDisable(false);
        handleOrderNumberSelection(keys.get(keys.indexOf(orderNumberSelector.getUserData())  + 1));

    }

    public void onFirstButtonClick() {
        orderNumberSelector.setUserData(keys.getFirst());
        handleOrderNumberSelection(keys.getFirst());
    }

    public void onLastButtonClick() {
        orderNumberSelector.setUserData(keys.getLast());
        handleOrderNumberSelection(keys.getLast());

    }
}
