package eat.neilson.Cafe;

public enum SandwichBread {
    BAGEL,
    WHEAT,
    SOUR_DOUGH;

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
