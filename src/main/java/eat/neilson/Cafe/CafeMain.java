package eat.neilson.Cafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class CafeMain extends Application {

    ArrayList<Order> orderHistory = new ArrayList<>();

    Order currentOrder = null;


    public boolean createOrder() {
        if (currentOrder == null) {
            currentOrder = new Order();
            return true;
        }
        return false;
    }


    public boolean addOrder() {
        if (currentOrder != null) {
            orderHistory.add(currentOrder);
            currentOrder = null;
            return true;
        }
        return false;
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeMain.class.getResource("cafe-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        CafeViewController cafeController = fxmlLoader.getController();
        cafeController.setPrimaryStage(stage, scene);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}