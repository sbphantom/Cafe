package eat.neilson.Cafe;

import java.util.ArrayList;

public class Order {
    private int orderNumber;
    private double subtotal;

    private double total;

    public static double STATE_TAX = 6.625 / 100;

    private ArrayList<MenuItem> items = new ArrayList<>();


    public boolean addItem(MenuItem item) {
        items.add(item);
        return true;
    }


    public static void main(String[] args) {


        Order order = new Order();


    }


}
