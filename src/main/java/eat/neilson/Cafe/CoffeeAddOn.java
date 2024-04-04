package eat.neilson.Cafe;

/**
 * Enum class of Coffee AddOns.
 *
 * @author Danny Onuorah
 */
public enum CoffeeAddOn {
    SWEET_CREAM,
    FRENCH_VANILLA,
    IRISH_CREAM,
    CARAMEL,
    MOCHA;

    public double price;

    CoffeeAddOn() {
        this.price = 0.30;
    }

    /**
     * Return addOn string
     *
     * @return string of addOn
     */
    @Override
    public String toString() {
        String[] words = this.name().split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i != 0) sb.append(" ");
            sb.append(words[i].charAt(0)).append(words[i].substring(1).toLowerCase());
        }

        return sb.toString();
    }

}
