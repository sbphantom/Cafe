package eat.neilson.Cafe;
/**
 * Enum class for the available sandwich bread options.
 * @author Danny Onurah
 */
public enum SandwichBread {
    BAGEL,
    WHEAT,
    SOUR_DOUGH;

    /**
     *
     * @return formatted String the sandwich bread Enum object.
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
