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
    ToggleGroup typeToggleGroup = new ToggleGroup(); 
    @FXML
    public void initialize() {
        addRadioToDonutTypeColumn();
    }

    private void addRadioToDonutTypeColumn(){

        for(DonutType donut: DonutType.values()) {
            donutTypeList.add(donut.toString());
        }
        donutTypeComboBox.setItems(donutTypeList);
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
