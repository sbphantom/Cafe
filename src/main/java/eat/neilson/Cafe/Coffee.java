package eat.neilson.Cafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Subclass of MenuItem for Coffee
 *
 * @author Danny Onurah
 */
public class Coffee extends MenuItem {
    private CoffeeSize size;
    private ArrayList<CoffeeAddOn> addOns;

    /**
     * Coffee constructor with CoffeeSize and ArrayList<CoffeeAddOn>
     */
    public Coffee(CoffeeSize size, ArrayList<CoffeeAddOn> addOns) {
        this.size = size;
        this.addOns = addOns;
    }

    /**
     * Sets the size of the coffee
     */
    public void setCoffeeSize(CoffeeSize size) {
        this.size = size;
    }

    /**
     * Adds a coffee addOn
     */
    public void addAddOn(CoffeeAddOn addOn) {
        addOns.add(addOn);
    }

    /**
     * Removes a coffee addOn
     */
    public void removeAddOn(CoffeeAddOn addOn) {
        addOns.remove(addOn);
    }

    /**
     * Returns the item name
     *
     * @return item name
     */
    @Override
    public String name() {
        return size + " Coffee";
    }

    /**
     * Returns formatted addOn string
     *
     * @return addOn string
     */
    @Override
    public String addOnString() {
        return addOns.toString().substring(1, addOns.toString().length() - 1);
    }

    /**
     * Calculates and returns the coffee subtotal
     *
     * @return price
     */
    @Override
    public double price() {
        double price = size.price;
        for (CoffeeAddOn addOn : addOns) {
            price += addOn.price;
        }
        return Math.round(price * 100.0) / 100.0;
    }

    /**
     * Compares two coffee by size then price
     *
     * @param item against coffee to compare
     * @return > 0 if source is less
     * = 0 if source is equal
     * < 0 if source is greater
     */
    @Override
    public int compareTo(MenuItem item) {
        if (item instanceof Coffee coffee) {
            if (this.size.compareTo(coffee.size) != 0) {
                return this.size.compareTo(coffee.size);
            }
            if (this.price() - coffee.price() != 0) {
                return (int) (this.price() - coffee.price());
            } else {
                return 0;
            }
        } else return this.getClass().getSimpleName().compareTo(item.getClass().getSimpleName());
    }

    /**
     * Returns whether a coffee is equal
     *
     * @param o object
     * @return true if object is equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return size == coffee.size &&
                Objects.equals(addOns, coffee.addOns);
    }

    /**
     * Calculates based on coffee fields
     *
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        Collections.sort(addOns);
        return Objects.hash(getClass(), size, addOns);
    }

    /**
     * Returns formatted coffee string
     *
     * @return formatted coffee string
     */
    @Override
    public String toString() {
        return size + " Coffee with (" + addOns + ")";
    }

}
