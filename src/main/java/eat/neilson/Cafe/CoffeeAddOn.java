package eat.neilson.Cafe;

public enum CoffeeAddOn {

    SWEET_CREAM,
    FRENCH_VANILLA,
    IRISH_CREAM,
    CARAMEL,
    MOCHA;

    private double price;

    CoffeeAddOn(){
        this.price = 0.30;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }

}
