package eat.neilson.Cafe;

import java.util.ArrayList;
import java.util.List;

public enum DonutType {

    YEAST(1.79),
    CAKE (1.89),
    HOLE (0.39);

    public double price;

    DonutType(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
    public List<DonutFlavor> getFlavors() {
        List<DonutFlavor> flavors = new ArrayList<>();
        for (DonutFlavor flavor : DonutFlavor.values()) {
            if (flavor.getType() == this) {
                flavors.add(flavor);
            }
        }
        return flavors;
    }
}
