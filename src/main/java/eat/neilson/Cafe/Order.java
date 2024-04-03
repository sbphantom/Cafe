package eat.neilson.Cafe;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing an order
 *
 * @author Danny Onuorah
 */
public class Order {
    private int orderNumber;
    private double subtotal = 0.0;
    private double total = 0.0;
    private HashMap<MenuItem, Integer> cart = new HashMap<>();

    public static double STATE_TAX = 6.625 / 100;

    /**
     * Order object constructor
     *
     * @param num order number to associate with order
     **/
    public Order(int num) {
        this.orderNumber = num;
    }

    /**
     * Get order number
     *
     * @return order number
     **/
    public Integer getOrderNumber() {
        return orderNumber;
    }

    /**
     * Get item cart
     *
     * @return cart
     **/
    public HashMap<MenuItem, Integer> getCart() {
        return cart;
    }

    /**
     * Return cart size
     *
     * @return number of items in cart
     **/
    public int cartSize() {
        return cart.size();
    }

    /**
     * Return the quantity of an item in the cart
     *
     * @param item to be checked
     * @return item quantity
     **/
    public int itemCount(MenuItem item) {
        return cart.getOrDefault(item, -1);
    }

    /**
     * Add an item to the order
     *
     * @param item     to add
     * @param quantity to add
     * @return true if successfully added
     **/
    public boolean addItem(MenuItem item, Integer quantity) {
        if (cart.containsKey(item)) {
            cart.put(item, cart.get(item) + quantity);
        } else {
            cart.put(item, quantity);
        }
        return true;
    }

    /**
     * Remove an item from the order
     *
     * @param item     to remove
     * @param quantity to remove
     * @return true if successfully remove
     **/
    public boolean removeItem(MenuItem item, Integer quantity) {
        if (cart.containsKey(item)) {
            if (quantity >= cart.get(item)) {
                cart.remove(item);
            } else {
                cart.put(item, cart.get(item) - quantity);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calculates and returns item subtotal
     *
     * @return order subtotal
     **/
    public double getSubtotal() {
        subtotal = 0;
        for (Map.Entry<MenuItem, Integer> entry : cart.entrySet()) {
            subtotal += (entry.getKey().price() * entry.getValue());
        }
        return subtotal;
    }

    /**
     * Calculates and returns tax incurred
     *
     * @return tax amount
     **/
    public double tax() {
        return (double) Math.round(subtotal * STATE_TAX * 100.0) / 100;
    }

    /**
     * Calculates and returns the final total
     *
     * @return order total
     **/
    public double getTotal() {
        return subtotal + tax();
    }
}