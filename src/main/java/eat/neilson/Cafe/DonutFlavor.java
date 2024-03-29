package eat.neilson.Cafe;

import java.util.HashMap;
import java.util.Map;

public enum DonutFlavor {
    MAPLE(DonutType.YEAST),
    SUGAR(DonutType.YEAST),
    BLUEBERRY(DonutType.YEAST),
    JELLY(DonutType.YEAST),
    CRULLER(DonutType.YEAST),
    COFFEE(DonutType.YEAST),


    VANILLA(DonutType.CAKE),
    CHOCOLATE(DonutType.CAKE),
    STRAWBERRY(DonutType.CAKE),

    PLAIN(DonutType.HOLE),
    POWDER(DonutType.HOLE),
    GLAZED(DonutType.HOLE);
    private static final Map<String, DonutFlavor> flavorMap = new HashMap<>();
    static {
        // Populate the map with flavor strings and corresponding DonutFlavor objects
        flavorMap.put("MAPLE", DonutFlavor.MAPLE);
        flavorMap.put("SUGAR", DonutFlavor.SUGAR);
        flavorMap.put("BLUEBERRY", DonutFlavor.BLUEBERRY);
        flavorMap.put("JELLY", DonutFlavor.JELLY);
        flavorMap.put("CRULLER", DonutFlavor.CRULLER);
        flavorMap.put("COFFEE", DonutFlavor.COFFEE);
        flavorMap.put("VANILLA", DonutFlavor.VANILLA);
        flavorMap.put("CHOCOLATE", DonutFlavor.CHOCOLATE);
        flavorMap.put("STRAWBERRY", DonutFlavor.STRAWBERRY);
        flavorMap.put("PLAIN", DonutFlavor.PLAIN);
        flavorMap.put("POWDER", DonutFlavor.POWDER);
        flavorMap.put("GLAZED", DonutFlavor.GLAZED);
    }
    private DonutType type;

    DonutFlavor(DonutType type){
        this.type = type;
    }

    public DonutType getType() {
        return type;
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

    public static DonutFlavor getDonutFlavor(String flavorString) {
        String flavor = flavorString.toUpperCase();

        return flavorMap.getOrDefault(flavor, null);
    }


}
