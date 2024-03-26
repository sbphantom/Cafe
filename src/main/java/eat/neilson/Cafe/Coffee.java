package eat.neilson.Cafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Coffee extends MenuItem {

    private CoffeeSize size;
    private ArrayList<CoffeeAddOn> addOns = new ArrayList<>();


    public Coffee(){
    }

    public Coffee(CoffeeSize size, ArrayList<CoffeeAddOn> addOns){
        this.size = size;
        this.addOns = addOns;
    }



    public void setCoffeeSize(CoffeeSize size){
        this.size = size;
    }

    public void addAddOn(CoffeeAddOn addOn){
        if (addOns.size() <= 5){
            addOns.add(addOn);
        }
    }

    public int addOnCount(){
        return addOns.size();
    }

    @Override
    public double price() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return size == coffee.size &&
                Objects.equals(addOns, coffee.addOns);
    }

    @Override
    public int hashCode() {
        Collections.sort(addOns);
        return Objects.hash(getClass(), size, addOns);
    }

    public static void main(String[] args){
        Coffee a = new Coffee();
        a.setCoffeeSize(CoffeeSize.GRANDE);
        a.addAddOn(CoffeeAddOn.CARAMEL);
        a.addAddOn(CoffeeAddOn.FRENCH_VANILLA);

        Coffee b = new Coffee();
        b.setCoffeeSize(CoffeeSize.GRANDE);
        b.addAddOn(CoffeeAddOn.FRENCH_VANILLA);
        b.addAddOn(CoffeeAddOn.CARAMEL);

        System.out.println(a);
        System.out.println(b);

        System.out.println(a == b);
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));
    }
}
