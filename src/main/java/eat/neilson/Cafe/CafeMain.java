package eat.neilson.Cafe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class CafeMain extends Application {
    ArrayList<Order> orderHistory = new ArrayList<>();
    Order currentOrder = new Order();
    public boolean createOrder() {
        if (currentOrder == null) {
            currentOrder = new Order();
            return true;
        }
        return false;
    }


    public boolean addOrder() {
        if (currentOrder.cartSize() > 0) {
            orderHistory.add(currentOrder);
            currentOrder = new Order();
            return true;
        }
        return false;
    }

    public boolean addItem(MenuItem item, int quantity) {
        currentOrder.addItem(item, quantity);

        for (Map.Entry<MenuItem, Integer> entry : currentOrder.getCart().entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("-----");

        return true;
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CafeMain.class.getResource("cafe-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        CafeViewController cafeController = fxmlLoader.getController();
        cafeController.setPrimaryStage(stage);
        stage.setTitle("Neilson Cafe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}