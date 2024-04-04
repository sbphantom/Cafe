package eat.neilson.Cafe;

import java.util.ArrayList;

/**
 * Enum class of Donut Types.
 *
 * @author Danny Onuorah
 */
public enum DonutType {

    YEAST(1.79),
    CAKE(1.89),
    HOLE(0.39);

    public double price;

    DonutType(double price) {
        this.price = price;
    }

    /**
     * Return donut type string
     *
     * @return donut type
     */
    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }

    /**
     * Return arraylist of flavors associated with the donut type
     *
     * @return flavor arraylist
     */
    public ArrayList<DonutFlavor> getFlavors() {
        ArrayList<DonutFlavor> flavors = new ArrayList<>();
        for (DonutFlavor flavor : DonutFlavor.values()) {
            if (flavor.getType() == this) {
                flavors.add(flavor);
            }
        }
        return flavors;
    }
}
