package eat.neilson.Cafe;

/**
 * Enum class for the available sandwich protein options.
 *
 * @author Danny Onuorah
 */
public enum SandwichProtein {

    BEEF(10.99),
    CHICKEN(8.99),
    FISH(9.99);

    public double price;

    SandwichProtein(double price) {
        this.price = price;
    }

    /**
     * Return protein string
     *
     * @return protein string
     */
    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
