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

    public Button addOrder; 

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
    private ComboBox donutTypeComboBox;

    private ObservableList<String> donutTypeList = FXCollections.observableArrayList();
   public  ToggleGroup typeToggleGroup = new ToggleGroup();
    @FXML
    public void initialize() {

    }

    private void addRadioToDonutTypeColumn(){

        int row = 1;
        for (DonutType bread: DonutType.values()){
            RadioButton radioButton = new RadioButton(bread.toString());
            radioButton.setToggleGroup(typeToggleGroup);
            radioButton.setUserData(bread);

            donutGridPane.add(radioButton, 0, row);

            if(donutGridPane.getRowConstraints().size() < row){
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setMinHeight(35);
                rowConstraints.setPrefHeight(35);
                rowConstraints.setVgrow(Priority.SOMETIMES);
                donutGridPane.getRowConstraints().add(rowConstraints);
            }
            if(row ==1){
                radioButton.setSelected(true);
            }

            row++;
    }





    //if this donuc ttype selected display this     
    
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

}
