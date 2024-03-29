package eat.neilson.Cafe;

import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;

import java.util.Map;

public class CartViewController {

    public Label subtotalText;
    public Label taxText;
    public Label totalText;
    @FXML
    private TableView<Map.Entry<MenuItem, Integer>> menuItemTable;

    private CafeViewController app;

    ObservableMap<MenuItem, Integer> items;

    public Order order;



    public void initialize() {


    }

    public void initializeTable(){
        TableColumn<Map.Entry<MenuItem, Integer>, String> itemColumn = getItemColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, String> addOnColumn = getAddOnColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, Void> quantityColumn = getQuantityColumn();
        TableColumn<Map.Entry<MenuItem, Integer>, Double> priceColumn = getPriceColumn();



        menuItemTable.getColumns().addAll(itemColumn, addOnColumn, quantityColumn, priceColumn);

        ObservableMap<MenuItem, Integer> observableMap = FXCollections.observableMap(order.getCart());
        menuItemTable.setItems(FXCollections.observableArrayList(observableMap.entrySet()));
        updateTotal();
    }

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
                });

                decrementButton.setOnAction(event -> {
                    Map.Entry<MenuItem, Integer> item = getTableView().getItems().get(getIndex());
                    if (item.getValue() > 1) {
                        item.setValue(item.getValue() - 1);
                        quantityLabel.setText(item.getValue().toString());
                        getTableView().refresh();
                        updateTotal();
                    }
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
                    HBox pane = new HBox(decrementButton, quantityLabel, incrementButton);
                    pane.setAlignment(Pos.CENTER);
                    pane.setSpacing(10);
                    setGraphic(pane);
                }
            }
        });

        return quantityColumn;
    }

    private TableColumn<Map.Entry<MenuItem, Integer>, Double> getPriceColumn() {
        TableColumn<Map.Entry<MenuItem, Integer>, Double> priceColumn = new TableColumn<>("Price");

        priceColumn.setCellValueFactory(data -> {
            double price = data.getValue().getKey().price() * data.getValue().getValue();
            return new SimpleDoubleProperty(price).asObject();
        });

        priceColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
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


    public void updateTotal(){
        subtotalText.setText("$" + order.getSubtotal());
        taxText.setText("$" + order.tax());
        totalText.setText("$" + order.getTotal());
    }

    public void setMainController(CafeViewController controller, Order order) {
        app = controller;
        this.order = order;
    }
}
