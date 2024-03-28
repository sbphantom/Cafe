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

    @Override
    public int compareTo(MenuItem item){
        if(item instanceof Sandwich sandwich){
            if (this.bread.compareTo(sandwich.bread) !=0) {
                return this.bread.compareTo(sandwich.bread);
            }
            else if(this.protein.compareTo(sandwich.protein) != 0){
                return this.protein.compareTo(sandwich.protein);
            }
            else{
                // return(this.addOns.compareTo(sandwich.addOns));
                return 0;
            }
        }
        else return this.getClass().getSimpleName().compareTo(item.getClass().getSimpleName());
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sandwich sandwich = (Sandwich) o;
        return bread == sandwich.bread &&
                protein == sandwich.protein &&
                Objects.equals(addOns, sandwich.addOns);
    }

    public void addAddOn(SandwichAddOn addOn){
        addOns.add(addOn);
    }


    public void setProtein(SandwichProtein protein){
        this.protein = protein;
    }
    public void setBread(SandwichBread bread){
        this.bread = bread;
    }

    public void removeAddOn(SandwichAddOn addOn){
        addOns.remove(addOn);
    }

    public void setSandwichAddOn(ArrayList<SandwichAddOn> addOns){
        this.addOns = addOns;
    }

    public int addOnCount(){
        return addOns.size();
    }
    @Override
    public int hashCode() {
        Collections.sort(addOns);
        return Objects.hash(getClass(), bread, protein, addOns);
    }

    @Override
    public String toString(){
        return protein + " " + bread +" sandwich with ("+addOns +")";
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
