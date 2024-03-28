package eat.neilson.Cafe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int orderNumber;
    private double subtotal = 0.0;

    private double total = 0.0;

    private ArrayList<MenuItem> items = new ArrayList<>();
    private HashMap<MenuItem, Integer> cart = new HashMap<>();

    public static double STATE_TAX = 6.625 / 100;



    public HashMap<MenuItem, Integer> getCart(){
        return cart;
    }

    public int cartSize(){
        return cart.size();
    }

    public int itemCount(MenuItem item){
        return cart.getOrDefault(item, -1);
    }

    public boolean addItem(MenuItem item, Integer quantity) {
        if (cart.containsKey(item)){
            cart.put(item, cart.get(item) + quantity);
        }
        else{
            cart.put(item, quantity);
        }
        return true;
    }

    public boolean removeItem(MenuItem item, Integer quantity) {
        if (cart.containsKey(item)){
            if (quantity >= cart.get(item)) {
                cart.remove(item);
            }
            else{
                cart.put(item, cart.get(item) - quantity);
            }
            return true;
        }
        else{
            return false;
        }
    }

    public double getSubtotal(){
        subtotal = 0;
        for (Map.Entry<MenuItem, Integer> entry : cart.entrySet()){
            subtotal += (entry.getKey().price() * entry.getValue());
        }
        return subtotal;
    }

    public double tax(){
        return subtotal * STATE_TAX;
    }

    public double getTotal(){
        return subtotal + tax();
    }

    public static void main(String[] args) {

        Order order = new Order();
        Coffee a = new Coffee();
        a.setCoffeeSize(CoffeeSize.GRANDE);
        a.addAddOn(CoffeeAddOn.CARAMEL);
        a.addAddOn(CoffeeAddOn.FRENCH_VANILLA);

        Coffee b = new Coffee();
        b.setCoffeeSize(CoffeeSize.GRANDE);
        b.addAddOn(CoffeeAddOn.FRENCH_VANILLA);
        b.addAddOn(CoffeeAddOn.CARAMEL);

        //order.cart.put(a, 1);

        System.out.println(order.cart.containsKey(a));
        System.out.println(order.cart.containsKey(b));

        order.addItem(a, 3);
        order.addItem(b, 5);
        order.removeItem(b, 2);
        System.out.println(order.itemCount(b));


    }


}
