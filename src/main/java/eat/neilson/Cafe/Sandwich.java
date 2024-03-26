package eat.neilson.Cafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Sandwich extends MenuItem {

    private double price = 0;

    private SandwichBread bread;

    private SandwichProtein protein;
    private ArrayList<SandwichAddOn> addOns = new ArrayList<>();




    public Sandwich(){
    }

    public Sandwich(SandwichBread bread, SandwichProtein protein, ArrayList<SandwichAddOn> addOns){
        this.bread = bread;
        this.protein = protein;
        this.addOns = addOns;
    }


    @Override
    public double price() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sandwich sandwich = (Sandwich) o;
        return bread == sandwich.bread &&
                protein == sandwich.protein &&
                Objects.equals(addOns, sandwich.addOns);
    }

    @Override
    public int hashCode() {
        Collections.sort(addOns);
        return Objects.hash(getClass(), bread, protein, addOns);
    }

    public static void main(String[] args){
        Sandwich a = new Sandwich();
        a.bread = SandwichBread.BAGEL;
        a.protein = SandwichProtein.BEEF;
        a.addOns.add(SandwichAddOn.ONIONS);
        a.addOns.add(SandwichAddOn.CHEESE);

        Sandwich b = new Sandwich();
        b.bread = SandwichBread.BAGEL;
        b.protein = SandwichProtein.BEEF;
        b.addOns.add(SandwichAddOn.CHEESE);
        b.addOns.add(SandwichAddOn.ONIONS);

        System.out.println(a);
        System.out.println(b);

        System.out.println(a == b);
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));
    }

}
