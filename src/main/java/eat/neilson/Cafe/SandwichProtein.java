package eat.neilson.Cafe;

/**
 * Enum class for the available sandwich protein options.
 * @author Danny Onurah
 */
public enum SandwichProtein {

    BEEF(10.99),
    CHICKEN(8.99),
    FISH(9.99);

    public double price;

    SandwichProtein(double price){
        this.price = price;
    }
    /**
     * toString method for the sandwich addon
     * @return formatted string of a sandwich protein object
     */
    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
