package eat.neilson.Cafe;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class SandwichViewController {

    private Sandwich sandwich = new Sandwich();
    public ImageView sandwichImage;

    public TextField sandwichSubtotalTextField;

    public Button addOrderButton;

    public Button CancelOrderButton;

    private GridPane sandwichGridPane;

    public ColumnConstraints sandwichBreadColumn;

    public ColumnConstraints sandwichProteinColumn;

    public ColumnConstraints sandwichAddOnColumn;

    private CafeViewController app;

    private Stage stage;

    private Scene primaryScene;

    private Stage primaryStage;

    public
    ToggleGroup breadToggleGroup = new ToggleGroup();

    ToggleGroup proteinToggleGroup = new ToggleGroup();


    @FXML
    public void initialize() {
        addRadioButtonsToBreadColumn();
        addRadioButtonsToProteinColumn();

        sandwich.setBread((SandwichBread) breadToggleGroup.getSelectedToggle().getUserData());
        sandwich.setProtein((SandwichProtein) proteinToggleGroup.getSelectedToggle().getUserData());
        updateSubtotal();

    }

    /**
     * Adds radio buttons of bread options to gridpane column.
     */
    private void addRadioButtonsToBreadColumn(){
        int row = 1;
        for (SandwichBread bread: SandwichBread.values()){
            RadioButton radioButton = new RadioButton(bread.toString());
            radioButton.setToggleGroup(breadToggleGroup);
            radioButton.setUserData(bread);

            sandwichGridPane.add(radioButton, 0, row);

            if(sandwichGridPane.getRowConstraints().size() < row){
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(35);
                rowConstraints.setPrefHeight(35);
                rowConstraints.setVgrow(Priority.SOMETIMES);
                sandwichGridPane.getRowConstraints().add(rowConstraints);
            }

            if(row ==1){
                radioButton.setSelected(true);
            }

            row++;

            breadToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->{
                if(newValue !=null ){
                    sandwich.setBread((SandwichBread) breadToggleGroup.getSelectedToggle().getUserData());
                    updateSubtotal();
                }
            });
        }
    }

    /**
     * Adds radio buttons of protein options to gridpane column.
     */
    private void addRadioButtonsToProteinColumn(){
        int row = 1;
        for (SandwichProtein protein: SandwichProtein.values()){
            RadioButton radioButton = new RadioButton(protein.toString());
            radioButton.setToggleGroup(proteinToggleGroup);
            radioButton.setUserData(protein);

            sandwichGridPane.add(radioButton, 0, row);

            if(sandwichGridPane.getRowConstraints().size() < row){
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(35);
                rowConstraints.setPrefHeight(35);
                rowConstraints.setVgrow(Priority.SOMETIMES);
                sandwichGridPane.getRowConstraints().add(rowConstraints);
            }

            if(row == 1){

                radioButton.setSelected(true);
            }

            row++;

        proteinToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue !=null){
                sandwich.setProtein((SandwichProtein) proteinToggleGroup.getSelectedToggle().getUserData());
                updateSubtotal();
            }
        });
        }
    }

    /**
     * adds checkboxes of sandwich add-ons to gridpane column.
     */
    private void  addCheckboxesToAddOnColumn(){
        int row = 1;
        for (SandwichAddOn addOn: SandwichAddOn.values()){
            CheckBox checkBox = new CheckBox((addOn.toString()));
            checkBox.setUserData(addOn);
            checkBox.setOnAction(event ->{
                if (checkBox.isSelected()){
                    sandwich.addAddOn((SandwichAddOn) checkBox.getUserData());
                }
                else {
                    sandwich.removeAddOn((SandwichAddOn) checkBox.getUserData());
                }
                updateSubtotal();
            } );

            sandwichGridPane.add(checkBox, 2, row);
            if (sandwichGridPane.getRowConstraints().size() <= row){
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(35);
                rowConstraints.setPrefHeight(35);
                rowConstraints.setVgrow(Priority.SOMETIMES);
                sandwichGridPane.getRowConstraints().add(rowConstraints);
            }
            row++;
        }
    }

    /**
     *
     * @param controller
     * @param stage
     * @param primaryStage
     * @param primaryScene
     */
    public void setMainController(CafeViewController controller, Stage stage,
                                  Stage primaryStage, Scene primaryScene){

        app = controller;
        this.stage = stage;
        this.primaryStage = primaryStage;
        this.primaryScene = primaryScene;

    }

    /**
     * updates the subtotal of the sandwich order.
     */
    private void updateSubtotal(){
        double subtotal = sandwich.price();
        String formattedSubtotal = String.format("%.2f", subtotal);
        sandwichSubtotalTextField.setText("$" + formattedSubtotal);
    }


    /**
     * Adds sandwich order to main cart.
     * @param actionEvent
     */
    public void onAddOrderButtonClick(ActionEvent actionEvent) {
    }
    /**
     * Resets the entire order
     * @param actionEvent
     */
    public void onCancelOrderButtonClick(ActionEvent actionEvent) {
    }
}
