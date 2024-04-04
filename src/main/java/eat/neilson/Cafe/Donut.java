package eat.neilson.Cafe;
/**
 * Subclass of MenuItem for Donuts
 * @author Danny Onurah
 */

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
    public String name(){
        return flavor + " " + type + " Donut";
    }

    @Override
    public String addOnString(){
        return "";
    }
    /**
     * Calculates price of donut object.
     * @return price of donut.
     */
    @Override
    public double price() {
        return type.price;
    }
    /**
     * Equals method to compare to if two donuts are the same.
     *
     * @param o comparison donut.
     * @return boolean. True if equal. False if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donut donut = (Donut) o;
        return type == donut.type && flavor == donut.flavor;
    }

    /**
     *
     * @return hashcode for donut object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, flavor);
    }

    /**
     * toString method for donut .
     * @return a formatted String for the donut object.
     */
    @Override
    public String toString(){

        return flavor + " " + type + " (" + quantity +")";
    }
    /**
     * Setter method for donut Type.
     * @param type new DonutType to set.
     */
    public void setType(DonutType type) {
        this.type = type;
    }
    /**
     * Setter method for donut Flavor.
     * @param flavor new flavor to set.
     */
    public void setFlavor(DonutFlavor flavor){
        this.flavor = flavor;
    }
    /**
     * Setter method for donut Quantity.
     * @param  quantity new quantity to set.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    /**
     * Getter method for donut Quantity.
     * @return donut quantity
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Getter method for DonutType.
     * @return DonutType
     */
    public DonutType getType(){
        return this.type;
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
