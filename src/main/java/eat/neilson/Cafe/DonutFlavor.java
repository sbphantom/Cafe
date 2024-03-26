package eat.neilson.Cafe;

public enum DonutFlavor {
    YEAST_FLAVOR_A(DonutType.YEAST),
    YEAST_FLAVOR_B(DonutType.YEAST),
    YEAST_FLAVOR_C(DonutType.YEAST),
    YEAST_FLAVOR_D(DonutType.YEAST),
    YEAST_FLAVOR_E(DonutType.YEAST),
    YEAST_FLAVOR_F(DonutType.YEAST),


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
