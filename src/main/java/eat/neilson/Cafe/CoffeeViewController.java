package eat.neilson.Cafe;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;


public class CoffeeViewController {

    @FXML
    public ImageView coffeeImage;
    @FXML
    public Spinner<Integer> coffeeQuantitySpinner;
    public TextField coffeeSubtotalTextField;
    public Button addOrderButton;
    public Button cancelOrderButton;
    @FXML
    private GridPane coffeeGridPane;
    @FXML

    public ColumnConstraints coffeeSizeColumn;
    @FXML

    public ColumnConstraints coffeeAddOnColumn;

    public ArrayList<CheckBox> coffeeAddOnOptions = new ArrayList<>();

    ToggleGroup coffeeSizeToggleGroup = new ToggleGroup();

    private CafeViewController app;
//    private Stage stage;
//    private Scene primaryScene;
//    private Stage primaryStage;

    private Coffee coffee;

    public void initialize() {
        addCoffeeSizeButtons();
        addCoffeeAddOnBoxes();
        coffeeQuantitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5));
        coffeeQuantitySpinner.getValueFactory().setValue(1);
        coffeeQuantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (coffee != null) updateSubtotal();
        });

//        coffee.setCoffeeSize((CoffeeSize) coffeeSizeToggleGroup.getSelectedToggle().getUserData());
        coffee = new Coffee((CoffeeSize) coffeeSizeToggleGroup.getSelectedToggle().getUserData(), new ArrayList<>());
        updateSubtotal();
    }


    private void updateSubtotal(){
        double subtotal = coffee.price() * coffeeQuantitySpinner.getValue();
        String formattedSubtotal = String.format("%.2f", subtotal);
        coffeeSubtotalTextField.setText("$" + formattedSubtotal);
    }
    private void addCoffeeSizeButtons() {
        int row = 1;
        for (CoffeeSize size : CoffeeSize.values()) {
            RadioButton radioButton = new RadioButton(size.toString());
            radioButton.setToggleGroup(coffeeSizeToggleGroup);
            radioButton.setUserData(size);

            coffeeGridPane.add(radioButton, 0, row);

            if (coffeeGridPane.getRowConstraints().size() < row) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(35);
                rowConstraints.setPrefHeight(35);
                rowConstraints.setVgrow(Priority.SOMETIMES);
                coffeeGridPane.getRowConstraints().add(rowConstraints);
            }

            if (row == 1) {
                radioButton.setSelected(true);
            }

            row++;
        }

        coffeeSizeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && coffee != null) {
                coffee.setCoffeeSize((CoffeeSize) coffeeSizeToggleGroup.getSelectedToggle().getUserData());
                updateSubtotal();
            }
        });
    }

    private void addCoffeeAddOnBoxes() {
        int row = 1;
        for (CoffeeAddOn addOn : CoffeeAddOn.values()) {
            CheckBox checkBox = new CheckBox(addOn.toString());
            checkBox.setUserData(addOn);
            checkBox.setOnAction(event -> {
                if (checkBox.isSelected()) {
                    coffee.addAddOn((CoffeeAddOn) checkBox.getUserData());
                } else {
                    coffee.removeAddOn((CoffeeAddOn) checkBox.getUserData());
                }
                updateSubtotal();
            });
            coffeeAddOnOptions.add(checkBox);
            coffeeGridPane.add(checkBox, 1, row);

            if (coffeeGridPane.getRowConstraints().size() <= row) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(35);
                rowConstraints.setPrefHeight(35);
                rowConstraints.setVgrow(Priority.SOMETIMES);
                coffeeGridPane.getRowConstraints().add(rowConstraints);
            }
            row++;
        }
    }

    public void setMainController(CafeViewController controller) {
        app = controller;
    }

//    public void setMainController(CafeViewController controller,
//                                  Stage stage,
//                                  Stage primaryStage,
//                                  Scene primaryScene) {
//        app = controller;
//        this.stage = stage;
//        this.primaryStage = primaryStage;
//        this.primaryScene = primaryScene;
//    }

    public void onAddOrderButtonClick() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Add to Order");
        alert.setContentText("Add " + coffeeQuantitySpinner.getValue()+ " coffee to order?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            app.addItemToOrder(coffee, coffeeQuantitySpinner.getValue());

            coffee = null;

            coffeeQuantitySpinner.getValueFactory().setValue(1);
            coffeeSizeToggleGroup.selectToggle(coffeeSizeToggleGroup.getToggles().getFirst());
            for (CheckBox box : coffeeAddOnOptions){
                box.setSelected(false);
            }

            coffee = new Coffee((CoffeeSize) coffeeSizeToggleGroup.getSelectedToggle().getUserData(), new ArrayList<>());
            updateSubtotal();
        }
    }

    public void onCancelOrderButtonClick() {
    }
}
