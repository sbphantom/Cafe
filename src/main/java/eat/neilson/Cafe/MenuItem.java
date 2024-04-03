package eat.neilson.Cafe;

/**
 * Abstract class representing a menu item
 *
 * @author Danny Onuorah
 */
public abstract class MenuItem {


    /**
     * Returns the item name
     *
     * @return item name
     */
    public abstract String name();

    /**
     * Returns formatted addOn string
     *
     * @return addOn string
     */
    public abstract String addOnString();

    /**
     * Calculates and returns the coffee subtotal
     *
     * @return price
     */
    public abstract double price();

    /**
     * Compares two menu item
     *
     * @param item to compare against
     * @return > 0 if source is less
     * = 0 if source is equal
     * < 0 if source is greater
     */
    public abstract int compareTo(MenuItem item);

    /**
     * Returns whether a menu item is equal
     *
     * @param obj object
     * @return true if object is equal
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Generate hashcode of item
     *
     * @return hashcode
     */
    @Override
    public abstract int hashCode();

    /**
     * Returns formatted coffee string
     *
     * @return formatted coffee string
     */
    @Override
    public abstract String toString();
}