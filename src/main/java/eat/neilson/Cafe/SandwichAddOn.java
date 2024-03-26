package eat.neilson.Cafe;

public enum SandwichAddOn {

    LETTUCE(0.30),
    TOMATOES(0.30),
    ONIONS(0.30),
    CHEESE(1.00);

    private double price;
    SandwichAddOn(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }


}
