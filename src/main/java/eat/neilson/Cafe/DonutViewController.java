package eat.neilson.Cafe;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class DonutViewController {

    private Donut donut;

    public Button addOrder;
    public Button add;
    public Button delete;


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
    private ComboBox quantity;

    private ObservableList<String> flavors = FXCollections.observableArrayList();

    private ListView flavorListView;
   public  ToggleGroup typeToggleGroup = new ToggleGroup();
    @FXML
    public void initialize() {

    }

    private void addRadioToDonutTypeColumn() {

        int row = 1;
        for (DonutType donut : DonutType.values()) {
            RadioButton radioButton = new RadioButton(donut.toString());
            radioButton.setToggleGroup(typeToggleGroup);
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
        typeToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->{
            if(newValue !=null ){
                donut.setType((DonutType) typeToggleGroup.getSelectedToggle().getUserData());
                populateFlavors(donut);
                updateSubtotal();
            }
        });
    }

    private void populateFlavors(Donut donut) {
        flavors.clear();
        flavors.addAll(donut.getType().getFlavors());
        flavorListView.setItems(flavors);

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



    private void updateSubtotal(){
        double subtotal = donut.price() ;
        String formattedSubtotal = String.format("%.2f", subtotal);
        donutSubtotalTextField.setText("$" + formattedSubtotal);
    }

}
