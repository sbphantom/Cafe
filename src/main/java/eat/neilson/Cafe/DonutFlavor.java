package eat.neilson.Cafe;

/**
 * Enum class of Donut Flavors.
 * @author Danny Onurah
 */
public enum DonutFlavor {
    MAPLE(DonutType.YEAST),
    SUGAR(DonutType.YEAST),
    BLUEBERRY(DonutType.YEAST),
    JELLY(DonutType.YEAST),
    CRULLER(DonutType.YEAST),
    COFFEE(DonutType.YEAST),


    VANILLA(DonutType.CAKE),
    CHOCOLATE(DonutType.CAKE),
    STRAWBERRY(DonutType.CAKE),

    PLAIN(DonutType.HOLE),
    POWDER(DonutType.HOLE),
    GLAZED(DonutType.HOLE);

    private DonutType type;

    DonutFlavor(DonutType type){
        this.type = type;
    }

    public DonutType getType() {
        return type;
    }

    /**
     * @return formatted string for a Donut Flavor enum.
     */
    @Override
    public String toString() {
        String[] words = this.name().split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++){
            if (i != 0) sb.append(" ");
            sb.append(words[i].charAt(0)).append(words[i].substring(1).toLowerCase());
        }

        return sb.toString();
    }



}
