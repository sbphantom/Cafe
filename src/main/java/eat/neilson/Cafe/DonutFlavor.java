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
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
