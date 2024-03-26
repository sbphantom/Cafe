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
        String[] words = this.name().split("_");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++){
            if (i != 0) sb.append(" ");
            sb.append(words[i].charAt(0)).append(words[i].substring(1).toLowerCase());
        }

        return sb.toString();
    }

}
