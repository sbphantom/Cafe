package eat.neilson.Cafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Donut extends MenuItem {

    private DonutType type;
    private DonutFlavor flavor;

    private int quantity;
    public Donut() {
    }

    public Donut(DonutType type, DonutFlavor flavor) {
        this.type = type;
        this.flavor = flavor;
    }


    @Override
    public double price() {
        return type.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donut donut = (Donut) o;
        return type == donut.type && flavor == donut.flavor;
    }

    public void setType(DonutType type) {
        this.type = type;
    }
    public void setFlavor(DonutFlavor flavor){
        this.flavor = flavor;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return this.quantity;
    }
    public DonutType getType(){
        return this.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, flavor);
    }


    @Override
    public String toString(){
        return flavor + " " + type + " (" + quantity +")";
    }

    @Override
    public int compareTo(MenuItem item){
            if(item instanceof Donut donut){
                if (this.type.compareTo(donut.type) !=0) {
                    return this.type.compareTo(donut.type);
                }
                else{
                    return(this.flavor.compareTo(donut.flavor));
                }
            }
            else return this.getClass().getSimpleName().compareTo(item.getClass().getSimpleName());
    }


    public static void main(String[] args) {
        Donut a = new Donut();
        a.type = DonutType.CAKE;
        a.flavor = DonutFlavor.CHOCOLATE;

        Donut b = new Donut();
        b.type = DonutType.CAKE;
        b.flavor = DonutFlavor.CHOCOLATE;

        System.out.println(a);
        System.out.println(b);

        System.out.println(a == b);
        System.out.println(a.hashCode() == b.hashCode());
        System.out.println(a.equals(b));
    }


}
