package eat.neilson.Cafe;
/**
 * Enum class for the available sandwich addOn options.
 * @author Danny Onurah
 */
public enum SandwichAddOn {

    LETTUCE(0.30),
    TOMATOES(0.30),
    ONIONS(0.30),
    CHEESE(1.00);

    public double price;
    SandwichAddOn(double price){
        this.price = price;
    }

    /**
     * toString method for the sandwich addon
     * @return formatted string of an addOn object
     */
    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }


}
