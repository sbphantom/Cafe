package eat.neilson.Cafe;

public enum CoffeeSize {

    SHORT(1.99),
    TALL(2.49),
    GRANDE(2.99),
    VENTI(3.49);

    public double price;

    CoffeeSize(double price){
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
