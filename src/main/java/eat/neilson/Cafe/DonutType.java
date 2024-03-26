package eat.neilson.Cafe;

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

}
