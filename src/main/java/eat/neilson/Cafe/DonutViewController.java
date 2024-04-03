package eat.neilson.Cafe;
/**
 * This class serves as the main controller for the donut ordering window.
 * Orders from this window are sent back to the main cart.
 *
 * @author Adeola Asimolowo
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class DonutViewController {

    public ImageView donutImage;
    private Donut donut = new Donut();
    public Button addOrder;
    @FXML
    public Button addButtonPreOrder;
    @FXML
    public Button deleteButtonPreOrder;
    @FXML
    public TextField donutSubtotalTextField;
    @FXML
    public GridPane donutGridPane;
    public GridPane DonutTypesGridPane;
    public ColumnConstraints donutTypeColumn;
    public ColumnConstraints donutFlavorColumn;
    private CafeViewController app;
    private Stage stage;
    @FXML
    public ComboBox donutQuantity;
    private ObservableList<DonutFlavor> flavors = FXCollections.observableArrayList();
    private ObservableList<Donut> preOrdersList = FXCollections.observableArrayList();
    @FXML
    public ListView flavorListView;
    @FXML
    public ListView preOrders;
    public ToggleGroup donutTypeToggleGroup = new ToggleGroup();

    @FXML
    public void initialize() {
        donutSubtotalTextField.setText("$0.00");
        OnAddButtonClick();
        onDeleteButtonClick();
        populateQuantityComboBox();
        addRadioToDonutTypeColumn();
        setListViews();

    }

    /**
     * Adds available donut types to the screen
     */
    private void addRadioToDonutTypeColumn() {
        flavorListView.getSelectionModel().selectFirst();

        int row = 0;
        for (DonutType donutRadioType : DonutType.values()) {
            RadioButton radioButton = new RadioButton(donutRadioType.toString());
            radioButton.setToggleGroup(donutTypeToggleGroup);
            radioButton.setUserData(donutRadioType);

            DonutTypesGridPane.add(radioButton, 0, row);

            if (DonutTypesGridPane.getRowConstraints().size() < row) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(35);
                rowConstraints.setPrefHeight(20);
                rowConstraints.setVgrow(Priority.SOMETIMES);
                DonutTypesGridPane.getRowConstraints().add(rowConstraints);
            }
            if (row == 0) {
                radioButton.setSelected(true);
            }
            row++;
        }
        flavorListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        populateFlavors((DonutType) donutTypeToggleGroup.getSelectedToggle().getUserData());

        donutTypeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                DonutType selectedType = (DonutType) newValue.getUserData();
                populateFlavors(selectedType);
                String imagePath = changeImage(selectedType);
                Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
                donutImage.setImage(image);
            }
        });
    }

    /**
     * Sets new imagePath for the currentlky selcted donutType by user
     * @param selectedType Donut Type
     * @return new image path
     */
    public String changeImage(DonutType selectedType) {
        String imagePath = "";
        switch (selectedType) {
            case DonutType.YEAST:
                imagePath = "yeast.jpg";
                break;
            case DonutType.HOLE:
                imagePath = "holes.jpg";
                break;
            case DonutType.CAKE:
                imagePath = "cake.jpg";
                break;
            default:
                break;
        }
        return imagePath;
    }

    /**
     * Populates the flavors Listview based on the type of donut selected.
     *
     * @param donut
     */
    private void populateFlavors(DonutType donut) {
        flavorListView.setItems(flavors);
        flavors.clear();
        flavors.addAll(donut.getFlavors());
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
    public void OnAddButtonClick() {
        addButtonPreOrder.setOnAction(event -> {
            // Add a new donut to the preorder list

            Donut donut = new Donut();
            donut.setType((DonutType) donutTypeToggleGroup.getSelectedToggle().getUserData());
            donut.setFlavor((DonutFlavor) flavorListView.getSelectionModel().getSelectedItem());
            donut.setQuantity((int) donutQuantity.getValue());
            preOrdersList.add(donut);
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

    /**
     * Sends the order of donuts to the main cart.
     *
     */
    //Iterate through preOrder arrays and send to main cart, after clear the preOrder list
    public void OnAddOrderButtonClick() {
        for (Donut donut : preOrdersList) {
            app.addItemToOrder(donut, donut.getQuantity());
            updateSubtotal("sub", donut);
        }

        preOrdersList.clear();
        donutQuantity.getSelectionModel().selectFirst();
        donutTypeToggleGroup.selectToggle(donutTypeToggleGroup.getToggles().getFirst());
    }

    /**
     * Updates the running subtotal.
     * Adds to subtotal if we are adding to preOrder Listview .
     * Subtracts if we are removing a donut from the preOrder Listview.
     *
     * @param operation add or subtract from current subtotal
     * @param donut pass the donut currently being selected.
     */
    private void updateSubtotal(String operation, Donut donut) {

        String subtotalText = donutSubtotalTextField.getText();
        double subtotal = subtotalText.isEmpty() ? 0 : Double.parseDouble(subtotalText.substring(1));

        switch (operation) {
            case "add":
                subtotal += donut.price(); //* donut.getQuantity();
                break;
            case "sub":
                subtotal -= donut.price(); //* donut.getQuantity();
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
     */
    public void setMainController(CafeViewController controller) {
        app = controller;
    }

}
