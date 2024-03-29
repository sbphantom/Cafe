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

    private Donut donut;

    public Button addOrder;
    public Button add;
    public Button delete;

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

    private ObservableList<String> flavors = FXCollections.observableArrayList();

    private ObservableList<String> preOrdersList = FXCollections.observableArrayList();

    private ListView flavorListView;


    private ListView preOrders;
   public  ToggleGroup donutTypeToggleGroup = new ToggleGroup();
    @FXML
    public void initialize() {
        addRadioToDonutTypeColumn();
        populateQuantityComboBox();

    }

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

        donutTypeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue !=null ){
                donut.setType((DonutType) donutTypeToggleGroup.getSelectedToggle().getUserData());
                populateFlavors(donut);
               // updateSubtotal();
            }
        });
    }

    private void populateFlavors(Donut donut) {
        flavors.clear();
        flavors.addAll(donut.getType().getFlavors());
        flavorListView.setItems(flavors);

    }

    @SuppressWarnings("unchecked")
    private void populateQuantityComboBox(){
        for (int i = 1; i <= 12; i++) {
            donutQuantity.getItems().add(i);
        }
        donutQuantity.setValue(1);
    }



        //if this donut type selected display this
    
    /**
     *Sets DonutViewController as the main screen
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

    @SuppressWarnings("unchecked")
    public void onAddButtonClick(){

        Object[] tuple = new Object[2];
        Donut  d = new Donut();
        d.setType((DonutType) donutTypeToggleGroup.getSelectedToggle().getUserData());

        String donutFlavor = (String) flavorListView.getSelectionModel().getSelectedItem();
        d.setFlavor(DonutFlavor.getDonutFlavor(donutFlavor));

        tuple[0] = d;
        tuple[1] = 42; //Amount that they want turn it into a string to make it easy abeg


        String finalPreOrder= String.format(d.toString() + "(%s)",donutQuantity.getValue().toString());
        preOrdersList.add(finalPreOrder);
        preOrders.setItems(preOrdersList);

        updateSubtotal("add", (Integer) donutQuantity.getValue());
    }


    //Iterate through preOrder arrays and send to main cart
    public void onAddOrderButtonClick(ActionEvent actionEvent){}
    private void updateSubtotal(String operation, int quantity){

        switch (operation){
            case "add":

                String subtotalText = donutSubtotalTextField.getText();

                // Parse the subtotal if it's not empty, otherwise initialize it to 0
                double subtotal = subtotalText.isEmpty() ? 0 : Double.parseDouble(subtotalText.substring(1));

                subtotal += donut.price() * quantity;
                String formattedSubtotal = String.format("%.2f", subtotal);
                donutSubtotalTextField.setText("$" + formattedSubtotal);
                break;
            case "sub":
                break;
            default:
        }


    }

}
