package eat.neilson.Cafe;

public enum SandwichProtein {

    BEEF(10.99),
    CHICKEN(8.99),
    FISH(10.99);

    private double price;

    SandwichProtein(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
