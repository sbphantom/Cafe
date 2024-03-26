package eat.neilson.Cafe;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


public class CoffeeViewController {
    @FXML
    private GridPane coffeeGridPane;
    @FXML

    public ColumnConstraints coffeeSizeColumn;
    @FXML

    public ColumnConstraints coffeeAddOnColumn;
    private CafeViewController app;
    private Stage stage;
    private Scene primaryScene;
    private Stage primaryStage;

    public void initialize() {
        addRadioButtonsToCoffeeSizeColumn();
        addCheckboxesToCoffeeAddOnColumn();
    }

    private void addRadioButtonsToCoffeeSizeColumn() {
        ToggleGroup coffeeSizeToggleGroup = new ToggleGroup();
        int row = 1;
        for (CoffeeSize size : CoffeeSize.values()) {
            RadioButton radioButton = new RadioButton(size.toString());
            radioButton.setToggleGroup(coffeeSizeToggleGroup);
            coffeeGridPane.add(radioButton, 0, row);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(50); // Set minimum height
            rowConstraints.setPrefHeight(50); // Set preferred height
            rowConstraints.setVgrow(Priority.SOMETIMES); // Allow the row to grow
            coffeeGridPane.getRowConstraints().add(rowConstraints); // Add the constraints to the GridPane

            row++;
        }
    }

    private void addCheckboxesToCoffeeAddOnColumn() {
        int row = 1;
        for (CoffeeAddOn addOn : CoffeeAddOn.values()) {
            CheckBox checkBox = new CheckBox(addOn.toString());
            coffeeGridPane.add(checkBox, 1, row);

            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setMinHeight(50); // Set minimum height
            rowConstraints.setPrefHeight(50); // Set preferred height
            rowConstraints.setVgrow(Priority.SOMETIMES); // Allow the row to grow
            coffeeGridPane.getRowConstraints().add(rowConstraints); // Add the constraints to the GridPane

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

}
