package eat.neilson.Cafe;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;

public class DonutViewController {

    private Donut donut = new Donut();
    public Button addOrder;
    public Button addButtonPreOrder;
    public Button deleteButtonPreOrder;
    @FXML
    public TextField donutSubtotalTextField;
    @FXML
    private GridPane donutGridPane;
    public ColumnConstraints donutTypeColumn;
    public ColumnConstraints donutFlavorColumn;
    private CafeViewController app;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;
    @FXML
    private ComboBox donutQuantity;
    private ObservableList<DonutFlavor> flavors = FXCollections.observableArrayList();
    private ObservableList<Donut> preOrdersList = FXCollections.observableArrayList();
    @FXML
    private ListView flavorListView;
    @FXML
    private ListView preOrders;
    public ToggleGroup donutTypeToggleGroup = new ToggleGroup();

    @FXML
    public void initialize() {
        addRadioToDonutTypeColumn();
        populateQuantityComboBox();

    }

    /**
     * Adds available donut types to the screen
     */
    private void addRadioToDonutTypeColumn() {

        int row = 1;
        for (DonutType donut : DonutType.values()) {
            RadioButton radioButton = new RadioButton(donut.toString());
            radioButton.setToggleGroup(donutTypeToggleGroup);
            radioButton.setUserData(donut);

            donutGridPane.add(radioButton, 0, row);

            if (donutGridPane.getRowConstraints().size() < row) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(35);
                rowConstraints.setPrefHeight(35);
                rowConstraints.setVgrow(Priority.SOMETIMES);
                donutGridPane.getRowConstraints().add(rowConstraints);
            }
            if (row == 1) {
                radioButton.setSelected(true);
            }
            row++;
        }
        donutGridPane.add(flavorListView, 1, 1);
        flavorListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        donutTypeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                donut.setType((DonutType) donutTypeToggleGroup.getSelectedToggle().getUserData());
                populateFlavors(donut);
            }
        });
    }

    /**
     * Populates the flavors Listview based on the type of donut selected.
     *
     * @param donut
     */
    private void populateFlavors(Donut donut) {
        flavorListView.setItems(flavors);
        flavors.clear();
        flavors.addAll(donut.getType().getFlavors());
        flavorListView.getSelectionModel().selectFirst();

    }

    /**
     * initializes the Quantity comboBox from numbers 1 thru 12.
     * Defaults to 1.
     */
    @SuppressWarnings("unchecked")
    private void populateQuantityComboBox() {
        for (int i = 1; i <= 12; i++) {
            donutQuantity.getItems().add(i);
        }
        donutQuantity.setValue(1);
    }


    /**
     * Adds Donut to the preOrder Listview & ObservableList.
     */
    @SuppressWarnings("unchecked")
    public void onAddButtonClick() {
        addButtonPreOrder.setOnAction(event -> {
            // Add a new donut to the preorder list
            donut.setFlavor((DonutFlavor) flavorListView.getSelectionModel().getSelectedItem());
            donut.setQuantity((int) donutQuantity.getValue());
            preOrdersList.add(donut);
            preOrders.setItems(preOrdersList);
            updateSubtotal("add", donut);

        });
    }

    /**
     * Initializes  all the listview to their cooresponding ObservableList
     */
    private void setListViews() {
        preOrders.setItems(preOrdersList);
        flavorListView.setItems(flavors);
    }

    /**
     * Removes selected Donut from the preOrder Listview & ObservableList.
     */
    public void onDeleteButtonClick() {
        deleteButtonPreOrder.setOnAction(event -> {
            Donut selectedItem = (Donut) preOrders.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                preOrdersList.remove(selectedItem);
                updateSubtotal("sub", selectedItem);
            }
        });
    }


    //Iterate through preOrder arrays and send to main cart, after clear the preOrder list
    public void onAddOrderButtonClick(ActionEvent actionEvent) {
    }


    /**
     * Updates the running subtotal.
     * Adds to subtotal if we are adding to preOrder Listview .
     * Subtracts if we are removing a donut from the preOrder Listview.
     *
     * @param operation
     * @param donut
     */
    private void updateSubtotal(String operation, Donut donut) {

        String subtotalText = donutSubtotalTextField.getText();
        double subtotal = subtotalText.isEmpty() ? 0 : Double.parseDouble(subtotalText.substring(1));

        switch (operation) {
            case "add":
                subtotal += donut.price() * donut.getQuantity();
                break;
            case "sub":
                subtotal -= donut.price() * donut.getQuantity();
                break;
            default:
                break;
        }

        String formattedSubtotal = String.format("%.2f", subtotal);
        donutSubtotalTextField.setText("$" + formattedSubtotal);


    }


    /**
     * Sets DonutViewController as the main screen
     *
     * @param controller
     * @param stage
     * @param primaryStage
     * @param primaryScene
     */
    public void setMainController(CafeViewController controller, Stage stage,
                                  Stage primaryStage, Scene primaryScene) {
        app = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

}
