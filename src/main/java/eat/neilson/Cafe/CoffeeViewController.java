package eat.neilson.Cafe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class CoffeeViewController {

    @FXML
    public ImageView coffeeImage;
    @FXML
    public Spinner<Integer> coffeeQuanitySpinner;
    public TextField coffeeSubtotalTextField;
    public Button addOrderButton;
    public Button cancelOrderButton;
    @FXML
    private GridPane coffeeGridPane;
    @FXML

    public ColumnConstraints coffeeSizeColumn;
    @FXML

    public ColumnConstraints coffeeAddOnColumn;
    ToggleGroup coffeeSizeToggleGroup = new ToggleGroup();

    private CafeViewController app;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    private Coffee coffee = new Coffee();

    public void initialize() {
        addRadioButtonsToCoffeeSizeColumn();
        addCheckboxesToCoffeeAddOnColumn();
        coffeeQuanitySpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5));
        coffeeQuanitySpinner.getValueFactory().setValue(1);
        coffeeQuanitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            updateSubtotal();
        });

        coffee.setCoffeeSize((CoffeeSize) coffeeSizeToggleGroup.getSelectedToggle().getUserData());
        updateSubtotal();
    }


    private void updateSubtotal(){
        double subtotal = coffee.price() * coffeeQuanitySpinner.getValue();
        String formattedSubtotal = String.format("%.2f", subtotal);
        coffeeSubtotalTextField.setText("$" + formattedSubtotal);
    }
    private void addRadioButtonsToCoffeeSizeColumn() {
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
            if (newValue != null) {
                coffee.setCoffeeSize((CoffeeSize) coffeeSizeToggleGroup.getSelectedToggle().getUserData());
                updateSubtotal();
            }
        });
    }

    private void addCheckboxesToCoffeeAddOnColumn() {
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

    public void setMainController(CafeViewController controller,
                                  Stage stage,
                                  Stage primaryStage,
                                  Scene primaryScene) {
        app = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;
    }

    public void onAddOrderButtonClick(ActionEvent actionEvent) {
    }

    public void onCancelOrderButtonClick(ActionEvent actionEvent) {
    }
}
