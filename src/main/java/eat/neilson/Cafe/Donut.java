package eat.neilson.Cafe;

import java.util.Objects;

/**
 * Subclass of MenuItem for Donuts
 *
 * @author Danny Onurah
 */
public class Donut extends MenuItem {
    private DonutType type;
    private DonutFlavor flavor;
    private int quantity;

    /**
     * Default donut constructor
     */
    public Donut() {
    }

    /**
     * Donut constructor with type and flavor
     */
    public Donut(DonutType type, DonutFlavor flavor) {
        this.type = type;
        this.flavor = flavor;
    }

    /**
     * Get donut type
     *
     * @return donut type
     */
    public DonutType getType() {
        return this.type;
    }

    /**
     * Get donut quantity.
     *
     * @return donut quantity
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns the item name
     *
     * @return item name
     */
    @Override
    public String name() {
        return flavor + " " + type + " Donut";
    }

    /**
     * Returns empty addOn string
     *
     * @return empty string
     */
    @Override
    public String addOnString() {
        return "";
    }

    /**
     * Calculates price of donut object.
     *
     * @return price of donut.
     */
    @Override
    public double price() {
        return type.price * this.quantity;
    }

    /**
     * Setter method for donut Type.
     *
     * @param type new DonutType to set.
     */
    public void setType(DonutType type) {
        this.type = type;
    }

    /**
     * Setter method for donut Flavor.
     *
     * @param flavor new flavor to set.
     */
    public void setFlavor(DonutFlavor flavor) {
        this.flavor = flavor;
    }

    /**
     * Setter method for donut Quantity.
     *
     * @param quantity new quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Compares two Donut by type then flavor
     *
     * @param item to compare against
     * @return > 0 if source is less
     * = 0 if source is equal
     * < 0 if source is greater
     */
    @Override
    public int compareTo(MenuItem item) {
        if (item instanceof Donut donut) {
            if (this.type.compareTo(donut.type) != 0) {
                return this.type.compareTo(donut.type);
            } else {
                return (this.flavor.compareTo(donut.flavor));
            }
        } else return this.getClass().getSimpleName().compareTo(item.getClass().getSimpleName());
    }

    /**
     * Equals method to compare to if two donuts are the same.
     *
     * @param o comparison donut.
     * @return boolean. True if equal. False if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donut donut = (Donut) o;
        return type == donut.type && flavor == donut.flavor;
    }

    /**
     * Calculates based on donut fields
     *
     * @return donut hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, flavor);
    }

    /**
     * toString method for donut .
     *
     * @return a formatted String for the donut object.
     */
    @Override
    public String toString() {
        return flavor + " " + type + " (" + quantity + ")";
    }
}